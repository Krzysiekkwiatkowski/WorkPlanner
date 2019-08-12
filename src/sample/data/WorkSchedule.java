package sample.data;

import sample.Controller;
import sample.HolidayController;

import java.time.LocalDate;
import java.util.*;

public class WorkSchedule {
    private String month;
    private Controller controller;
    private List<Day> days;
    private Map<Driver, List<Condition>> conditions;
    private ExcelSaving excel = new ExcelSaving(this);

    public WorkSchedule(Controller controller) {
        days = new ArrayList<>();
        this.controller = controller;
        String month = LocalDate.now().getMonth().plus(1).toString();
        switch (month) {
            case "JANUARY":
                this.month = "Styczeń";
                break;
            case "FEBRUARY":
                this.month = "Luty";
                break;
            case "MARCH":
                this.month = "Marzec";
                break;
            case "APRIL":
                this.month = "Kwiecień";
                break;
            case "MAY":
                this.month = "Maj";
                break;
            case "JUNE":
                this.month = "Czerwiec";
                break;
            case "JULY":
                this.month = "Lipiec";
                break;
            case "AUGUST":
                this.month = "Sierpień";
                break;
            case "SEPTEMBER":
                this.month = "Wrzesień";
                break;
            case "OCTOBER":
                this.month = "Październik";
                break;
            case "NOVEMBER":
                this.month = "Listopad";
                break;
            case "DECEMBER":
                this.month = "Grudzień";
                break;
        }
        conditions = new HashMap<>();
        for (Driver driver : DriverData.getDrivers()) {
            conditions.put(driver, new ArrayList<>());
        }
    }

    private void initializeDays() {
        List<LocalDate> holidays = HolidayController.getHolidays();
        for (int i = 0; i < LocalDate.now().getMonth().plus(1).length(false); i++) {
            LocalDate actualDate = LocalDate.now().plusMonths(1).withDayOfMonth(i + 1);
            Day day = new Day(actualDate, holidays.contains(actualDate));
            days.add(day);
        }
    }

    public void generate() {
        for (Day day : days) {
            manageDay(day);
        }
    }

    private void manageDay(Day day) {
        boolean finish = false;
        int actualShiftNumber = 1;
        while (!finish) {
            if(actualShiftNumber == 0){
               finish = true;
            }
            if(day.getShifts().containsKey(actualShiftNumber)) {
                prepareShift(day, actualShiftNumber);
                actualShiftNumber = generateNext(actualShiftNumber);
            } else {
                actualShiftNumber = generateNext(actualShiftNumber);
                if (actualShiftNumber > Shift.getShifts().size()) {
                    finish = true;
                }
            }
        }
    }

    private boolean prepareShift(Day day, int actualShiftNumber) {
        int numberOfDrivers = checkNumberOfDrivers(day, actualShiftNumber);
        while (day.getShifts().get(actualShiftNumber).size() < numberOfDrivers) {
            int driverNumber = generateDriverNumber();
            if (day.checkAvailability(driverNumber, actualShiftNumber) && day.getShifts().containsKey(actualShiftNumber)) {
                day.addShift(actualShiftNumber, DriverData.getDriver(driverNumber));
                if(day.getShifts().get(actualShiftNumber).size() == numberOfDrivers){
                    return true;
                }
            }
        }
        return false;
    }

    private int checkNumberOfDrivers(Day day, int shiftNumber){
        String dayCondition = day.getDate().getDayOfWeek().toString();
        switch (dayCondition){
            case "MONDAY":
                break;
            case "TUESDAY":
                break;
            case "WEDNESDAY":
                break;
            case "THURSDAY":
                break;
            case "FRIDAY":
                break;
            case "SATURDAY":
                break;
            case "SUNDAY":
                break;
        }
        return 0;
    }

    private int generateNext(int previous) {
        if (++previous <= 10) {
            return previous;
        }
        return 0;
    }

    private int generateDriverNumber() {
        return (int) (Math.random() * DriverData.getDrivers().size() + 1);
    }

    public void clearConditions() {
        for (Driver driver : conditions.keySet()) {
            conditions.get(driver).clear();
        }
    }

    public void addCondition(Driver driver, Condition condition) {
        if (driver != null && condition != null) {
            if (conditions.containsKey(driver)) {
                conditions.get(driver).add(condition);
            } else {
                conditions.get(driver).add(condition);
            }
        }
    }

    public void generateWorkSchedule() {
        initializeDays();
        setConditions();
        generate();
        showWorkSchedule();
        saveWorkSchedule();
    }

    public void showWorkSchedule() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < days.size(); i++) {
            sb.append(days.get(i).toString() + "  |  " + days.get(i).dailyWorkSchedule() + "\n");
        }
        this.controller.getDisplaySchedule().setText(sb.toString());
    }

    public Day getDay(LocalDate date) {
        for (Day day : days) {
            if (day.getDate().toString().equals(date.toString())) {
                return day;
            }
        }
        return null;
    }

    private void saveWorkSchedule(){
        excel.saveData();
    }

    public Map<Driver, List<Condition>> getConditions() {
        return conditions;
    }

    public String getMounth() {
        return month;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setConditions() {
        for (Driver driver : conditions.keySet()) {
            for (Condition condition : conditions.get(driver)) {
                days.get(condition.getDate().getDayOfMonth() - 1).setCondition(driver, condition);
            }
        }
    }
}