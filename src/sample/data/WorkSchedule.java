package sample.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkSchedule {
    private String mounth;
    private List<Day> days;
    private Map<Driver, List<Condition>> conditions;

    public WorkSchedule() {
        days = new ArrayList<>();
        String month = LocalDate.now().getMonth().plus(1).toString();
        switch (month) {
            case "JANUARY":
                this.mounth = "Styczeń";
                break;
            case "FEBRUARY":
                this.mounth = "Luty";
                break;
            case "MARCH":
                this.mounth = "Marzec";
                break;
            case "APRIL":
                this.mounth = "Kwiecień";
                break;
            case "MAY":
                this.mounth = "Maj";
                break;
            case "JUNE":
                this.mounth = "Czerwiec";
                break;
            case "JULY":
                this.mounth = "Lipiec";
                break;
            case "AUGUST":
                this.mounth = "Sierpień";
                break;
            case "SEPTEMBER":
                this.mounth = "Wrzesień";
                break;
            case "OCTOBER":
                this.mounth = "Październik";
                break;
            case "NOVEMBER":
                this.mounth = "Listopad";
                break;
            case "DECEMBER":
                this.mounth = "Grudzień";
                break;
        }
        for (int i = 0; i < LocalDate.now().getMonth().length(false); i++) {
            Day day = new Day(LocalDate.now().plusMonths(1).withDayOfMonth(i + 1));
            days.add(day);
        }
        conditions = new HashMap<>();
    }

    public String getMounth() {
        return mounth;
    }

    public List<Day> getDays() {
        return days;
    }

    public void clearConditions() {
        this.conditions.clear();
    }

    public void addCondition(Driver driver, Condition condition) {
        if(driver != null && condition != null) {
            if (conditions.containsKey(driver)) {
                conditions.get(driver).add(condition);
            } else {
                conditions.put(driver, new ArrayList<>());
                conditions.get(driver).add(condition);
            }
        }
    }
}
