package sample.data;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExcelSaving {

    private final File file = new File("workschedule.xls");
    private final WorkSchedule schedule;
    private WritableWorkbook excelData;
    private WritableSheet sheet;

    public ExcelSaving(WorkSchedule workSchedule) {
        this.schedule = workSchedule;
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
        for (Shift shift : Shift.getShifts()) {
            addToSheet(generateLabel(column, 1, shift.getHours()));
            column++;
        }
        for (Day day : schedule.getDays()) {
            addToSheet(generateLabel(1, row, day.getDate().toString()));
            int shiftNumber = 1;
            for (Shift shift : Shift.getShifts()) {
                List<Driver> drivers = day.getShifts().get(shiftNumber);
                StringBuilder sb = new StringBuilder();
                if(drivers != null){
                    if(drivers.size() > 0){
                        for (int i = 0; i < drivers.size(); i++) {
                            if(i < drivers.size() - 1){
                                sb.append(drivers.get(i).getNumber() + ", ");
                            } else {
                                sb.append(drivers.get(i).getNumber());
                            }
                        }
                    }
                } else {
                    sb.append("Brak zmiany!");
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
        return new Label(column, row, text);
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
            excelData.write();
            excelData.close();
        } catch (WriteException | IOException e) {
            System.out.println("Couldn't save the file");
            e.printStackTrace();
        }
    }
}
