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
    public void show(){
        StringBuilder sb = new StringBuilder();
        List<Driver> dr = DriverData.getDrivers();
        for (Driver driver : dr) {
            sb.append(driver.getNumber() + " " + driver.getFirstName() + " " + driver.getLastName() + " " + driver.getPhone() + "\n");
        }
        this.label.setText(sb.toString());
        dr.add(new Driver(5, "Kris", "Sris", 452145214));
        DriverData.saveDrivers(dr);
        for (Driver driver : dr) {
            System.out.println((driver.getNumber() + " " + driver.getFirstName() + " " + driver.getLastName() + " " + driver.getPhone()));
        }
    }
}
