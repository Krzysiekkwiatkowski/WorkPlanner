package sample.data;

import java.util.Arrays;
import java.util.Collections;
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
                shifts = Arrays.asList(1, 3, 5, 8, 10);
                break;
            default:
                shifts = getTypicalDay();
                if(day.isNextDayHoliday()){
                    shifts.add(9);
                    Collections.sort(shifts);
                }
                break;
        }
        return shifts;
    }

    public List<Integer> getOptional(Day day) {
        List<Integer> shifts;
        String dayName = day.getDate().getDayOfWeek().toString();
        switch (dayName) {
            case "FRIDAY":
                shifts = null;
                break;
            case "SATURDAY":
                shifts = Arrays.asList(2);
                break;
            case "SUNDAY":
                shifts = null;
                break;
            default:
                shifts = null;
                break;
        }
        return shifts;
    }

    private List<Integer> getTypicalDay() {
        return Arrays.asList(1, 2, 4, 5, 6, 8, 10);
    }
}