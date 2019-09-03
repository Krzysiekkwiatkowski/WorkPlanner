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
    private List<Integer> shiftFour;
    private Hour hour;

    public WorkSchedule(Controller controller) {
        days = new ArrayList<>();
        hour = new Hour();
        shiftFour = new ArrayList<>();
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

    private boolean prepareShift(Day day, int actualShiftNumber) {
        int numberOfDrivers = checkNumberOfDrivers(day, actualShiftNumber);
        int driverNumber = 0;
        while (day.getShifts().get(actualShiftNumber).size() < numberOfDrivers) {
            driverNumber = generateDriverNumber(driverNumber);
            if (day.checkAvailability(driverNumber, actualShiftNumber) && day.getShifts().containsKey(actualShiftNumber)) {
                if (actualShiftNumber != 10 && actualShiftNumber != 8 && actualShiftNumber != 4) {
                    addShift(actualShiftNumber, DriverData.getDriver(driverNumber), day);
                } else if (actualShiftNumber == 4) {
                    for (int i = 0; i < hour.getDrivers().size(); i++) {
                        Driver driver = hour.getDrivers().get(i);
                        if ((!shiftFour.contains(driver.getNumber())) && day.checkAvailability(driver.getNumber(), 4) && day.getShifts().get(4).size() < checkNumberOfDrivers(day, 4)) {
                            addShift(4, driver, day);
                            shiftFour.add(driver.getNumber());
                            if(shiftFour.size() == DriverData.getDrivers().size()){
                                shiftFour.clear();
                            }
                        }
                        if (day.getShifts().get(4).size() == checkNumberOfDrivers(day, 4)) {
                            break;
                        }
                    }
                } else {
                    if (actualShiftNumber != 8) {
                        for (int i = 0; i < checkNumberOfDrivers(day, 10); i++) {
                            Driver driver = day.getShifts().get(1).get(i);
                            addShift(actualShiftNumber, driver, day);
                            if (i == checkNumberOfDrivers(day, 10) - 1) {
                                Day second = getNextDay(day, 2);
                                if (second != null) {
                                    int random = (int) (Math.random() * (i + 1));
                                    driver = day.getShifts().get(10).get(random);
                                    addShift(8, driver, second);
                                }
                            }
                        }
                    }
                }
                if (day.getShifts().get(actualShiftNumber).size() == numberOfDrivers) {
                    hour.sortByHours();
                    return true;
                }
            }
        }
        return false;
    }

    private void addShift(int shift, Driver driver, Day day) {
        day.addShift(shift, driver);
        setAvailability(driver, shift, day);
        hour.addHours(driver.getNumber(), shift);
    }

    public void generate() {
        for (Day day : days) {
            manageDay(day);
        }
    }

    // For testing purposes
    private int printFourShift(int driverNumber) {
        if (DriverData.contains(driverNumber)) {
            int counter = 0;
            for (Integer number : shiftFour) {
                if (number == driverNumber) {
                    counter++;
                }
            }
            return counter;
        }
        return -1;
    }

    private void manageDay(Day day) {
        boolean finish = false;
        int actualShiftNumber = 4;
        while (!finish) {
            if (actualShiftNumber == 0) {
                finish = true;
            }
            if (day.getShifts().containsKey(actualShiftNumber)) {
                prepareShift(day, actualShiftNumber);
                actualShiftNumber = generateNext(actualShiftNumber);
            } else {
                actualShiftNumber = generateNext(actualShiftNumber);
                if (actualShiftNumber > Shift.getShifts().size()) {
                    finish = true;
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

    private int generateNext(int previous) {
        if(previous == 4){
            previous = 0;
        }
        if (previous == 7 || previous == 3) {
            previous++;
        }
        if (++previous <= 10) {
            return previous;
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
//        long millis = System.currentTimeMillis();
        initializeDays();
        setConditions();
        generate();
        showHours();
        saveWorkSchedule();
        for (int i = 1; i <= DriverData.getMaxNumber(); i++) {
            if (printFourShift(i) != -1) {
                System.out.println("Driver " + i + " has got\t" + printFourShift(i) + "\tshifts number 4");
            }
        }
//        System.out.println("Time to execute program = " + (System.currentTimeMillis() - millis));
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