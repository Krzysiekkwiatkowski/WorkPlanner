package com.programs.data;

import com.programs.Controller;
import com.programs.HolidayController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class WorkSchedule {
    private LocalDate date;
    private String month;
    private Controller controller;
    private List<Day> days;
    private List<Day> saintDays;
    private Map<Driver, List<Condition>> conditions;
    private ExcelSaving excel = new ExcelSaving(this);
    private ExcelLoading excelLoad;
    private Hour hour;
    private Map<Integer, Integer> firstShift;
    private Map<Integer, Integer> secondShift;
    private Map<Integer, Integer> thirdShift;
    private Map<Integer, Integer> fourthShift;
    private Map<Integer, Integer> fifthShift;
    private Map<Integer, Integer> sixthShift;
    private Map<Integer, Integer> seventhShift;
    private Map<Integer, Integer> eighthShift;
    private Map<Integer, Integer> ninthShift;
    private Map<Integer, Integer> tenthShift;
    private Map<Integer, Integer> spareDistribution;
    private ShiftManager manager;
    private Map<Driver, Boolean> driversToUpdate;
    private List<LastDays> lastDays = null;
    private String freeTime = "2,5 dnia";

    public WorkSchedule(Controller controller) {
        DriverData.loadDrivers();
        this.date = LocalDate.now();
        days = new ArrayList<>();
        saintDays = new ArrayList<>();
        excelLoad = new ExcelLoading(this, ("Grafik" + getMonth(date.getMonth().toString()) + ".xls"));
        hour = new Hour();
        firstShift = new HashMap<>();
        secondShift = new HashMap<>();
        thirdShift = new HashMap<>();
        fourthShift = new HashMap<>();
        fifthShift = new HashMap<>();
        sixthShift = new HashMap<>();
        seventhShift = new HashMap<>();
        eighthShift = new HashMap<>();
        ninthShift = new HashMap<>();
        tenthShift = new HashMap<>();
        spareDistribution = new HashMap<>();
        manager = new ShiftManager();
        for (Driver driver : hour.getDrivers()) {
            for (int i = 1; i < 11; i++) {
                initializeMaps(i, driver);
            }
        }
        this.controller = controller;
        this.month = getMonth(date.getMonth().plus(1).toString());
        conditions = new HashMap<>();
        for (Driver driver : DriverData.getDrivers()) {
            conditions.put(driver, new ArrayList<>());
        }
        driversToUpdate = new HashMap<>();
    }

    private void initializeMaps(int shiftNumber, Driver driver) {
        Map<Integer, Integer> map = getMap(shiftNumber);
        map.put(driver.getNumber(), 0);
    }

    public String getMonth(String month) {
        switch (month) {
            case "JANUARY":
                return "Styczen";
            case "FEBRUARY":
                return "Luty";
            case "MARCH":
                return "Marzec";
            case "APRIL":
                return "Kwiecien";
            case "MAY":
                return "Maj";
            case "JUNE":
                return "Czerwiec";
            case "JULY":
                return "Lipiec";
            case "AUGUST":
                return "Sierpien";
            case "SEPTEMBER":
                return "Wrzesien";
            case "OCTOBER":
                return "Pazdziernik";
            case "NOVEMBER":
                return "Listopad";
            case "DECEMBER":
                return "Grudzien";
        }
        return null;
    }

    protected void addHours(int shiftNumber, int driverNumber, boolean saintDay) {
        int hours = hour.addHours(driverNumber, shiftNumber, saintDay);
        Map<Integer, Integer> map = getMap(shiftNumber);
        map.put(driverNumber, (map.get(driverNumber) + hours));
    }

    protected void setAvailability(Driver driver, int shiftNumber, Day day) {
        List<Integer> availability;
        List<Integer> availabilityNextDay;
        List<Integer> availabilitySecondDay;
        switch (shiftNumber) {
            case 1:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
                day.setDriverAvailability(driver, availability);
                break;
            case 2:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                day.setDriverAvailability(driver, availability);
                break;
            case 3:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                day.setDriverAvailability(driver, availability);
                break;
            case 4:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                day.setDriverAvailability(driver, availability);
                break;
            case 5:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                day.setDriverAvailability(driver, availability);
                break;
            case 6:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                day.setDriverAvailability(driver, availability);
                break;
            case 7:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                day.setDriverAvailability(driver, availability);
                availabilityNextDay = Arrays.asList(1);
                Day nextDay = getNextDay(day, 1);
                if (nextDay != null) {
                    nextDay.setDriverAvailability(driver, availabilityNextDay);
                }
                break;
            case 8:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                day.setDriverAvailability(driver, availability);
                availabilityNextDay = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                nextDay = getNextDay(day, 1);
                if (nextDay != null) {
                    nextDay.setDriverAvailability(driver, availabilityNextDay);
                }
                break;
            case 9:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                day.setDriverAvailability(driver, availability);
                availabilityNextDay = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                nextDay = getNextDay(day, 1);
                if (nextDay != null) {
                    nextDay.setDriverAvailability(driver, availabilityNextDay);
                }
                break;
            case 10:
                availability = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                day.setDriverAvailability(driver, availability);
                availabilityNextDay = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                nextDay = getNextDay(day, 1);
                if (nextDay != null) {
                    nextDay.setDriverAvailability(driver, availabilityNextDay);
                }
                if (freeTime.equals("2,5 dnia")) {
                    availabilitySecondDay = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                    nextDay = getNextDay(nextDay, 1);
                    if (nextDay != null) {
                        nextDay.setDriverAvailability(driver, availabilitySecondDay);
                    }
                    List<Integer> availabilityThirdNextDay = Arrays.asList(1, 2, 3, 4);
                    if (nextDay != null) {
                        nextDay = getNextDay(nextDay, 1);
                    }
                    if (nextDay != null) {
                        nextDay.setDriverAvailability(driver, availabilityThirdNextDay);
                    }
                } else {
                    availabilitySecondDay = Arrays.asList(1, 2, 3, 4);
                    nextDay = getNextDay(nextDay, 1);
                    if (nextDay != null) {
                        nextDay.setDriverAvailability(driver, availabilitySecondDay);
                    }
                }
                break;
        }
    }

    private void updatePreviousDaysShifts() {
        Day actualDay;
        Map<String, String> actualMap;
        for (int i = 0; i < lastDays.size(); i++) {
            actualDay = getDayByDate(lastDays.get(i).getDate());
            actualMap = lastDays.get(i).getShifts();
            for (String hours : actualMap.keySet()) {
                updateShift(hours, actualMap.get(hours), actualDay);
            }
        }
    }

    private void updateShift(String hours, String input, Day day) {
        if (!input.equals("-")) {
            String[] driversNumbers = input.split(",");
            for (int i = 0; i < driversNumbers.length; i++) {
                int driverNumber = 0;
                try {
                    driverNumber = Integer.parseInt(driversNumbers[i].trim());
                } catch (NumberFormatException e) {
                    continue;
                }
                if (driverNumber != 0) {
                    if (i == 0) {
                        if (day.getShifts().get(Shift.getShift(hours).getNumber()) != null) {
                            day.getShifts().get(Shift.getShift(hours).getNumber()).clear();
                        } else {
                            day.getShifts().put(Shift.getShift(hours).getNumber(), new ArrayList<>());
                        }
                    }
                    day.getShifts().get(Shift.getShift(hours).getNumber()).add(DriverData.getDriver(driverNumber));
                }
            }
        }
    }

    private Day getDayByDate(LocalDate date) {
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).getDate().toString().equals(date.toString())) {
                return days.get(i);
            }
        }
        return null;
    }

    private Day getNextDay(Day day, int plusDay) {
        int index = days.indexOf(day);
        if (index + plusDay <= days.size() - 1) {
            return days.get(index + plusDay);
        }
        return null;
    }

    private Day getPreviousDay(Day day, int minusDay) {
        int index = days.indexOf(day);
        if (index - minusDay > -1) {
            return days.get(index - minusDay);
        }
        return null;
    }

    public void generate() {
        for (Day day : days) {
            manageDay(day);
        }
    }

    private void manageDay(Day day) {
        tryDistribute(day);
//        if(day.getDate().toString().equals("2019-12-09") || day.getDate().toString().equals("2019-12-16") || day.getDate().toString().equals("2019-12-23") || day.getDate().toString().equals("2019-12-30")){
//            for (Integer number : spareDistribution.keySet()) {
//                System.out.println(number + " ");
//            }
//            System.out.println();
//        }
        manageRequiredShifts(day);
        manageOptionalShifts(day);
        addSpareShifts(day);
        spareDistribution.clear();
    }

    private void addSpareShifts(Day day) {
        for (Integer driverNumber : spareDistribution.keySet()) {
            addShift(spareDistribution.get(driverNumber), driverNumber, day);
        }
    }

    private void manageRequiredShifts(Day day) {
        List<Integer> shifts = manager.getRequired(day);
        for (int i = 0; i < shifts.size(); i++) {
            if (shifts.get(i) != 10) {
                manageTypicalShift(shifts.get(i), day, verifyDrivers(getSortedList(getMap(shifts.get(i)), day)));
            } else {
                manageShiftTenth(day);
            }
        }
    }

    private void manageOptionalShifts(Day day) {
        List<Integer> shifts = manager.getOptional(day);
        int max = getMaxDrivers(shifts, day);
        for (int i = 0; i < max; i++) {
            for (Integer shiftNumber : shifts) {
                manageOptionalShifts(shiftNumber, day);
            }
        }
    }

    private void manageOptionalShifts(int shiftNumber, Day day) {
        List<Driver> drivers = verifyDrivers(getSortedList(getMap(shiftNumber), day));
        for (int i = 0; i < drivers.size(); i++) {
            if ((day.getShifts().get(shiftNumber).size() + getDisposedValue(shiftNumber)) < checkNumberOfDrivers(day, shiftNumber) && day.checkAvailability(drivers.get(i).getNumber(), shiftNumber) && canBeDistributed(drivers.get(i).getNumber()) && checkFutureAvailability(DriverData.getDriver(i), shiftNumber, day)) {
                addShift(shiftNumber, drivers.get(i).getNumber(), day);
                break;
            }
        }
    }

    private void manageShiftEighth(Day day) {
        Day previousDay;
        if (freeTime.equals("2,5 dnia")) {
            previousDay = getPreviousDay(day, 3);
        } else {
            previousDay = getPreviousDay(day, 2);
        }
        if (day.getDate().getMonth() != LocalDate.now().getMonth()) {
            List<Driver> drivers = verifyDrivers(previousDay.getShifts().get(10));
            List<Driver> sortedDrivers = getSortedList(getMap(8), day);
            for (int i = 0; i < sortedDrivers.size(); i++) {
                if (drivers.contains(sortedDrivers.get(i)) && (day.getShifts().get(8).size() < checkNumberOfDrivers(day, 8)) && day.checkAvailability(sortedDrivers.get(i).getNumber(), 8) && canBeDistributed(sortedDrivers.get(i).getNumber()) && checkFutureAvailability(sortedDrivers.get(i), 8, day)) {
                    addShift(8, sortedDrivers.get(i).getNumber(), day);
                    break;
                }
            }
        }
    }

    private void manageShiftTenth(Day day) {
        List<Driver> drivers = verifyDrivers(day.getShifts().get(1));
        for (int i = 0; i < drivers.size(); i++) {
            if ((day.getShifts().get(10).size() < checkNumberOfDrivers(day, 10)) && (day.checkAvailability(drivers.get(i).getNumber(), 10)) && (drivers.get(i).getNumber() != 15) && checkFutureAvailability(drivers.get(i), 10, day)) {
                addShift(10, drivers.get(i).getNumber(), day);
            }
        }
        if (day.getShifts().get(10).size() < checkNumberOfDrivers(day, 10)) {
            drivers = verifyDrivers(getSortedList(getMap(10), day));
            for (int i = 0; i < drivers.size(); i++) {
                if ((day.getShifts().get(10).size() < checkNumberOfDrivers(day, 10)) && (day.checkAvailability(drivers.get(i).getNumber(), 10)) && checkFutureAvailability(drivers.get(i), 10, day)) {
                    addShift(10, drivers.get(i).getNumber(), day);
                }
            }
        }
    }

    private void tryDistribute(Day day) {
        manageShiftEighth(day);
        int[] otherShifts;
        if (day.isNextDayHoliday()) {
            otherShifts = new int[3];
            otherShifts[0] = 5;
            otherShifts[1] = 7;
            otherShifts[2] = 9;
        } else {
            otherShifts = new int[2];
            otherShifts[0] = 5;
            otherShifts[1] = 7;
        }
        Day previousDay;
        if(freeTime.equals("2,5 dnia")) {
             previousDay = getPreviousDay(day, 3);
        } else {
            previousDay = getPreviousDay(day, 2);
        }
        if (day.getDate().getMonth() != LocalDate.now().getMonth()) {
            List<Driver> drivers = new ArrayList<>(verifyDrivers(previousDay.getShifts().get(10)));
            if(day.getShifts().containsKey(8)) {
                for (Driver driver : day.getShifts().get(8)) {
                    drivers.remove(driver);
                }
            }
            Random random = new Random();
            List<Integer> used = new ArrayList<>();
            int shiftNumber;
            while (used.size() < otherShifts.length) {
                shiftNumber = otherShifts[random.nextInt(otherShifts.length)];
                if (!used.contains(shiftNumber)) {
                    List<Driver> sortedDrivers = sortLimited(shiftNumber, drivers);
                    for (int i = 0; i < sortedDrivers.size(); i++) {
                        if ((day.getShifts().get(shiftNumber).size() < checkNumberOfDrivers(day, shiftNumber)) && day.checkAvailability(sortedDrivers.get(i).getNumber(), shiftNumber) && checkFutureAvailability(sortedDrivers.get(i), shiftNumber, day)) {
                            spareDistribution.put(sortedDrivers.get(i).getNumber(), shiftNumber);
                            used.add(shiftNumber);
                            drivers.remove(sortedDrivers.get(i));
                            break;
                        }
                        if (i == (sortedDrivers.size() - 1) && (!used.contains(shiftNumber))) {
                            used.add(shiftNumber);
                        }
                    }
                    if (sortedDrivers.size() == 0) {
                        break;
                    }
                }
            }
        }
    }

    private List<Driver> verifyDrivers(List<Driver> drivers) {
        if (DriverData.getDrivers().containsAll(drivers)) {
            return drivers;
        } else {
            List<Driver> actual = new ArrayList<>();
            for (Driver driver : drivers) {
                if (DriverData.getDrivers().contains(driver)) {
                    actual.add(driver);
                }
            }
            return actual;
        }
    }

    private void verifyMap(Map<Integer, Integer> map, int shiftNumber) {
        Map<Integer, Integer> verifiedMap = new HashMap<>();
        for (Driver driver : DriverData.getDrivers()) {
            if (map.containsKey(driver.getNumber())) {
                verifiedMap.put(driver.getNumber(), map.get(driver.getNumber()));
            } else {
                verifiedMap.put(driver.getNumber(), 0);
            }
        }
        switch (shiftNumber) {
            case 1:
                firstShift = verifiedMap;
                break;
            case 2:
                secondShift = verifiedMap;
                break;
            case 3:
                thirdShift = verifiedMap;
                break;
            case 4:
                fourthShift = verifiedMap;
                break;
            case 5:
                fifthShift = verifiedMap;
                break;
            case 6:
                sixthShift = verifiedMap;
                break;
            case 7:
                seventhShift = verifiedMap;
                break;
            case 8:
                eighthShift = verifiedMap;
                break;
            case 9:
                ninthShift = verifiedMap;
                break;
            case 10:
                tenthShift = verifiedMap;
                break;
            default:
                map = null;
                break;
        }
    }

    private void verifyMaps() {
        for (int i = 1; i < 11; i++) {
            verifyMap(getMap(i), i);
        }
    }

    private boolean canBeDistributed(int driverNumber) {
        if (spareDistribution.containsKey(driverNumber)) {
            return false;
        }
        return true;
    }

    private List<Driver> sortLimited(int shiftNumber, List<Driver> drivers) {
        List<Driver> sorted = new ArrayList<>();
        Map<Integer, Integer> map = getMap(shiftNumber);
        int shift = 0;
        while (sorted.size() < drivers.size()) {
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i) != null) {
                    if (map.get(drivers.get(i).getNumber()) == shift) {
                        sorted.add(drivers.get(i));
                    }
                } else {
                    drivers.remove(i);
                }
            }
            shift++;
        }
        return sorted;
    }

    private int getMaxDrivers(List<Integer> shifts, Day day) {
        int max = 0;
        for (Integer shiftNumber : shifts) {
            int actual = checkNumberOfDrivers(day, shiftNumber);
            if (actual > max) {
                max = actual;
            }
        }
        return max;
    }

    // Methods added for testing purposes
    private void showDistribution() {
        List<Driver> drivers = DriverData.getDrivers();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < drivers.size(); i++) {
            sb.append("Driver " + drivers.get(i).getNumber() + "\t");
            for (int j = 1; j <= Shift.getShifts().size(); j++) {
                sb.append(j + " : " + getMap(j).get(drivers.get(i).getNumber()) + "\t");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private List<Driver> randomOrder(List<Driver> drivers) {
        Random random = new Random();
        if (drivers.size() > 0) {
            List<Driver> randomList = new ArrayList<>();
            List<Integer> numbers = new ArrayList<>();
            while (numbers.size() < drivers.size()) {
                int number = random.nextInt(drivers.size());
                if (!numbers.contains(number)) {
                    randomList.add(drivers.get(number));
                    numbers.add(number);
                }
            }
            return randomList;
        } else {
            return drivers;
        }
    }

    private Map<Integer, Integer> getMap(int shiftNumber) {
        Map<Integer, Integer> map;
        switch (shiftNumber) {
            case 1:
                map = firstShift;
                break;
            case 2:
                map = secondShift;
                break;
            case 3:
                map = thirdShift;
                break;
            case 4:
                map = fourthShift;
                break;
            case 5:
                map = fifthShift;
                break;
            case 6:
                map = sixthShift;
                break;
            case 7:
                map = seventhShift;
                break;
            case 8:
                map = eighthShift;
                break;
            case 9:
                map = ninthShift;
                break;
            case 10:
                map = tenthShift;
                break;
            default:
                map = null;
                break;
        }
        return map;
    }

    private List<Driver> getSortedList(Map<Integer, Integer> map, Day day) {
        Day nextDay = getNextDay(day, 1);
        if (saintDays.contains(nextDay)) {
            return getSaint(map);
        } else {
            return getTypical(map);
        }
    }

    private List<Driver> getTypical(Map<Integer, Integer> map) {
        List<Driver> sortedList = new ArrayList<>();
        List<Driver> actual = new ArrayList<>();
        int numberOfShifts = 0;
        while (sortedList.size() < map.size()) {
            for (Integer driverNumber : map.keySet()) {
                if (map.get(driverNumber) == numberOfShifts) {
                    actual.add(DriverData.getDriver(driverNumber));
                }
            }
            sortedList.addAll(randomOrder(actual));
            numberOfShifts++;
            actual.clear();
        }
        return sortedList;
    }

    private List<Driver> getSaint(Map<Integer, Integer> map) {
        List<Driver> drivers = DriverData.getDrivers();
        Map<Driver, Integer> saintHoursList = hour.getSaintHours();
        List<Driver> sortedList = new ArrayList<>();
        List<Driver> actual = new ArrayList<>();
        List<Driver> actualSorted = new ArrayList<>();
        int shifts = 0;
        int saintHours = hour.getMaxSaintHours();
        while (sortedList.size() < map.size()) {
            for (int i = 0; i < drivers.size(); i++) {
                if (saintHoursList.get(drivers.get(i)) == saintHours) {
                    actual.add(drivers.get(i));
                }
            }
            while (actualSorted.size() < actual.size()) {
                for (int i = 0; i < actual.size(); i++) {
                    if (map.get(actual.get(i).getNumber()) == shifts) {
                        actualSorted.add(actual.get(i));
                    }
                }
                shifts++;
            }
            sortedList.addAll(actualSorted);
            actual.clear();
            actualSorted.clear();
            shifts = 0;
            saintHours -= 4;
        }
        return sortedList;
    }

    protected void addShift(int shiftNumber, int driverNumber, Day day) {
        day.addShift(shiftNumber, DriverData.getDriver(driverNumber));
        if (day.getDate().getMonth() != LocalDate.now().getMonth()) {
            addHours(shiftNumber, driverNumber, saintDays.contains(day));
        }
        setAvailability(DriverData.getDriver(driverNumber), shiftNumber, day);
    }

    private boolean checkFutureAvailability(Driver driver, int shiftNumber, Day day) {
        Map<Integer, List<Integer>> checkMap = new HashMap<>();
        int dayIndex;
        switch (shiftNumber) {
            case 1:
                checkMap.put(days.indexOf(day), Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9));
                return checkFutureAvailability(driver, checkMap);
            case 2:
                checkMap.put(days.indexOf(day), Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10));
                return checkFutureAvailability(driver, checkMap);
            case 3:
                checkMap.put(days.indexOf(day), Arrays.asList(4, 5, 6, 7, 8, 9, 10));
                return checkFutureAvailability(driver, checkMap);
            case 4:
                checkMap.put(days.indexOf(day), Arrays.asList(5, 6, 7, 8, 9, 10));
                return checkFutureAvailability(driver, checkMap);
            case 5:
                checkMap.put(days.indexOf(day), Arrays.asList(6, 7, 8, 9, 10));
                return checkFutureAvailability(driver, checkMap);
            case 6:
                checkMap.put(days.indexOf(day), Arrays.asList(7, 8, 9, 10));
                return checkFutureAvailability(driver, checkMap);
            case 7:
                dayIndex = days.indexOf(day);
                checkMap.put(dayIndex, Arrays.asList(8, 9, 10));
                if ((dayIndex + 1) < days.size()) {
                    checkMap.put((dayIndex + 1), Arrays.asList(1));
                }
                return checkFutureAvailability(driver, checkMap);
            case 8:
                dayIndex = days.indexOf(day);
                checkMap.put(dayIndex, Arrays.asList(9, 10));
                if ((dayIndex + 1) < days.size()) {
                    checkMap.put(dayIndex + 1, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
                }
                return checkFutureAvailability(driver, checkMap);
            case 9:
                dayIndex = days.indexOf(day);
                checkMap.put(dayIndex, Arrays.asList(10));
                if ((dayIndex + 1) < days.size()) {
                    checkMap.put(dayIndex + 1, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
                }
                return checkFutureAvailability(driver, checkMap);
            case 10:
                dayIndex = days.indexOf(day) + 1;
                if (dayIndex < days.size()) {
                    checkMap.put(dayIndex, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
                    dayIndex++;
                }
                if (freeTime.equals("2,5 dnia")) {
                    if (dayIndex < days.size()) {
                        checkMap.put(dayIndex, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
                        dayIndex++;
                    }
                    if (dayIndex < days.size()) {
                        checkMap.put(dayIndex, Arrays.asList(1, 2, 3, 4));
                    }
                } else {
                    if (dayIndex < days.size()) {
                        checkMap.put(dayIndex, Arrays.asList(1, 2, 3, 4));
                    }

                }
                return checkFutureAvailability(driver, checkMap);
        }
        return false;
    }

    private boolean checkFutureAvailability(Driver driver, Map<Integer, List<Integer>> shiftsToCheck) {
        for (Integer dayIndex : shiftsToCheck.keySet()) {
            for (int j = 0; j < shiftsToCheck.get(dayIndex).size(); j++) {
                if (days.get(dayIndex).getShifts().get(shiftsToCheck.get(dayIndex).get(j)) != null) {
                    if (days.get(dayIndex).getShifts().get(shiftsToCheck.get(dayIndex).get(j)).contains(driver)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void manageTypicalShift(int shiftNumber, Day day, List<Driver> drivers) {
        for (int i = 0; i < drivers.size(); i++) {
            if (day.checkAvailability(drivers.get(i).getNumber(), shiftNumber) && ((day.getShifts().get(shiftNumber).size() + getDisposedValue(shiftNumber)) < checkNumberOfDrivers(day, shiftNumber)) && checkUniqueness(shiftNumber, drivers.get(i).getNumber(), day) && (!day.getShifts().get(shiftNumber).contains(drivers.get(i))) && (!spareDistribution.containsKey(drivers.get(i).getNumber())) && checkFutureAvailability(drivers.get(i), shiftNumber, day)) {
                addShift(shiftNumber, drivers.get(i).getNumber(), day);
            }
        }
    }

    private int getDisposedValue(int shiftNumber) {
        if (spareDistribution.containsValue(shiftNumber)) {
            return 1;
        }
        return 0;
    }

    private boolean checkUniqueness(int shiftNumber, int driverNumber, Day day) {
        int dayBack = 0;
        switch (day.getDate().getDayOfWeek().getValue()) {
            case 4:
                dayBack = 1;
                break;
            case 5:
                dayBack = 2;
                break;
            case 6:
                dayBack = 3;
                break;
        }
        if (dayBack == 0 || shiftNumber != 1) {
            return true;
        }
        Day previousDay;
        for (int i = 0; i < dayBack; i++) {
            previousDay = getPreviousDay(day, (i + 1));
            if (previousDay != null) {
                if (previousDay.getShifts().get(1).contains(DriverData.getDriver(driverNumber))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void initializeDays() {
        List<LocalDate> holidays = HolidayController.getHolidays();
        for (int i = 0; i < date.getMonth().plus(1).length(LocalDate.now().isLeapYear()); i++) {
            LocalDate actualDate = date.plusMonths(1).withDayOfMonth(i + 1);
            Day previousDay = null;
            if (days.size() > 0) {
                previousDay = days.get(days.size() - 1);
            }
            Day day;
            if (previousDay != null) {
                if (previousDay.isNextDayHoliday()) {
                    day = new Day(actualDate, holidays.contains(actualDate), true, this);
                } else {
                    day = new Day(actualDate, holidays.contains(actualDate), false, this);
                }
            } else {
                day = new Day(actualDate, holidays.contains(actualDate), false, this);
            }
            days.add(day);
            Day saintDayCheck = getPreviousDay(day, 1);
            if (saintDayCheck != null) {
                if (holidays.contains(saintDayCheck.getDate())) {
                    saintDays.add(day);
                }
            }
            if (day.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
                saintDays.add(day);
            }
        }
        LocalDate actualDate = date.plusMonths(2).withDayOfMonth(1);
        Day previousDay = getPreviousDay(days.get(days.size() - 1), 1);
        Day day;
        if (previousDay != null) {
            if (previousDay.isNextDayHoliday()) {
                day = new Day(actualDate, holidays.contains(actualDate), true, this);
            } else {
                day = new Day(actualDate, holidays.contains(actualDate), false, this);
            }
        } else {
            day = new Day(actualDate, holidays.contains(actualDate), false, this);
        }
        days.add(day);
        Day saintDayCheck = getPreviousDay(day, 1);
        if (saintDayCheck != null) {
            if (holidays.contains(saintDayCheck.getDate())) {
                saintDays.add(day);
            }
        }
        if (day.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            saintDays.add(day);
        }
    }

    private int checkNumberOfDrivers(Day day, int shiftNumber) {
        return manager.checkNumberOfDrivers(day, shiftNumber);
    }

    public void clearConditions() {
        for (Driver driver : conditions.keySet()) {
            conditions.get(driver).clear();
        }
    }

    public void addCondition(Driver driver, Condition condition) {
        if (driver != null && condition != null) {
            if (conditions.containsKey(driver)) {
                conditions.get(driver).add(condition);
            } else {
                conditions.get(driver).add(condition);
            }
        }
    }

    public void generateWorkSchedule() {
        long millis = System.currentTimeMillis();
        getPreviousDays();
        if (lastDays != null) {
            updatePreviousDaysShifts();
        }
        initializeDays();
        updateDriversList();
        verifyMaps();
        setPreviousDaysAvailability();
        setConditions();
        generate();
        showHours();
        removePreviousDays();
        removeNextDay();
        saveWorkSchedule();
        showDistribution();
        System.out.println("Time to execute program = " + (System.currentTimeMillis() - millis));
    }

    private void setPreviousDaysAvailability() {
        Day day;
        List<Driver> drivers;
        for (int i = 0; i < excelLoad.getPreviousDays().size(); i++) {
            day = days.get(i);
            for (Integer shiftNumber : day.getShifts().keySet()) {
                if (day.getShifts().containsKey(shiftNumber)) {
                    drivers = day.getShifts().get(shiftNumber);
                    for (Driver driver : drivers) {
                        setAvailability(driver, shiftNumber, day);
                    }
                }
            }
        }
    }

    private void removePreviousDays() {
        days.removeAll(excelLoad.getPreviousDays());
    }

    private void removeNextDay() {
        days.remove(days.size() - 1);
    }

    private void getPreviousDays() {
        this.days.addAll(excelLoad.getPreviousDays());
    }

    public void showHours() {
        StringBuilder sb = new StringBuilder();
        for (Driver driver : DriverData.getDrivers()) {
            sb.append("Kierowca " + driver.getNumber() + " ma zagrafikowanych " + hour.getHours().get(driver) + " godzin, w tym " + hour.getSaintHours().get(driver) + " w święta\n\n");
        }
        this.controller.getDisplaySchedule().setText(sb.toString());
    }

    public Day getDay(LocalDate date) {
        for (Day day : days) {
            if (day.getDate().toString().equals(date.toString())) {
                return day;
            }
        }
        return null;
    }

    private void saveWorkSchedule() {
        excel.saveData();
    }

    public Map<Driver, List<Condition>> getConditions() {
        return conditions;
    }

    public String getMonth() {
        return month;
    }

    public List<Day> getDays() {
        return days;
    }

    public void addDriverToUpdate(Driver driver, boolean deletion) {
        driversToUpdate.put(driver, deletion);
    }

    private void updateDriversList() {
        for (Driver driver : driversToUpdate.keySet()) {
            if (!driversToUpdate.get(driver)) {
                hour.initializeHours(driver);
                for (int i = 1; i < 11; i++) {
                    initializeMaps(i, driver);
                }
            }
            for (Day day : days) {
                if (day.getDate().getMonth() == LocalDate.now().getMonth().plus(1)) {
                    if (driversToUpdate.get(driver)) {
                        day.updateListsDeletion(driver);
                    } else {
                        day.updateListsAddition(driver);
                    }
                }
            }
        }
    }

    public void setConditions() {
        for (Driver driver : conditions.keySet()) {
            for (Condition condition : conditions.get(driver)) {
                int index = getIndexByDate(condition.getDate());
                if (index >= 0) {
                    days.get(index).setCondition(driver, condition);
                }
            }
        }
    }

    private int getIndexByDate(LocalDate date) {
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).getDate().equals(date)) {
                return days.indexOf(days.get(i));
            }
        }
        return -1;
    }

    public void setLastDays(List<LastDays> lastDays) {
        this.lastDays = lastDays;
    }

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }
}