package sample.data;

import sample.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkSchedule {
    private String month;
    private Controller controller;
    private List<Day> days;
    private Map<Driver, List<Condition>> conditions;

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
        for (int i = 0; i < LocalDate.now().getMonth().plus(1).length(false); i++) {
            Day day = new Day(LocalDate.now().plusMonths(1).withDayOfMonth(i + 1));
            days.add(day);
        }
        conditions = new HashMap<>();
        for (Driver driver : DriverData.getDrivers()) {
            conditions.put(driver, new ArrayList<>());
        }
    }

    public String getMounth() {
        return month;
    }

    public List<Day> getDays() {
        return days;
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

    public Map<Driver, List<Condition>> getConditions() {
        return conditions;
    }

    public void generateWorkSchedule() {
        setConditions();
        showWorkSchedule();
    }

    public void setConditions(){
        for (Driver driver : conditions.keySet()) {
            for (Condition condition : conditions.get(driver)) {
                days.get(condition.getDate().getDayOfMonth() -1).setCondition(driver, condition);
            }
        }
    }

    public void showWorkSchedule() {
        StringBuilder sb = new StringBuilder();
        for (Driver driver : conditions.keySet()) {
            for (Condition condition : conditions.get(driver)) {
                sb.append(condition.toString() + "\n");
            }
        }
        this.controller.getDisplaySchedule().setText(sb.toString());
    }

    public Day getDay(LocalDate date){
        for (Day day : days) {
            if(day.getDate().toString().equals(date.toString())){
                return day;
            }
        }
        return null;
    }
}
