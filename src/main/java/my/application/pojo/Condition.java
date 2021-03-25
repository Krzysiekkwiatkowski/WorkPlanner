package my.application.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Condition {
    private List<Shift> shifts;
    private int driverNumber;
    private LocalDate date;
    private boolean possibleShift;
    private boolean allDayLong;

    public Condition(List<Shift> shifts, int driverNumber, LocalDate date, boolean possibleShift) {
        if(date != null && shifts != null) {
            this.shifts = shifts;
            this.driverNumber = driverNumber;
            this.date = date;
            this.possibleShift = possibleShift;
            if(shifts.size() != 10) {
                this.allDayLong = false;
            } else {
                this.allDayLong = true;
            }
        }
    }

    public int getDriverNumber() {
        return driverNumber;
    }

    public List<Integer> getShiftsNumbers(){
        List<Integer> shiftNumbers = new ArrayList<>();
        if(this.shifts != null){
            if(shifts.size() == 1){
                shiftNumbers.add(shifts.get(0).getNumber());
                return shiftNumbers;
            } else {
                for (Shift shift : shifts) {
                    shiftNumbers.add(shift.getNumber());
                }
                return shiftNumbers;
            }
        }
        return null;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPossibleShift() {
        return possibleShift;
    }

    public boolean isAllDayLong(){
        return allDayLong;
    }

    @Override
    public String toString() {
        return driverNumber + ", " + date.toString() + ((shifts.size() == 1) ? (" godziny " + shifts.get(0).getHours()) : " cały dzień");
    }
}
