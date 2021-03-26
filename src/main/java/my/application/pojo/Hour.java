package my.application.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hour {
    private Map<Driver, Integer> hours;
    private Map<Driver, Integer> saintHours;
    private List<Driver> drivers;

    Hour() {
        this.hours = new HashMap<>();
        this.saintHours = new HashMap<>();
        this.drivers = new ArrayList<>();
        for (Driver driver : DriverData.getDrivers()) {
            hours.put(driver, 0);
            saintHours.put(driver, 0);
        }
        generateRandomOrder();
    }

    private void generateRandomOrder() {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 1; i <= DriverData.getMaxNumber(); i++) {
            if(DriverData.contains(i)) {
                randomNumbers.add(i);
            }
        }
        int numberIndex;
        while (randomNumbers.size() > 0) {
            numberIndex = (int) (Math.random() * randomNumbers.size());
            drivers.add(DriverData.getDriver( randomNumbers.get(numberIndex)));
            randomNumbers.remove(numberIndex);
        }
    }

    int addHours(int driverNumber, int shiftNumber, boolean saintDay) {
        Driver driver = DriverData.getDriver(driverNumber);
        int hoursToAdd;
        if (shiftNumber == 1 || shiftNumber == 2 || shiftNumber == 5 || shiftNumber == 7 || shiftNumber == 8 || shiftNumber == 9 || shiftNumber == 10) {
            if (hours.containsKey(driver)) {
                hours.put(driver, hours.get(driver) + 8);
                if(saintDay) {
                    saintHours.put(driver, saintHours.get(driver) + 8);
                }
            } else {
                hours.put(driver, 8);
                if(saintDay) {
                    saintHours.put(driver, saintHours.get(driver) + 8);
                }
            }
            hoursToAdd = 8;
        } else if (shiftNumber == 3 || shiftNumber == 6) {
            if (hours.containsKey(driver)) {
                hours.put(driver, hours.get(driver) + 4);
                if(saintDay) {
                    saintHours.put(driver, saintHours.get(driver) + 4);
                }
            } else {
                hours.put(driver, 4);
                if(saintDay) {
                    saintHours.put(driver, saintHours.get(driver) + 4);
                }
            }
            hoursToAdd = 4;
        } else {
            if (hours.containsKey(driver)) {
                hours.put(driver, hours.get(driver) + 16);
                if(saintDay) {
                    saintHours.put(driver, saintHours.get(driver) + 16);
                }
            } else {
                hours.put(driver, 16);
                if(saintDay) {
                    saintHours.put(driver, saintHours.get(driver) + 16);
                }
            }
            hoursToAdd = 16;
        }
        if (!drivers.contains(driver)) {
            drivers.add(driver);
        }
        return hoursToAdd;
    }

    int getMaxSaintHours(){
        List<Driver> drivers = DriverData.getDrivers();
        int max = 0;
        for (int i = 0; i < drivers.size(); i++) {
            if(saintHours.get(drivers.get(i)) > max){
                max = saintHours.get(drivers.get(i));
            }
        }
        return max;
    }

    void initializeHours(Driver driver){
        hours.put(driver, 0);
        saintHours.put(driver, 0);
        drivers.add(driver);
    }

    public Map<Driver, Integer> getHours() {
        return hours;
    }

    Map<Driver, Integer> getSaintHours() {
        return saintHours;
    }

    List<Driver> getDrivers() {
        return drivers;
    }
}
