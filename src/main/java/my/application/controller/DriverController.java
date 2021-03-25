package my.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import my.application.pojo.Driver;
import my.application.pojo.DriverData;

public class DriverController {

    @FXML
    private Spinner<Integer> number;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Spinner<Integer> phone;

    public Driver processResult(){
        int driverNumber = number.getValue();
        String driverFirstName = firstName.getText().trim();
        String driverLastName = lastName.getText().trim();
        int driverPhone = phone.getValue();
        Driver driver = new Driver(driverNumber, driverFirstName, driverLastName, driverPhone);
        DriverData.addDriver(driver);
        return driver;
    }

    public void editDriver(Driver driver){
        driver.setNumber(number.getValue());
        driver.setFirstName(firstName.getText().trim());
        driver.setLastName(lastName.getText().trim());
        driver.setPhone(phone.getValue());
    }

    public void loadValues(Driver driver){
        number.getValueFactory().setValue(driver.getNumber());
        firstName.textProperty().setValue(driver.getFirstName());
        lastName.textProperty().setValue(driver.getLastName());
        phone.getValueFactory().setValue(driver.getPhone());
    }
}
