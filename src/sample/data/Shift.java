package sample.data;

import java.util.ArrayList;
import java.util.List;

public final class Shift {

    private int number;
    private String hours;
    private static List<Shift> shifts = new ArrayList<>();

    static {
        shifts.add(new Shift(1, "6-14"));
        shifts.add(new Shift(2, "14-22"));
        shifts.add(new Shift(3, "22-6"));
        shifts.add(new Shift(4, "6-22"));
        shifts.add(new Shift(5, "7:30-15:30"));
        shifts.add(new Shift(6, "15:30-23:30"));
        shifts.add(new Shift(7, "17-1"));
        shifts.add(new Shift(8, "18-2"));
        shifts.add(new Shift(9, "10-14"));
        shifts.add(new Shift(10, "14-18"));
    }

    private Shift(int number, String hours) {
        this.number = number;
        this.hours = hours;
    }

    public static Shift getShift(int number) {
        return shifts.get(number);
    }

    public static List<Shift> getShifts(){
        return shifts;
    }

    public int getNumber() {
        return number;
    }

    public String getHours() {
        return hours;
    }

    @Override
    public String toString() {
        return this.hours;
    }
}
