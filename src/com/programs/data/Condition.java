package com.programs.data;

import java.time.LocalDate;

public class Condition {
    private Shift shift;
    private int driverNumber;
    private LocalDate date;
    private boolean possibleShift;

    public Condition(Shift shift, int driverNumber, LocalDate date, boolean possibleShift) {
        if(date != null && shift != null) {
            this.shift = shift;
            this.driverNumber = driverNumber;
            this.date = date;
            this.possibleShift = possibleShift;
        }
    }

    public int getDriverNumber() {
        return driverNumber;
    }

    public int getShiftNumber(){
        return shift.getNumber();
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPossibleShift() {
        return possibleShift;
    }

    @Override
    public String toString() {
        return driverNumber + ", " + date.toString() + " godziny " + shift.getHours();
    }
}
