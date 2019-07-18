package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.data.Driver;
import sample.data.DriverData;

import java.util.List;

public class Controller {

    @FXML
    private Label label;

    @FXML
    public void exitAction(){
        Platform.exit();
    }

    @FXML
    public void showDrivers(){
        StringBuilder sb = new StringBuilder();
        List<Driver> dr = DriverData.getDrivers();
        for (Driver driver : dr) {
            sb.append(driver.getNumber() + " " + driver.getFirstName() + " " + driver.getLastName() + " " + driver.getPhone() + "\n");
        }
        this.label.setText(sb.toString());
    }
}
