package my.application.pojo;

import java.util.ArrayList;
import java.util.List;

public class ShiftManager {
    private Setting setting;

    ShiftManager(){
        setting = new Setting();
    }

    List<Integer> getRequired(Day day) {
        List<Integer> shifts = new ArrayList<>();
        Shift.getShifts().forEach(s -> {
            if(setting.getRequiredShifts().get(day.getDate().getDayOfWeek()).get(s.getHours()) != 0){
                shifts.add(s.getNumber());
            }
        });
        if(day.isNextDayHoliday()){
            shifts.add(9);
        }
        return sortRequiredList(shifts);
    }

    List<Integer> getOptional(Day day) {
        List<Integer> shifts = new ArrayList<>();
        Shift.getShifts().forEach( s -> {
            if(setting.getOptionalShifts().get(day.getDate().getDayOfWeek()).get(s.getHours()) != 0){
                shifts.add(s.getNumber());
            }
        });
        return sortOptionalList(shifts);
    }

    private List<Integer> sortOptionalList(List<Integer> list){
        if(list != null){
            List<Integer> sortedList = new ArrayList<>(list);
            if(sortedList.contains(6)){
                int index = sortedList.indexOf(6);
                sortedList.remove(index);
                sortedList.add(0, 6);
            }
            if(sortedList.contains(2)){
                int index = sortedList.indexOf(2);
                sortedList.remove(index);
                sortedList.add(0, 2);
            }
            if(sortedList.contains(7)){
                int index = sortedList.indexOf(7);
                sortedList.remove(index);
                sortedList.add(0, 7);
            }
            return sortedList;
        }
        return null;
    }

    private List<Integer> sortRequiredList(List<Integer> list) {
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

    int checkNumberOfDrivers(Day day, int shiftNumber){
        if(setting.getRequiredShifts().get(day.getDate().getDayOfWeek()).get(Shift.getShift(shiftNumber).getHours()) != 0){
            return setting.getRequiredShifts().get(day.getDate().getDayOfWeek()).get(Shift.getShift(shiftNumber).getHours());
        } else if(setting.getOptionalShifts().get(day.getDate().getDayOfWeek()).get(Shift.getShift(shiftNumber).getHours()) != 0){
            return setting.getOptionalShifts().get(day.getDate().getDayOfWeek()).get(Shift.getShift(shiftNumber).getHours());
        } else {
            if(shiftNumber == 9 && day.isNextDayHoliday()){
                return 1;
            }
            return 0;
        }
    }
}