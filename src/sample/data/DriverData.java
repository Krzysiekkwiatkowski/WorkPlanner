package sample.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DriverData {
    private static ObservableList<Driver> drivers = FXCollections.observableArrayList();
    private static File data = new File("drivers.txt");

    public static ObservableList<Driver> getDrivers() {
        try {
            Scanner scanner = new Scanner(data);
            String firstName;
            String lastName;
            int number;
            int phone;
            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(" ");
                firstName = values[1];
                lastName = values[2];
                try {
                    number = Integer.parseInt(values[0]);
                    phone = Integer.parseInt(values[3]);
                } catch (IllegalArgumentException e) {
                    number = 0;
                    phone = 0;
                    continue;
                }
                Driver driver = new Driver(number, firstName, lastName, phone);
                drivers.add(driver);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return drivers;
    }

    public static void saveDrivers() {
        try {
            PrintWriter printWriter = new PrintWriter(data);
            for (Driver driver : drivers) {
                printWriter.println(driver.getNumber() + " " + driver.getFirstName() + " " + driver.getLastName() + " " + driver.getPhone());
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public static Driver addDriver(Driver driver) {
        if (driver != null) {
            if (!DriverData.drivers.contains(driver)) {
                drivers.add(driver);
            }
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

    public static Driver getDriver(int driverNumber){
        for (Driver driver : drivers) {
            if(driver.getNumber() == driverNumber){
                return driver;
            }
        }
        return null;
    }
}
