package my.application.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day {
    private WorkSchedule schedule;
    private LocalDate date;
    private boolean nextDayHoliday;
    private boolean holiday;
    private Map<Integer, List<Driver>> shifts;
    private Map<Driver, List<Condition>> conditions;
    private Map<Driver, Map<Integer, Boolean>> availability;

    public Day(LocalDate date, boolean nextDayHoliday, boolean holiday, WorkSchedule schedule) {
        this.schedule = schedule;
        this.date = date;
        this.nextDayHoliday = nextDayHoliday;
        this.holiday = holiday;
        shifts = new HashMap<>();
        initializeLists(date);
    }

    void updateListsDeletion(Driver driver) {
        shifts.keySet().forEach(k -> {
            if(shifts.get(k).contains(driver)){
                shifts.get(driver).remove(driver);
            }
        });
        if (conditions.containsKey(driver)) {
            conditions.remove(driver);
        }
        if (availability.containsKey(driver)) {
            availability.remove(driver);
        }
    }

    void updateListsAddition(Driver driver) {
        initializeAvailability(driver);
    }

    private void initializeLists(LocalDate date) {
        String dayOfWeek = date.getDayOfWeek().toString();
        List<Integer> tempConditions = new ArrayList<>();
        switch (dayOfWeek) {
            case "FRIDAY":
                break;
            case "SATURDAY":
                tempConditions.add(4);
                tempConditions.add(6);
                break;
            case "SUNDAY":
                tempConditions.add(2);
                tempConditions.add(4);
                tempConditions.add(6);
                tempConditions.add(9);
                break;
            default:
                tempConditions.add(9);
                break;
        }
        if (nextDayHoliday) {
            tempConditions = redCardCondition(tempConditions);
        }
        manageShifts(tempConditions);
        conditions = new HashMap<>();
        availability = new HashMap<>();
        DriverData.getDrivers().forEach(d -> initializeAvailability(d));
    }

    private void initializeAvailability(Driver driver) {
        String dayOfWeek = date.getDayOfWeek().toString();
        Map<Integer, Boolean> availabilityMap = new HashMap<>();
        Shift.getShifts().forEach(s -> {
            if (s.getNumber() == 10 && driver.getNumber() == 15) {
                availabilityMap.put(s.getNumber(), false);
            } else {
                if (driver.getNumber() != 15) {
                    availabilityMap.put(s.getNumber(), true);
                } else {
                    if (holiday || dayOfWeek.equals("SUNDAY")) {
                        if (s.getNumber() == 1 || s.getNumber() == 3) {
                            availabilityMap.put(s.getNumber(), true);
                        } else {
                            availabilityMap.put(s.getNumber(), false);
                        }
                    } else {
                        availabilityMap.put(s.getNumber(), true);
                    }
                }
            }
        });
        this.availability.put(driver, availabilityMap);
    }

    public void setDriverAvailability(Driver driver, List<Integer> conditions) {
        Map<Integer, Boolean> map = availability.get(driver);
        if (map != null) {
            map.keySet().forEach(k -> {
                if(conditions.contains(k)){
                    map.replace(k, false);
                }
            });
        }
    }

    private void manageShifts(List<Integer> conditions) {
        if (conditions.size() > 0) {
            Shift.getShifts().forEach(s -> {
                if (!conditions.contains(s.getNumber())) {
                    shifts.put(s.getNumber(), new ArrayList<>());
                }
            });
        } else {
            Shift.getShifts().forEach(s -> shifts.put(s.getNumber(), new ArrayList<>()));
        }
    }

    private List<Integer> redCardCondition(List<Integer> conditions) {
        if (conditions.contains(9)) {
            conditions.remove(conditions.indexOf(9));
        }
        return conditions;
    }

    void setCondition(Driver driver, Condition condition) {
        if (conditions.containsKey(driver)) {
            this.conditions.get(driver).add(condition);
        } else {
            this.conditions.put(driver, new ArrayList<>());
            this.conditions.get(driver).add(condition);
        }
        if (!condition.isPossibleShift()) {
            if(!condition.isAllDayLong()) {
                availability.get(driver).put(condition.getShiftsNumbers().get(0), false);
            } else {
                for (int i = 0; i < condition.getShiftsNumbers().size(); i++) {
                    availability.get(driver).put(condition.getShiftsNumbers().get(i), false);
                }
            }
        } else {
            Shift.getShifts().forEach(s -> {
                if (s.getNumber() != condition.getShiftsNumbers().get(0)) {
                    if (condition.getShiftsNumbers().get(0) == 1) {
                        if (s.getNumber() != 10) {
                            availability.get(driver).put(s.getNumber(), false);
                        }
                    } else {
                        availability.get(driver).put(s.getNumber(), false);
                    }
                } else {
                    schedule.addShift(s.getNumber(), driver.getNumber(), this);
                }
            });
        }
    }

    LocalDate getDate() {
        return date;
    }

    boolean checkAvailability(int driverNumber, int shiftNumber) {
        if (driverNumber != 0) {
            Driver driver = DriverData.getDriver(driverNumber);
            if (availability.get(driver) != null) {
                return (availability.get(driver).get(shiftNumber) && shifts.containsKey(shiftNumber));
            }
        }
        return false;
    }

    void addShift(int shiftNumber, Driver driver) {
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

    boolean isNextDayHoliday() {
        return nextDayHoliday;
    }

    @Override
    public String toString() {
        return date.getDayOfMonth() + " " + generateShortCut();
    }
}