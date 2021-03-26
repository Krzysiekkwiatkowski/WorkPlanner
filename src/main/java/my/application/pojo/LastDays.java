package my.application.pojo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LastDays {
    private LocalDate date;
    private Map<String, String> shifts;

    public LastDays(LocalDate date){
        this.date = date;
        this.shifts = new HashMap<>();
    }

    public Map<String, String> getShifts() {
        return shifts;
    }

    LocalDate getDate() {
        return date;
    }
}
