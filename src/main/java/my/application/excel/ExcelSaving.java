package my.application.excel;

import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import jxl.write.Label;
import my.application.pojo.Shift;
import my.application.pojo.WorkSchedule;
import my.application.pojo.Day;
import my.application.pojo.Driver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public class ExcelSaving {
    private static final String PATH_PROPERTIES_FILE = "path.properties";
    private static final String PATH_PROPERTY_NAME = "userFilePath";
    static final String WORK_SCHEDULE = "Grafik";
    static final String FILE_EXTENSION = ".xls";


    private WorkSchedule schedule;
    private String fileName;
    private File file;
    private File fileCopy;
    private String applicationFile;
    private WritableWorkbook excelData;
    private WritableSheet sheet;
    private WritableCellFormat cellFormat = null;
    private Properties properties;

    public ExcelSaving(WorkSchedule workSchedule) {
        this.schedule = workSchedule;
        fileName = WORK_SCHEDULE + schedule.getMonth(LocalDate.now().getMonth().plus(1).toString()) + FILE_EXTENSION;
        applicationFile = fileName;
        this.properties = new Properties();
        getPropertiesFromXml();
    }

    public void createFiles(){
        file = new File(applicationFile);
        if(verifyUserFilePath()) {
            fileCopy = new File(properties.getProperty(PATH_PROPERTY_NAME) + File.separator + fileName);
        }
        try {
            excelData = Workbook.createWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = excelData.createSheet(WORK_SCHEDULE, 0);
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
            if(fileCopy != null){
                copyFile();
            }
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

    private void copyFile(){
        try {
            Files.copy(file.toPath(), fileCopy.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            System.out.println("Couldn't copy work schedule file");
        }
    }

    private boolean verifyUserFilePath(){
        String path = properties.getProperty(PATH_PROPERTY_NAME);
        return (path != null) && (!path.equals(""));
    }

    private void setProperties(String path){
        properties.setProperty(PATH_PROPERTY_NAME, (((path != null) && (!path.equals(""))) ? path : ""));
        savePropertiesToXML();
    }

    private void savePropertiesToXML(){
        try {
            properties.storeToXML(new FileOutputStream(PATH_PROPERTIES_FILE), null);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void getPropertiesFromXml(){
        try {
            if(Files.exists(Paths.get(PATH_PROPERTIES_FILE))){
                properties.loadFromXML(new FileInputStream(PATH_PROPERTIES_FILE));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setUserFilePath(String path){
        setProperties(path);
    }

    public String getUserFilePath(){
        return properties.getProperty(PATH_PROPERTY_NAME);
    }

    public boolean isValidPath(){
        return verifyUserFilePath();
    }
}
