package sample.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hour {
    private Map<Driver, Integer> hours;
    private Map<Driver, Integer> saintHours;
    private List<Driver> drivers;

    public Hour() {
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

    public int addHours(int driverNumber, int shiftNumber, boolean saintDay) {
        Driver driver = DriverData.getDriver(driverNumber);
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
            return 8;
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
            return 4;
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
        }
        if (!drivers.contains(driver)) {
            drivers.add(driver);
        }
        return 16;
    }

    public void sortByHours(boolean typicalDay) {
        if(typicalDay){
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
        } else {
            List<Driver> sortedList = new ArrayList<>();
            int i = 0;
            while(sortedList.size() < drivers.size()){
                List<Driver> drivers = new ArrayList<>();
                for (Driver driver : saintHours.keySet()) {
                    if(saintHours.get(driver) == i){
                        drivers.add(driver);
                    }
                }
                sortedList.addAll(sortList(drivers));
                i += 4;
            }
            drivers = sortedList;
            show(drivers);
        }
    }

    private List<Driver> sortList(List<Driver> drivers){
        List<Driver> sorted = new ArrayList<>(drivers);
        for (int i = 0; i < sorted.size() - 1; i++) {
            if (hours.get(sorted.get(i)) > hours.get(sorted.get(i + 1))) {
                Driver driver = sorted.get(i);
                sorted.set(i, sorted.get(i + 1));
                sorted.set(i + 1, driver);
                i = 0;
            }
        }
        return sorted;
    }

    private void show(List<Driver> drivers){
        for (int j = 0; j < drivers.size(); j++) {
            System.out.println("Driver " + drivers.get(j).getNumber() + " has " + hours.get(drivers.get(j)) + " standard hours and " + saintHours.get(drivers.get(j)) + " saint hours.");
        }
    }

    public Map<Driver, Integer> getHours() {
        return hours;
    }

    public Map<Driver, Integer> getSaintHours() {
        return saintHours;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }
}
