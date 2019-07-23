package sample.data;

import java.time.LocalDate;

public class Condition {
    private int shiftNumber;
    private int driverNumber;
    private LocalDate date;
    private boolean possibleShift;

    public Condition(int shiftNumber, int driverNumber, LocalDate date, boolean possibleShift) {
        this.shiftNumber = shiftNumber;
        this.driverNumber = driverNumber;
        this.date = date;
        this.possibleShift = possibleShift;
    }

    public int getDriverNumber() {
        return driverNumber;
    }

    public LocalDate getDate(){
        return date;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public boolean isPossibleShift() {
        return possibleShift;
    }
}
