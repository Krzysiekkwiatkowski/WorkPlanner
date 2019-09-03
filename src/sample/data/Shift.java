package sample.data;

import java.util.ArrayList;
import java.util.List;

public final class Shift {

    private int number;
    private String hours;
    private boolean optional;
    private static List<Shift> shifts = new ArrayList<>();

    static {
        shifts.add(new Shift(1, "6-14", false));
        shifts.add(new Shift(2, "7:30-15:30", true));
        shifts.add(new Shift(3, "10-14", false));
        shifts.add(new Shift(4, "6-22", false));
        shifts.add(new Shift(5, "14-22", false));
        shifts.add(new Shift(6, "14-18", false));
        shifts.add(new Shift(7, "15:30-23:30", true));
        shifts.add(new Shift(8, "17-1", false));
        shifts.add(new Shift(9, "18-2", false));
        shifts.add(new Shift(10, "22-6", false));
    }

    private Shift(int number, String hours, boolean optional) {
        this.number = number;
        this.hours = hours;
        this.optional = optional;
    }

    public static Shift getShift(int number) {
        return shifts.get(number - 1);
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

    public boolean isOptional(int number) {
        return shifts.get(number - 1).optional;
    }

    @Override
    public String toString() {
        return this.hours;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getNumber() == ((Shift) obj).getNumber();
    }
}
