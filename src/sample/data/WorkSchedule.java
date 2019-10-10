package sample.data;

import sample.Controller;
import sample.HolidayController;

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

    public WorkSchedule(Controller controller) {
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
            firstShift.put(driver.getNumber(), 0);
            secondShift.put(driver.getNumber(), 0);
            thirdShift.put(driver.getNumber(), 0);
            fourthShift.put(driver.getNumber(), 0);
            fifthShift.put(driver.getNumber(), 0);
            sixthShift.put(driver.getNumber(), 0);
            seventhShift.put(driver.getNumber(), 0);
            eighthShift.put(driver.getNumber(), 0);
            ninthShift.put(driver.getNumber(), 0);
            tenthShift.put(driver.getNumber(), 0);
        }
        this.controller = controller;
        this.month = getMonth(date.getMonth().plus(1).toString());
        conditions = new HashMap<>();
        for (Driver driver : DriverData.getDrivers()) {
            conditions.put(driver, new ArrayList<>());
        }
    }

    public String getMonth(String month) {
        switch (month) {
            case "JANUARY":
                return "Styczeń";
            case "FEBRUARY":
                return "Luty";
            case "MARCH":
                return "Marzec";
            case "APRIL":
                return "Kwiecień";
            case "MAY":
                return "Maj";
            case "JUNE":
                return "Czerwiec";
            case "JULY":
                return "Lipiec";
            case "AUGUST":
                return "Sierpień";
            case "SEPTEMBER":
                return "Wrzesień";
            case "OCTOBER":
                return "Październik";
            case "NOVEMBER":
                return "Listopad";
            case "DECEMBER":
                return "Grudzień";
        }
        return null;
    }

    private void addHours(int shiftNumber, int driverNumber, boolean saintDay) {
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
                break;
        }
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
        manageRequiredShifts(day);
        manageOptionalShifts(day);
        addSpareShifts(day);
        spareDistribution.clear();
    }

    private void addSpareShifts(Day day){
        for (Integer driverNumber : spareDistribution.keySet()) {
            addShift(spareDistribution.get(driverNumber), driverNumber, day);
        }
    }

    private void manageRequiredShifts(Day day) {
        List<Integer> shifts = manager.getRequired(day);
        for (int i = 0; i < shifts.size(); i++) {
            if (shifts.get(i) != 10) {
                manageTypicalShift(shifts.get(i), day, getSortedList(getMap(shifts.get(i))));
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
        List<Driver> drivers = getSortedList(getMap(shiftNumber));
        for (int i = 0; i < drivers.size(); i++) {
            if ((day.getShifts().get(shiftNumber).size() + getDisposedValue(shiftNumber)) < checkNumberOfDrivers(day, shiftNumber) && day.checkAvailability(drivers.get(i).getNumber(), shiftNumber)) {
                addShift(shiftNumber, drivers.get(i).getNumber(), day);
                break;
            }
        }
    }

    private void manageShiftEighth(Day day) {
        Day previousDay = getPreviousDay(day, 3);
        if (day.getDate().getMonth() != LocalDate.now().getMonth()) {
            List<Driver> drivers = previousDay.getShifts().get(10);
            List<Driver> sortedDrivers = getSortedList(getMap(8));
            for (int i = 0; i < sortedDrivers.size(); i++) {
                if (drivers.contains(sortedDrivers.get(i)) && (day.getShifts().get(8).size() < checkNumberOfDrivers(day, 8)) && day.checkAvailability(sortedDrivers.get(i).getNumber(), 8)) {
                    addShift(8, sortedDrivers.get(i).getNumber(), day);
                    break;
                }
            }
        }
    }

    private void manageShiftTenth(Day day) {
        List<Driver> drivers = day.getShifts().get(1);
        for (int i = 0; i < drivers.size(); i++) {
            if ((day.getShifts().get(10).size() < checkNumberOfDrivers(day, 10)) && (day.checkAvailability(drivers.get(i).getNumber(), 10)) && (drivers.get(i).getNumber() != 15)) {
                addShift(10, drivers.get(i).getNumber(), day);
            }
        }
        if (day.getShifts().get(10).size() < checkNumberOfDrivers(day, 10)) {
            drivers = getSortedList(getMap(10));
            for (int i = 0; i < drivers.size(); i++) {
                if ((day.getShifts().get(10).size() < checkNumberOfDrivers(day, 10)) && (day.checkAvailability(drivers.get(i).getNumber(), 10))) {
                    addShift(10, drivers.get(i).getNumber(), day);
                }
            }
        }
    }

    private void tryDistribute(Day day) {
        manageShiftEighth(day);
        int[] otherShifts;
        if(day.isNextDayHoliday()) {
            otherShifts = new int[3];
            otherShifts[0] = 5;
            otherShifts[1] = 7;
            otherShifts[2] = 9;
        } else {
            otherShifts = new int[2];
            otherShifts[0] = 5;
            otherShifts[1] = 7;
        }
        Day previousDay = getPreviousDay(day, 3);
        if (day.getDate().getMonth() != LocalDate.now().getMonth()) {
            List<Driver> drivers = previousDay.getShifts().get(10);
            drivers.remove(day.getShifts().get(8));
            Random random = new Random();
            List<Integer> used = new ArrayList<>();
            int shiftNumber;
            while (used.size() < otherShifts.length){
                shiftNumber = otherShifts[random.nextInt(otherShifts.length)];
                if(!used.contains(shiftNumber)) {
                    List<Driver> sortedDrivers = sortLimited(shiftNumber, drivers);
                    for (int i = 0; i < sortedDrivers.size(); i++) {
                        if ((day.getShifts().get(shiftNumber).size() < checkNumberOfDrivers(day, shiftNumber)) && day.checkAvailability(sortedDrivers.get(i).getNumber(), shiftNumber)) {
                            spareDistribution.put(sortedDrivers.get(i).getNumber(), shiftNumber);
                            sortedDrivers.remove(i);
                            used.add(shiftNumber);
                            break;
                        }
                    }
                    if(sortedDrivers.size() == 0){
                        break;
                    }
                } else {
                    shiftNumber = otherShifts[random.nextInt(otherShifts.length)];
                }
            }
        }
    }

    private List<Driver> sortLimited(int shiftNumber, List<Driver> drivers) {
        List<Driver> sorted = new ArrayList<>();
        Map<Integer, Integer> map = getMap(shiftNumber);
        int shift = 0;
        while (sorted.size() < drivers.size()) {
            for (int i = 0; i < drivers.size(); i++) {
                if (map.get(drivers.get(i).getNumber()) == shift) {
                    sorted.add(drivers.get(i));
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

    private Map<Integer, Integer> getMap(int shiftNumber) {
        switch (shiftNumber) {
            case 1:
                return firstShift;
            case 2:
                return secondShift;
            case 3:
                return thirdShift;
            case 4:
                return fourthShift;
            case 5:
                return fifthShift;
            case 6:
                return sixthShift;
            case 7:
                return seventhShift;
            case 8:
                return eighthShift;
            case 9:
                return ninthShift;
            case 10:
                return tenthShift;
        }
        return null;
    }

    private List<Driver> getSortedList(Map<Integer, Integer> map) {
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

    protected void addShift(int shiftNumber, int driverNumber, Day day) {
        day.addShift(shiftNumber, DriverData.getDriver(driverNumber));
        if (day.getDate().getMonth() != LocalDate.now().getMonth()) {
            addHours(shiftNumber, driverNumber, saintDays.contains(day));
        }
        setAvailability(DriverData.getDriver(driverNumber), shiftNumber, day);
    }

    private void manageTypicalShift(int shiftNumber, Day day, List<Driver> drivers) {
        for (int i = 0; i < drivers.size(); i++) {
            if (day.checkAvailability(drivers.get(i).getNumber(), shiftNumber) && ((day.getShifts().get(shiftNumber).size() + getDisposedValue(shiftNumber)) < checkNumberOfDrivers(day, shiftNumber)) && checkUniqueness(shiftNumber, drivers.get(i).getNumber(), day) && (!day.getShifts().get(shiftNumber).contains(drivers.get(i))) && (!spareDistribution.containsKey(drivers.get(i).getNumber()))) {
                addShift(shiftNumber, drivers.get(i).getNumber(), day);
            }
        }
    }

    private int getDisposedValue(int shiftNumber){
        if(spareDistribution.containsValue(shiftNumber)){
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
                    day = new Day(actualDate, holidays.contains(actualDate), true);
                } else {
                    day = new Day(actualDate, holidays.contains(actualDate), false);
                }
            } else {
                day = new Day(actualDate, holidays.contains(actualDate), false);
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
    }

    private int checkNumberOfDrivers(Day day, int shiftNumber) {
        String dayCondition = day.getDate().getDayOfWeek().toString();
        switch (dayCondition) {
            case "MONDAY":
                return typicalDay(shiftNumber);
            case "TUESDAY":
                return typicalDay(shiftNumber);
            case "WEDNESDAY":
                return typicalDay(shiftNumber);
            case "THURSDAY":
                return typicalDay(shiftNumber);
            case "FRIDAY":
                if (shiftNumber == 2 || shiftNumber == 10) {
                    return 2;
                } else if (shiftNumber == 3 || shiftNumber == 4 || shiftNumber == 6 || shiftNumber == 7 || shiftNumber == 8 || shiftNumber == 9) {
                    return 1;
                } else if (shiftNumber == 1 || shiftNumber == 5) {
                    return 3;
                } else {
                    return 0;
                }
            case "SATURDAY":
                if (shiftNumber == 1 || shiftNumber == 5 || shiftNumber == 10) {
                    return 3;
                } else if (shiftNumber == 2 || shiftNumber == 3 || shiftNumber == 7 || shiftNumber == 8 || shiftNumber == 9) {
                    return 1;
                }
                break;
            case "SUNDAY":
                if (shiftNumber == 1 || shiftNumber == 5) {
                    return 3;
                } else if (shiftNumber == 2 || shiftNumber == 3 || shiftNumber == 7 || shiftNumber == 8 || shiftNumber == 9) {
                    return 1;
                } else if (shiftNumber == 10) {
                    return 2;
                }
                break;
        }
        return 0;
    }

    private int typicalDay(int shiftNumber) {
        if (shiftNumber == 2 || shiftNumber == 10) {
            return 2;
        } else if (shiftNumber == 3 || shiftNumber == 4 || shiftNumber == 6 || shiftNumber == 7 || shiftNumber == 8 || shiftNumber == 9) {
            return 1;
        } else if (shiftNumber == 1 || shiftNumber == 5) {
            return 3;
        }
        return 0;
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
        initializeDays();
        setPreviousDaysAvailability();
        setConditions();
        generate();
        showHours();
        removePreviousDays();
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

    private void getPreviousDays() {
        this.days.addAll(excelLoad.getPreviousDays());
    }

    public void showHours() {
        StringBuilder sb = new StringBuilder();
        for (Driver driver : DriverData.getDrivers()) {
            sb.append("Kierowca " + driver.getNumber() + " ma zagrafikowanych " + hour.getHours().get(driver) + " godzin\n\n");
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

    public void setConditions() {
        for (Driver driver : conditions.keySet()) {
            for (Condition condition : conditions.get(driver)) {
                days.get(condition.getDate().getDayOfMonth() - 1).setCondition(driver, condition);
            }
        }
    }
}