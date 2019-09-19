package sample.data;

import sample.Controller;
import sample.HolidayController;

import java.time.LocalDate;
import java.util.*;

public class WorkSchedule {
    private String month;
    private Controller controller;
    private List<Day> days;
    private Map<Driver, List<Condition>> conditions;
    private ExcelSaving excel = new ExcelSaving(this);
    private Hour hour;
    private List<Integer> shiftFour;
    private List<Integer> shiftFourSpare;
    private List<Integer> shiftEight;
    private List<Integer> shiftEightSpare;
    private ShiftManager manager;

    public WorkSchedule(Controller controller) {
        days = new ArrayList<>();
        hour = new Hour();
        shiftFour = new ArrayList<>();
        shiftEight = new ArrayList<>();
        shiftFourSpare = new ArrayList<>();
        shiftEightSpare = new ArrayList<>();
        manager = new ShiftManager();
        this.controller = controller;
        this.month = getMonth(LocalDate.now().getMonth().plus(1).toString());
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

    private void setAvailability(Driver driver, int shiftNumber, Day day) {
        List<Integer> availability;
        List<Integer> availabilityNextDay;
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
                List<Integer> availabilitySecondNextDay = Arrays.asList(1, 2, 3, 4);
                if (nextDay != null) {
                    nextDay = getNextDay(nextDay, 1);
                }
                if (nextDay != null) {
                    getNextDay(day, 2).setDriverAvailability(driver, availabilitySecondNextDay);
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

    private void addShift(int shift, Driver driver, Day day) {
        day.addShift(shift, driver);
        setAvailability(driver, shift, day);
        hour.addHours(driver.getNumber(), shift);
    }

    public void generate() {
        for (Day day : days) {
            manageDay(day);
            hour.sortByHours();
        }
    }

    private void manageDay(Day day) {
        manageRequiredShifts(day);
        manageOptionalShifts(day);
    }

    private void manageRequiredShifts(Day day) {
        List<Integer> shifts = manager.getRequired(day);
        for (int i = 0; i < shifts.size(); i++) {
            int actualShiftNumber = shifts.get(i);
            if (actualShiftNumber < 10) {
                if (day.getShifts().containsKey(actualShiftNumber)) {
                    if (actualShiftNumber != 4 && actualShiftNumber != 8) {
                        manageTypicalShift(actualShiftNumber, day);
                    } else if (actualShiftNumber == 4) {
                        manageShiftFour(day, true);
                    } else if (actualShiftNumber == 8) {
                        manageShiftEight(day);
                        tryDistribute(day);
                    }
                }
            } else {
                break;
            }
        }
    }

    private void manageShiftFour(Day day, boolean firstRound) {
        List<Driver> drivers = hour.getDrivers();
        List<Integer> list;
        if (firstRound) {
            list = shiftFour;
        } else {
            list = shiftFourSpare;
        }
        for (int i = 0; i < drivers.size(); i++) {
            if (day.checkAvailability(drivers.get(i).getNumber(), 4) && day.getShifts().get(4).size() < checkNumberOfDrivers(day, 4) && !list.contains(drivers.get(i).getNumber())) {
                addDistributedShift(4, drivers.get(i).getNumber(), day, firstRound);
                if (day.getShifts().get(4).size() == checkNumberOfDrivers(day, 4)) {
                    break;
                }
            }
            if (day.getShifts().get(4).size() < checkNumberOfDrivers(day, 4) && (i == (drivers.size() - 1))) {
                manageShiftFour(day, false);
            }
        }
    }

    private void tryDistribute(Day day) {
        Day previousDay = getPreviousDay(day, 2);
        if (previousDay != null) {
            List<Driver> drivers = new ArrayList<>(previousDay.getShifts().get(10));
            int shiftNumber = 5;
            drivers.removeAll(day.getShifts().get(8));
            for (int i = 0; i < drivers.size(); i++) {
                if (day.checkAvailability(drivers.get(i).getNumber(), shiftNumber)) {
                    addShift(shiftNumber, drivers.get(i), day);
                    break;
                }
            }
        }
    }

    private void manageTypicalShift(int shiftNumber, Day day) {
        List<Driver> drivers = hour.getDrivers();
        for (int i = 0; i < drivers.size(); i++) {
            if (day.checkAvailability(drivers.get(i).getNumber(), shiftNumber) && day.getShifts().get(shiftNumber).size() < checkNumberOfDrivers(day, shiftNumber)) {
                addShift(shiftNumber, drivers.get(i), day);
                if (shiftNumber == 1 && checkNumberOfDrivers(day, 10) > day.getShifts().get(10).size()) {
                    addShift(10, drivers.get(i), day);
                }
                if (day.getShifts().get(shiftNumber).size() == checkNumberOfDrivers(day, shiftNumber)) {
                    break;
                }
            }
        }
    }

    private void manageShiftEight(Day day) {
        List<Driver> drivers;
        Day previousDay = getPreviousDay(day, 2);
        if (previousDay != null) {
            drivers = previousDay.getShifts().get(10);
            List<Integer> randoms = new ArrayList<>();
            while (randoms.size() < drivers.size()) {
                int random = (int) (Math.random() * previousDay.getShifts().get(10).size());
                if (!randoms.contains(random)) {
                    randoms.add(random);
                }
                if (day.checkAvailability(drivers.get(random).getNumber(), 8) && !shiftEight.contains(drivers.get(random).getNumber()) && day.getShifts().get(8).size() < checkNumberOfDrivers(day, 8)) {
                    addDistributedShift(8, drivers.get(random).getNumber(), day, true);
                    break;
                }
            }
            if (day.getShifts().get(8).size() < checkNumberOfDrivers(day, 8)) {
                drivers = hour.getDrivers();
                for (int i = 0; i < drivers.size(); i++) {
                    if (day.checkAvailability(drivers.get(i).getNumber(), 8) && !shiftEight.contains(drivers.get(i).getNumber())) {
                        addDistributedShift(8, drivers.get(i).getNumber(), day, true);
                        break;
                    }
                }
            }
        }
    }

    private void addDistributedShift(int shiftNumber, int driverNumber, Day day, boolean standardList) {
        addShift(shiftNumber, DriverData.getDriver(driverNumber), day);
        if (shiftNumber == 4) {
            manageLists(4, driverNumber, standardList);
        } else if (shiftNumber == 8) {
            manageLists(8, driverNumber, standardList);
        }
    }

    private void manageLists(int shiftNumber, int driverNumber, boolean standardList) {
        List<Integer> list;
        List<Integer> listSpare;
        if (shiftNumber == 4) {
            list = shiftFour;
            listSpare = shiftFourSpare;
        } else {
            list = shiftEight;
            listSpare = shiftEightSpare;
        }
        if (standardList) {
            list.add(driverNumber);
            if (list.size() == DriverData.getDrivers().size()) {
                list.clear();
                if (listSpare.size() > 0) {
                    if (shiftNumber == 4) {
                        shiftFour = new ArrayList<>(listSpare);
                    } else {
                        shiftEight = new ArrayList<>(listSpare);
                    }
                }
            }
        } else {
            listSpare.add(driverNumber);
        }
    }

    private void manageOptionalShifts(Day day) {
        List<Integer> shifts = manager.getOptional(day);
        for (int i = 0; i < shifts.size(); i++) {
            manageOptionalShifts(shifts.get(i), day);
        }
    }

    private void manageOptionalShifts(int shiftNumber, Day day) {
        Day previousDay = getPreviousDay(day, 2);
        if (previousDay != null) {
            List<Driver> drivers = new ArrayList<>(previousDay.getShifts().get(10));
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < drivers.size(); i++) {
                if ((!day.getShifts().get(5).contains(drivers.get(i))) || (!day.getShifts().get(7).contains(drivers.get(i))) || (!day.getShifts().get(9).contains(drivers.get(i)))) {
                    list.add(i);
                }
            }
            if (list.size() > 0) {
                List<Integer> alternatives = Arrays.asList(7,9,5);
                for (int i = 0; i < alternatives.size(); i++) {
                    if(day.getShifts().containsKey(alternatives.get(i))) {
                        if (day.checkAvailability(drivers.get(list.get(0)).getNumber(), alternatives.get(i)) && day.getShifts().get(alternatives.get(i)).size() < checkNumberOfDrivers(day, alternatives.get(i))) {
                            addShift(alternatives.get(i), drivers.get(list.get(0)), day);
                        }
                    }
                }
            }
            drivers = hour.getDrivers();
            for (int i = 0; i < drivers.size(); i++) {
                if (day.checkAvailability(drivers.get(i).getNumber(), shiftNumber) && day.getShifts().get(shiftNumber).size() < checkNumberOfDrivers(day, shiftNumber)) {
                    addShift(shiftNumber, drivers.get(i), day);
                }
            }
        }
    }

    private void initializeDays() {
        List<LocalDate> holidays = HolidayController.getHolidays();
        for (int i = 0; i < LocalDate.now().getMonth().plus(1).length(false); i++) {
            LocalDate actualDate = LocalDate.now().plusMonths(1).withDayOfMonth(i + 1);
            Day day = new Day(actualDate, holidays.contains(actualDate));
            days.add(day);
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
                if (shiftNumber == 1) {
                    return 4;
                } else if (shiftNumber == 2 || shiftNumber == 10) {
                    return 2;
                } else if (shiftNumber == 3 || shiftNumber == 4 || shiftNumber == 6 || shiftNumber == 7 || shiftNumber == 8 || shiftNumber == 9) {
                    return 1;
                } else if (shiftNumber == 5) {
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
        if (shiftNumber == 1) {
            return 4;
        } else if (shiftNumber == 2 || shiftNumber == 10) {
            return 2;
        } else if (shiftNumber == 3 || shiftNumber == 4 || shiftNumber == 6 || shiftNumber == 7 || shiftNumber == 8 || shiftNumber == 9) {
            return 1;
        } else if (shiftNumber == 5) {
            return 3;
        }
        return 0;
    }

    private int generateDriverNumber(int driverNumber) {
        if (driverNumber != 0) {
            int index = hour.getDrivers().indexOf(DriverData.getDriver(driverNumber));
            if (index + 1 < hour.getDrivers().size()) {
                return hour.getDrivers().get(index + 1).getNumber();
            }
        }
        return hour.getDrivers().get(0).getNumber();
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
        initializeDays();
        setConditions();
        generate();
        showHours();
        saveWorkSchedule();
        System.out.println("Time to execute program = " + (System.currentTimeMillis() - millis));
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