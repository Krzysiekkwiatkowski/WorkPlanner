package my.application.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import my.application.pojo.WorkSchedule;
import my.application.pojo.Day;
import my.application.pojo.Driver;
import my.application.pojo.DriverData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelLoading {
    private WorkSchedule schedule;
    private List<Day> previousDays;
    private int monthLong;

    public ExcelLoading(WorkSchedule schedule, String fileName) {
        this.schedule = schedule;
        previousDays = new ArrayList<>();
        this.monthLong = LocalDate.now().lengthOfMonth();
        for (int i = 2; i >= 0; i--) {
            Day day = new Day((LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), monthLong - i)), false, false, schedule);
            previousDays.add(day);
        }
        loadData(fileName);
        setAvailability();
    }

    private void setAvailability() {
        List<Driver> drivers = DriverData.getDrivers();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        for (Day day : previousDays) {
            for (Driver driver : drivers) {
                day.setDriverAvailability(driver, list);
            }
        }
    }

    private void loadData(String fileName) {
        Workbook workbook = null;
        try {
            File file = new File(fileName);
            if(file.exists()) {
                workbook = Workbook.getWorkbook(file);
                Sheet sheet = workbook.getSheet(0);
                String input;
                for (int j = (monthLong - 1); j < (monthLong + 2); j++) {
                    Day day = previousDays.get(j - monthLong + 1);
                    for (int i = 2; i < 12; i++) {
                        input = sheet.getCell(i, j).getContents();
                        if (!input.contains("-")) {
                            if (input.contains(",")) {
                                String[] parts = input.split(",");
                                for (int k = 0; k < parts.length; k++) {
                                    if (day.getShifts().containsKey(i - 1)) {
                                        schedule.addShift((i - 1), Integer.parseInt(parts[k].trim()), day);
                                    }
                                }
                            } else {
                                if (!day.getShifts().containsKey((i - 1))) {
                                    day.getShifts().put((i - 1), new ArrayList<>());
                                }
                                schedule.addShift((i - 1), Integer.parseInt(input.trim()), day);
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

    public List<Day> getPreviousDays() {
        return previousDays;
    }
}