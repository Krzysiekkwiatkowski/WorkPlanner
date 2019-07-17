package sample.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverData {
    private static List<Driver> drivers = new ArrayList<>();
    private static File data = new File("drivers.txt");

    public static List<Driver> getDrivers(){
        try {
            Scanner scanner = new Scanner(data);
            String firstName;
            String lastName;
            int number;
            int phone;
            while (scanner.hasNextLine()){
                String[] values = scanner.nextLine().split(" ");
                firstName = values[1];
                lastName = values[2];
                try{
                    number = Integer.parseInt(values[0]);
                    phone = Integer.parseInt(values[3]);
                } catch (IllegalArgumentException e){
                    number = 0;
                    phone = 0;
                    continue;
                }
                Driver driver = new Driver(number, firstName, lastName, phone);
                drivers.add(driver);
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        return drivers;
    }

    public static void saveDrivers(List<Driver> driversToSave){
        try{
            PrintWriter printWriter = new PrintWriter(data);
            for (Driver driver : driversToSave) {
                printWriter.println(driver.getNumber() + " " + driver.getFirstName() + " " + driver.getLastName() + " " + driver.getPhone());
            }
            printWriter.close();
            drivers = driversToSave;
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
