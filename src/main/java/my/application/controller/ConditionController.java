package my.application.controller;

import my.application.helper.LoggingHelper;
import my.application.pojo.Condition;
import my.application.pojo.DriverData;
import my.application.pojo.Shift;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ConditionController {

    private static final Logger logger = Logger.getLogger(ConditionController.class.getName());

    static {
        logger.addHandler(LoggingHelper.getFileHandler());
    }

    @FXML
    private Label comboBoxDescription;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Spinner<Integer> driverNumber;

    @FXML
    private DatePicker conditionDate;

    @FXML
    private RadioButton wantedShift;

    @FXML
    private RadioButton unwantedShift;

    @FXML
    private RadioButton unwantedDay;

    public void initialize(){
        unwantedDay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                comboBoxDescription.visibleProperty().setValue(false);
                comboBox.visibleProperty().setValue(false);
            }
        });

        wantedShift.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                comboBoxDescription.visibleProperty().setValue(true);
                comboBox.visibleProperty().setValue(true);
            }
        });

        unwantedShift.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                comboBoxDescription.visibleProperty().setValue(true);
                comboBox.visibleProperty().setValue(true);
            }
        });
    }

    Condition processResult() {
        String value= comboBox.getValue();
        Shift shift = Shift.getShift(value);
        int driver = driverNumber.getValue();
        LocalDate date = conditionDate.getValue();
        boolean wanted = wantedShift.isSelected();
        boolean unwanted = unwantedShift.isSelected();
        if (DriverData.contains(driver) && date != null) {
            logger.info("Added condition for driver number: " + driver);
            if (wanted) {
                return new Condition(Arrays.asList(shift), driver, date, true);
            } else if (unwanted) {
                return new Condition(Arrays.asList(shift), driver, date, false);
            } else if(unwantedDay.isSelected()){
                return new Condition(Shift.getShifts(), driver, date, false);
            }
        }
        return null;
    }

    void loadShifts(){
        List<String> hours = new ArrayList<>();
        List<Shift> shift = Shift.getShifts();
        shift.forEach(s -> hours.add(s.getHours()));
        comboBox.setItems(FXCollections.observableArrayList(hours));
    }

    ComboBox<String> getComboBox(){
        return comboBox;
    }
}
