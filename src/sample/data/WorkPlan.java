package sample.data;

import java.time.LocalDate;
import java.util.List;

public class WorkPlan {
    private String mounth;
    private List<Day> days;

    public WorkPlan() {
        String month = LocalDate.now().getMonth().plus(1).toString();
        switch (month){
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
    }

    public String getMounth() {
        return mounth;
    }
}
