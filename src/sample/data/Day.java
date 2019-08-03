package sample.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day {
    private LocalDate date;
    private Map<Shift, List<Driver>> shifts;
    private Map<Driver, List<Condition>> conditions;
    private Map<Driver, Map<Shift, Boolean>> availability;

    public Day(LocalDate date) {
        this.date = date;
        shifts = new HashMap<>();
        for (Shift shift : Shift.getShifts()) {
            shifts.put(shift, new ArrayList<>());
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
        test();
    }

    public void setCondition(Driver driver, Condition condition) {
        if(conditions.containsKey(driver)) {
            this.conditions.get(driver).add(condition);
        } else {
            this.conditions.put(driver, new ArrayList<>());
            this.conditions.get(driver).add(condition);
        }
        if(!condition.isPossibleShift()) {
            availability.get(driver).put(Shift.getShift(condition.getShiftNumber()), false);
        } else {
            for (Shift shift : Shift.getShifts()) {
                if(shift.getNumber() != condition.getShiftNumber()){
                    availability.get(driver).put(shift, false);
                }
            }
        }
    }

    public void removeCondition(Condition condition){
        Driver driver = DriverData.getDriver(condition.getDriverNumber());
        if(conditions.containsKey(driver)){
            for (Condition driverCondition : conditions.get(driver)) {
                if(driverCondition == condition){
                    conditions.get(driver).remove(driverCondition);
                    availability.get(driver).replace(Shift.getShift(condition.getShiftNumber()), true);
                }
            }
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public String dailyWorkSchedule(){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= shifts.size(); i++) {
            sb.append("[" + Shift.getShift(i).getNumber() + "]  ");
            List<Driver> drivers = shifts.get(Shift.getShift(i));
            for (int j = 0; j < drivers.size(); j++) {
                if(j < drivers.size() - 1) {
                    sb.append(drivers.get(j).getNumber() + ",");
                } else {
                    sb.append(drivers.get(j).getNumber() + "  ");
                }
            }
        }
        return sb.toString();
    }

    public void addShift(int shiftNumber, Driver driver){
        shifts.get(Shift.getShift(shiftNumber)).add(driver);
    }

    public void test(){
        for (Shift shift : Shift.getShifts()) {
            for(int i = 0; i < 3; i++) {
                addShift(shift.getNumber(), DriverData.getDriver(generate()));
            }
        }
    }

    private int generate(){
        return (int) (Math.random() * DriverData.getDrivers().size()) + 1;
    }
}