package my.application.pojo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class DriverData {
    private static final String DRIVERS_DATA = "drivers.txt";

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
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public static void saveDrivers() {
        try {
            PrintWriter printWriter = new PrintWriter(data);
            drivers.forEach(d -> printWriter.println(d.getNumber() + " " + d.getFirstName() + " " + d.getLastName() + " " + d.getPhone()));
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
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
                System.out.println("Driver doesn't exist");
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