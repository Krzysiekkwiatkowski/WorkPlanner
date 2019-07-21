package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import sample.data.Driver;
import sample.data.DriverData;

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
        String driverFirtName = firstName.getText().trim();
        String driverLastName = lastName.getText().trim();
        int driverPhone = phone.getValue();
        Driver driver = new Driver(driverNumber, driverFirtName, driverLastName, driverPhone);
        DriverData.addDriver(driver);
        return driver;
    }

    public Driver editDriver(Driver driver){

        return driver;
    }

    public void loadValues(Driver driver){
        number.getValueFactory().setValue(driver.getNumber());
        firstName.textProperty().setValue(driver.getFirstName());
        lastName.textProperty().setValue(driver.getLastName());
        phone.getValueFactory().setValue(driver.getPhone());
    }
}
