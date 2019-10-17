package com.programs.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day {
    private LocalDate date;
    private boolean nextDayHoliday;
    private boolean holiday;
    private Map<Integer, List<Driver>> shifts;
    private Map<Driver, List<Condition>> conditions;
    private Map<Driver, Map<Integer, Boolean>> availability;

    public Day(LocalDate date, boolean nextDayHoliday, boolean holiday) {
        this.date = date;
        this.nextDayHoliday = nextDayHoliday;
        this.holiday = holiday;
        shifts = new HashMap<>();
        initializeLists(date);
    }

    protected void updateListsDeletion(Driver driver) {
        for (Integer shiftNumber : shifts.keySet()) {
            if (shifts.get(shiftNumber).contains(driver)) {
                shifts.get(driver).remove(driver);
            }
        }
        if (conditions.containsKey(driver)) {
            conditions.remove(driver);
        }
        if (availability.containsKey(driver)) {
            availability.remove(driver);
        }
    }

    protected void updateListsAddition(Driver driver) {
        initializeAvailability(driver);
    }

    private void initializeLists(LocalDate date) {
        String dayOfWeek = date.getDayOfWeek().toString();
        List<Integer> tempConditions = new ArrayList<>();
        switch (dayOfWeek) {
            case "FRIDAY":
                manageShifts(tempConditions);
                break;
            case "SATURDAY":
                tempConditions.add(4);
                tempConditions.add(6);
                manageShifts(tempConditions);
                break;
            case "SUNDAY":
                tempConditions.add(2);
                tempConditions.add(4);
                tempConditions.add(6);
                tempConditions.add(9);
                manageShifts(tempConditions);
                break;
            default:
                tempConditions.add(9);
                manageShifts(tempConditions);
                break;
        }
        conditions = new HashMap<>();
        availability = new HashMap<>();
        for (Driver driver : DriverData.getDrivers()) {
            initializeAvailability(driver);
        }
    }

    private void initializeAvailability(Driver driver) {
        String dayOfWeek = date.getDayOfWeek().toString();
        Map<Integer, Boolean> availabilityMap = new HashMap<>();
        for (Shift shift : Shift.getShifts()) {
            if (shift.getNumber() == 10 && driver.getNumber() == 15) {
                availabilityMap.put(shift.getNumber(), false);
            } else {
                if (driver.getNumber() != 15) {
                    availabilityMap.put(shift.getNumber(), true);
                } else {
                    if (holiday || dayOfWeek.equals("SUNDAY")) {
                        if (shift.getNumber() == 1 || shift.getNumber() == 3) {
                            availabilityMap.put(shift.getNumber(), true);
                        } else {
                            availabilityMap.put(shift.getNumber(), false);
                        }
                    } else {
                        availabilityMap.put(shift.getNumber(), true);
                    }
                }
            }
        }
        this.availability.put(driver, availabilityMap);
    }

    public void setDriverAvailability(Driver driver, List<Integer> conditions) {
        Map<Integer, Boolean> map = availability.get(driver);
        if (map != null) {
            for (Integer shiftNumber : map.keySet()) {
                if (conditions.contains(shiftNumber)) {
                    map.replace(shiftNumber, false);
                }
            }
        }
    }

    private void manageShifts(List<Integer> conditions) {
        if (nextDayHoliday) {
            conditions = redCardCondition(conditions);
        }
        if (conditions.size() > 0) {
            for (Shift shift : Shift.getShifts()) {
                if (!conditions.contains(shift.getNumber())) {
                    shifts.put(shift.getNumber(), new ArrayList<>());
                }
            }
        } else {
            for (Shift shift : Shift.getShifts()) {
                shifts.put(shift.getNumber(), new ArrayList<>());
            }
        }
    }

    private List<Integer> redCardCondition(List<Integer> conditions) {
        int index = -1;
        if (conditions.contains(9)) {
            index = conditions.indexOf(9);
        }
        conditions.remove(index);
        return conditions;
    }

    public void setCondition(Driver driver, Condition condition) {
        if (conditions.containsKey(driver)) {
            this.conditions.get(driver).add(condition);
        } else {
            this.conditions.put(driver, new ArrayList<>());
            this.conditions.get(driver).add(condition);
        }
        if (!condition.isPossibleShift()) {
            availability.get(driver).put(condition.getShiftNumber(), false);
        } else {
            for (Shift shift : Shift.getShifts()) {
                if (shift.getNumber() != condition.getShiftNumber()) {
                    availability.get(driver).put(shift.getNumber(), false);
                } else {
                    availability.get(driver).put(shift.getNumber(), true);
                    shifts.get(shift.getNumber()).add(driver);
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
                    availability.get(driver).replace(condition.getShiftNumber(), true);
                }
            }
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean checkAvailability(int driverNumber, int shiftNumber) {
        if (driverNumber != 0) {
            Driver driver = DriverData.getDriver(driverNumber);
            if (availability.get(driver) != null) {
                if (availability.get(driver).get(shiftNumber) && shifts.containsKey(shiftNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addShift(int shiftNumber, Driver driver) {
        shifts.get(shiftNumber).add(driver);
    }

    public Map<Integer, List<Driver>> getShifts() {
        return this.shifts;
    }

    private String generateShortCut() {
        switch (date.getDayOfWeek().toString()) {
            case "MONDAY":
                return "pn";
            case "TUESDAY":
                return "wt";
            case "WEDNESDAY":
                return "śr";
            case "THURSDAY":
                return "cz";
            case "FRIDAY":
                return "pt";
            case "SATURDAY":
                return "sb";
            case "SUNDAY":
                return "nd";
            default:
                return null;
        }
    }

    public boolean isNextDayHoliday() {
        return nextDayHoliday;
    }

    @Override
    public String toString() {
        return date.getDayOfMonth() + " " + generateShortCut();
    }
}