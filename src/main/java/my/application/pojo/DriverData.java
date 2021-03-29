package my.application.pojo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import my.application.helper.LoggingHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverData {

    private static final Logger logger = Logger.getLogger(DriverData.class.getName());
    private static final String DRIVERS_DATA = "drivers.txt";

    static {
        logger.addHandler(LoggingHelper.getFileHandler());
    }

    private static ObservableList<Driver> drivers = FXCollections.observableArrayList();
    private static File data = new File(DRIVERS_DATA);

    public static ObservableList<Driver> getDrivers() {
        return drivers;
    }

    static void loadDrivers() {
        try {
            Scanner scanner = new Scanner(data);
            String firstName;
            String lastName;
            int number = 0;
            int phone = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] values = line.split(" ");
                    firstName = values[1];
                    lastName = values[2];
                    try {
                        number = Integer.parseInt(values[0]);
                        phone = Integer.parseInt(values[3]);
                    } catch (IllegalArgumentException e) {
                        continue;
                    }
                    Driver driver = new Driver(number, firstName, lastName, phone);
                    drivers.add(driver);
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File " + DRIVERS_DATA + " not found", e);
        }
    }

    public static void saveDrivers() {
        try {
            PrintWriter printWriter = new PrintWriter(data);
            drivers.forEach(d -> printWriter.println(d.getNumber() + " " + d.getFirstName() + " " + d.getLastName() + " " + d.getPhone()));
            printWriter.close();
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "There was a problem saving data to file " + DRIVERS_DATA, e);
        }
    }

    public static Driver addDriver(Driver driver) {
        if ((driver != null) && (!DriverData.drivers.contains(driver))) {
            drivers.add(driver);
        }
        return driver;
    }

    public static void deleteDriver(Driver driver) {
        if (driver != null) {
            if (drivers.contains(driver)) {
                drivers.remove(driver);
            } else {
                logger.warning("Driver doesn't exist");
            }
        }
    }

    public static Driver getDriver(int driverNumber) {
        Optional<Driver> result = drivers.stream()
                .filter(d -> d.getNumber() == driverNumber)
                .findFirst();
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    public static boolean contains(int number) {
        Optional<Driver> result = drivers.stream()
                .filter(d -> d.getNumber() == number)
                .findFirst();
        return result.isPresent();
    }

    static int getMaxNumber() {
        Optional<Driver> driver = drivers.stream()
                .max(Driver::compareTo);
        if(driver.isPresent()){
            return driver.get().getNumber();
        }
        return 0;
    }
}