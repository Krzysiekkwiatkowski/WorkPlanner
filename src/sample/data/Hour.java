package sample.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hour {
    private Map<Driver, Integer> hours;
    private ArrayList<Driver> drivers;

    public Hour() {
        this.hours = new HashMap<>();
        this.drivers = new ArrayList<>();
        for (Driver driver : DriverData.getDrivers()) {
            hours.put(driver, 0);
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

    public void addHours(int driverNumber, int shiftNumber) {
        Driver driver = DriverData.getDriver(driverNumber);
        if (shiftNumber == 1 || shiftNumber == 2 || shiftNumber == 5 || shiftNumber == 7 || shiftNumber == 8 || shiftNumber == 9 || shiftNumber == 10) {
            if (hours.containsKey(driver)) {
                hours.put(driver, hours.get(driver) + 8);
            } else {
                hours.put(driver, 8);
            }
        } else if (shiftNumber == 3 || shiftNumber == 6) {
            if (hours.containsKey(driver)) {
                hours.put(driver, hours.get(driver) + 4);
            } else {
                hours.put(driver, 4);
            }
        } else {
            if (hours.containsKey(driver)) {
                hours.put(driver, hours.get(driver) + 16);
            } else {
                hours.put(driver, 16);
            }
        }
        if (!drivers.contains(driver)) {
            drivers.add(driver);
        }
    }

    public void sortByHours() {
        if (drivers.size() > 1) {
            for (int i = 0; i < drivers.size() - 1; i++) {
                if (hours.get(drivers.get(i)) > hours.get(drivers.get(i + 1))) {
                    Driver driver = drivers.get(i);
                    drivers.set(i, drivers.get(i + 1));
                    drivers.set(i + 1, driver);
                    i = 0;
                }
            }
        }
    }

    public Map<Driver, Integer> getHours() {
        return hours;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }
}
