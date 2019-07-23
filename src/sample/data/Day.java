package sample.data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day {
    private LocalDate date;
    private Map<Shift, List<Driver>> shifts;

    public Day(LocalDate date) {
        this.date = date;
        shifts = new HashMap<>();
        for (Shift shift : Shift.getShifts()) {
            shifts.put(shift, null);
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
