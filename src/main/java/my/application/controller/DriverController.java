package my.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import my.application.helper.LoggingHelper;
import my.application.pojo.Driver;
import my.application.pojo.DriverData;

import java.util.logging.Logger;

public class DriverController {

    private static final Logger logger = Logger.getLogger(DriverController.class.getName());

    static {
        logger.addHandler(LoggingHelper.getFileHandler());
    }

    @FXML
    private Spinner<Integer> number;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Spinner<Integer> phone;

    Driver processResult(){
        int driverNumber = number.getValue();
        String driverFirstName = firstName.getText().trim();
        String driverLastName = lastName.getText().trim();
        int driverPhone = phone.getValue();
        Driver driver = new Driver(driverNumber, driverFirstName, driverLastName, driverPhone);
        DriverData.addDriver(driver);
        logger.info("Added driver with a number: " + driver.getNumber());
        return driver;
    }

    void editDriver(Driver driver){
        driver.setNumber(number.getValue());
        driver.setFirstName(firstName.getText().trim());
        driver.setLastName(lastName.getText().trim());
        driver.setPhone(phone.getValue());
        logger.info("Modified driver with a number: " + driver.getNumber());
    }

    void loadValues(Driver driver){
        number.getValueFactory().setValue(driver.getNumber());
        firstName.textProperty().setValue(driver.getFirstName());
        lastName.textProperty().setValue(driver.getLastName());
        phone.getValueFactory().setValue(driver.getPhone());
    }
}
