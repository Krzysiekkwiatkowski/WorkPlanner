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
        manager = new ShiftManager();
        for (Driver driver : DriverData.getDrivers()) {
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
        manageRequiredShifts(day);
//        manageOptionalShifts(day);
    }

    private void manageRequiredShifts(Day day) {
        List<Integer> shifts = manager.getRequired(day);
        for (int i = 0; i < shifts.size(); i++) {
            manageTypicalShift(shifts.get(i), day, getSortedList(getMap(shifts.get(i))));
        }
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
            sortedList.addAll(actual);
            numberOfShifts++;
            actual.clear();
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

    private void manageTypicalShift(int shiftNumber, Day day, List<Driver> drivers) {
        for (int i = 0; i < drivers.size(); i++) {
            if (day.checkAvailability(drivers.get(i).getNumber(), shiftNumber) && (day.getShifts().get(shiftNumber).size() < checkNumberOfDrivers(day, shiftNumber)) && checkUniqueness(shiftNumber, drivers.get(i).getNumber(), day)) {
                addShift(shiftNumber, drivers.get(i).getNumber(), day);
            }
        }
    }

    private void manageShiftEight(Day day, List<Driver> drivers) {
    }

    private void manageOptionalShifts(Day day) {
    }

    private void manageOptionalShifts(int shiftNumber, Day day) {
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