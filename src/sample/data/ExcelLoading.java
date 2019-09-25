package sample.data;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExcelLoading {
    private WorkSchedule schedule;
    private List<Day> previousDays;
    private int monthLong;

    // 1,2,3,4,5,6  od  1
    // 7  od  2
    // 8,9 dwa dni później od 1
    // 10 trzy dni póżniej id 5

    public ExcelLoading(WorkSchedule schedule, String fileName) {
        this.schedule = schedule;
        previousDays = new ArrayList<>();
        this.monthLong = LocalDate.now().lengthOfMonth();
        for (int i = 2; i >= 0; i--) {
            Day day = new Day((LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), monthLong - i)), false, false);
            previousDays.add(day);
        }
        loadData(fileName);
    }

    private void loadData(String fileName) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = workbook.getSheet(0);
            String input;
            for (int j = (monthLong - 1); j < (monthLong + 2); j++) {
                Day day = previousDays.get(j - monthLong + 1);
                for (int i = 2; i < 12; i++) {
                    input = sheet.getCell(i, j).getContents();
                    if(!input.contains("-")){
                        String[] parts = input.split(",");
                        for (int k = 0; k < parts.length; k++) {
                            if(day.getShifts().containsKey(i - 1)) {
                                day.addShift((i - 1), DriverData.getDriver(Integer.parseInt(parts[k].trim())));
                            }
                        }
                    }
                }
            }
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }
}
