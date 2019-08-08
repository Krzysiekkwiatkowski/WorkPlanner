package sample.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day {
    private LocalDate date;
    private boolean nextDayHoliday;
    private Map<Shift, List<Driver>> shifts;
    private Map<Driver, List<Condition>> conditions;
    private Map<Driver, Map<Shift, Boolean>> availability;

    public Day(LocalDate date, boolean holiday) {
        this.date = date;
        String dayOfWeek = date.getDayOfWeek().toString();
        this.nextDayHoliday = holiday;
        shifts = new HashMap<>();
        List<Integer> tempConditions = new ArrayList<>();
        switch (dayOfWeek) {
            case "FRIDAY":
                tempConditions.add(8);
                manageShifts(tempConditions);
                break;
            case "SATURDAY":
                tempConditions.add(4);
                tempConditions.add(8);
                tempConditions.add(10);
                manageShifts(tempConditions);
                break;
            case "SUNDAY":
                tempConditions.add(5);
                tempConditions.add(8);
                tempConditions.add(10);
                manageShifts(tempConditions);
                break;
            default:
                manageShifts(null);
                break;
        }
        conditions = new HashMap<>();
        availability = new HashMap<>();
        for (Driver driver : DriverData.getDrivers()) {
            Map<Shift, Boolean> availabilityMap = new HashMap<>();
            for (Shift shift : Shift.getShifts()) {
                availabilityMap.put(shift, true);
            }
            availability.put(driver, availabilityMap);
        }
    }

    public void manageShifts(List<Integer> conditions) {
        if(nextDayHoliday == true){
            if(!conditions.contains(8)){
                conditions.add(8);
            }
        }
        if(conditions != null){
            for (Shift shift : Shift.getShifts()) {
                if(!conditions.contains(shift.getNumber())){
                    shifts.put(shift, new ArrayList<>());
                }
            }
        } else {
            for (Shift shift : Shift.getShifts()) {
                shifts.put(shift, new ArrayList<>());
            }
        }
    }

    public void setCondition(Driver driver, Condition condition) {
        if (conditions.containsKey(driver)) {
            this.conditions.get(driver).add(condition);
        } else {
            this.conditions.put(driver, new ArrayList<>());
            this.conditions.get(driver).add(condition);
        }
        if (!condition.isPossibleShift()) {
            availability.get(driver).put(Shift.getShift(condition.getShiftNumber()), false);
        } else {
            for (Shift shift : Shift.getShifts()) {
                if (shift.getNumber() != condition.getShiftNumber()) {
                    availability.get(driver).put(shift, false);
                } else {
                    availability.get(driver).put(shift, true);
                    shifts.get(shift).add(driver);
                }
            }
        }
    }

    public void removeCondition(Condition condition) {
        Driver driver = DriverData.getDriver(condition.getDriverNumber());
        if (conditions.containsKey(driver)) {
            for (Condition driverCondition : conditions.get(driver)) {
                if (driverCondition == condition) {
                    conditions.get(driver).remove(driverCondition);
                    availability.get(driver).replace(Shift.getShift(condition.getShiftNumber()), true);
                }
            }
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public String dailyWorkSchedule() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= shifts.size(); i++) {
            sb.append("[" + Shift.getShift(i).getNumber() + "]   ");
            List<Driver> drivers = shifts.get(Shift.getShift(i));
            for (int j = 0; j < drivers.size(); j++) {
                if (j < drivers.size() - 1) {
                    sb.append(drivers.get(j).getNumber() + ",");
                } else {
                    sb.append(drivers.get(j).getNumber() + "   ");
                }
            }
        }
        return sb.toString();
    }

    public void addShift(int shiftNumber, Driver driver) {
        shifts.get(Shift.getShift(shiftNumber)).add(driver);
    }

    private int generateShiftNumber() {
        return (int) (Math.random() * 10 + 1);
    }

    public int shifts() {
        return this.shifts.size();
    }
}