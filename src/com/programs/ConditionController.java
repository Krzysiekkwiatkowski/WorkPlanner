package com.programs;

import com.programs.data.Condition;
import com.programs.data.DriverData;
import com.programs.data.Shift;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConditionController {

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

    public Condition processResult() {
        String value= comboBox.getValue();
        Shift shift = Shift.getShift(value);
        int driver = driverNumber.getValue();
        LocalDate date = conditionDate.getValue();
        boolean wanted = wantedShift.isSelected();
        boolean unwanted = unwantedShift.isSelected();
        if (DriverData.contains(driver) && date != null) {
            if (wanted) {
                return new Condition(shift, driver, date, true);
            } else if (unwanted) {
                return new Condition(shift, driver, date, false);
            }
        }
        return null;
    }

    public void loadShifts(){
        List<String> hours = new ArrayList<>();
        List<Shift> shift = Shift.getShifts();
        for (int i = 0; i < shift.size(); i++) {
            hours.add(shift.get(i).getHours());
        }
        comboBox.setItems(FXCollections.observableArrayList(hours));
    }

    public ComboBox<String> getComboBox(){
        return comboBox;
    }
}
