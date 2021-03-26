package my.application.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Shift {

    private int number;
    private String hours;
    private static List<Shift> shifts = new ArrayList<>();

    static {
        shifts.add(new Shift(1, "6-14"));
        shifts.add(new Shift(2, "7:30-15:30"));
        shifts.add(new Shift(3, "10-14"));
        shifts.add(new Shift(4, "6-22"));
        shifts.add(new Shift(5, "14-22"));
        shifts.add(new Shift(6, "14-18"));
        shifts.add(new Shift(7, "15:30-23:30"));
        shifts.add(new Shift(8, "17-1"));
        shifts.add(new Shift(9, "18-2"));
        shifts.add(new Shift(10, "22-6"));
    }

    private Shift(int number, String hours) {
        this.number = number;
        this.hours = hours;
    }

    public static Shift getShift(int number) {
        return shifts.get(number - 1);
    }

    public static Shift getShift(String shiftSignature){
        Optional<Shift> result = shifts.stream()
                .filter(s -> s.getHours().equals(shiftSignature))
                .findFirst();
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    public static List<Shift> getShifts(){
        return shifts;
    }

    int getNumber() {
        return number;
    }

    public String getHours() {
        return hours;
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
