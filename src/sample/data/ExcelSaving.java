package sample.data;

import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ExcelSaving {
    private final WorkSchedule schedule;
    private final File file;
    private WritableWorkbook excelData;
    private WritableSheet sheet;
    private WritableCellFormat cellFormat = null;

    public ExcelSaving(WorkSchedule workSchedule) {
        this.schedule = workSchedule;
        file = new File("Grafik" + schedule.getMonth(LocalDate.now().getMonth().plus(1).toString()) + ".xls");
        try {
            excelData = Workbook.createWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = excelData.createSheet("Grafik", 0);
    }

    public void saveData() {
        int row = 2;
        int column = 2;
        int actualRow = 2;
        int actualColumn = 2;
        Label month = new Label(1,0, schedule.getMonth());
        Label date = generateLabel(1,1,"Data");
        addToSheet(date);
        try {
            cellFormat = new WritableCellFormat();
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat.setAlignment(Alignment.CENTRE);
            month.setCellFormat(cellFormat);
            sheet.addCell(month);
            sheet.mergeCells(1, 0, 11, 0);
        } catch (WriteException e){
            System.out.println("Couldn't save to file");
            e.printStackTrace();
        }
        for (Shift shift : Shift.getShifts()) {
            addToSheet(generateLabel(column, 1, shift.getHours()));
            column++;
        }
        for (Day day : schedule.getDays()) {
            addToSheet(generateLabel(1, row, day.toString()));
            int shiftNumber = 1;
            for (Shift shift : Shift.getShifts()) {
                List<Driver> drivers = day.getShifts().get(shiftNumber);
                StringBuilder sb = new StringBuilder();
                if (drivers != null) {
                    if (drivers.size() > 0) {
                        for (int i = 0; i < drivers.size(); i++) {
                            if (i < drivers.size() - 1) {
                                sb.append(drivers.get(i).getNumber() + ", ");
                            } else {
                                sb.append(drivers.get(i).getNumber());
                            }
                        }
                    } else {
                        sb.append("-");
                    }
                } else {
                    sb.append("-");
                }
                addToSheet(generateLabel(actualColumn, actualRow, sb.toString()));
                actualColumn++;
                shiftNumber++;
            }
            actualColumn = 2;
            actualRow++;
            row++;
        }
        saveFile();
    }

    private Label generateLabel(int column, int row, String text) {
        Label label = new Label(column, row, text);
        cellFormat = new WritableCellFormat();
        try {
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        } catch (WriteException e){
            System.out.println("Couldn't set border");
            e.printStackTrace();
        }
        label.setCellFormat(cellFormat);
        return label;
    }

    private void addToSheet(Label label) {
        try {
            this.sheet.addCell(label);
        } catch (WriteException e) {
            System.out.println("Couldn't write to the file");
            e.printStackTrace();
        }
    }

    private void saveFile() {
        try {
            for (int i = 0; i < sheet.getColumns(); i++) {
                setWidth(i, sheet.getColumnView(i), maxWidth(sheet.getColumn(i)));
            }
            excelData.write();
            excelData.close();
        } catch (WriteException | IOException e) {
            System.out.println("Couldn't save the file");
            e.printStackTrace();
        }
    }

    private void setWidth(int number, CellView column, int preferedWidth) {
        if(preferedWidth == 0){
            column.setSize(4 * 256);
        } else if(preferedWidth < 6) {
            column.setSize(preferedWidth * 256 + 200);
        } else {
            column.setSize(preferedWidth * 256);
        }
        sheet.setColumnView(number, column);
    }

    private int maxWidth(Cell[] cells) {
        int max = 0;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getContents().length() > max) {
                max = cells[i].getContents().trim().length();
            }
        }
        return max;
    }
}
