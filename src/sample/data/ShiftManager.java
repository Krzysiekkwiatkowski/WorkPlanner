package sample.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiftManager {
    public List<Integer> getRequired(Day day) {
        List<Integer> shifts;
        String dayName = day.getDate().getDayOfWeek().toString();
        switch (dayName) {
            case "FRIDAY":
                shifts = Arrays.asList(1, 2, 4, 5, 6, 8, 9, 10);
                break;
            case "SATURDAY":
                shifts = Arrays.asList(1, 3, 5, 8, 9, 10);
                break;
            case "SUNDAY":
                if(day.isNextDayHoliday()) {
                    shifts = Arrays.asList(1, 3, 5, 8, 9, 10);
                    break;
                } else {
                    shifts = Arrays.asList(1, 3, 5, 8, 10);
                    break;
                }
            default:
                shifts = getTypicalDay(day);
                break;
        }
        return sortList(shifts);
    }

    public List<Integer> getOptional(Day day) {
        List<Integer> shifts = new ArrayList<>();
        String dayName = day.getDate().getDayOfWeek().toString();
        switch (dayName) {
            case "SATURDAY":
                shifts.add(7);
                shifts.add(2);
                break;
            default:
                shifts.add(7);
                break;
        }
        return shifts;
    }

    private List<Integer> getTypicalDay(Day day) {
        if(day.isNextDayHoliday()){
            return Arrays.asList(1, 2, 4, 5, 6, 8, 9, 10);
        } else {
            return Arrays.asList(1, 2, 4, 5, 6, 8, 10);
        }
    }

    private List<Integer> sortList(List<Integer> list) {
        if (list != null) {
            List<Integer> sortedList = new ArrayList<>(list);
            if (sortedList.contains(9)) {
                int index = sortedList.indexOf(9);
                sortedList.remove(index);
                sortedList.add(0, 9);
            }
            if (sortedList.contains(8)) {
                int index = sortedList.indexOf(8);
                sortedList.remove(index);
                sortedList.add(0, 8);
            }
            if (sortedList.contains(4)) {
                int index = sortedList.indexOf(4);
                sortedList.remove(index);
                sortedList.add(0, 4);
            }
            return sortedList;
        }
        return null;
    }
}