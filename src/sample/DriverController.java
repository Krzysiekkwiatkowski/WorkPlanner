package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.data.Driver;
import sample.data.DriverData;

public class DriverController {

    @FXML
    private TextField number;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phone;

    public Driver processResult(){
        int driverNumber = Integer.parseInt(number.getText().trim());
        String driverFirtName = firstName.getText().trim();
        String driverLastName = lastName.getText().trim();
        int driverPhone = Integer.parseInt(phone.getText().trim());
        Driver driver = new Driver(driverNumber, driverFirtName, driverLastName, driverPhone);
        DriverData.addDriver(driver);
        return driver;
    }
}
