package sample;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import sample.data.Condition;

import java.time.LocalDate;

public class ConditionController {

    @FXML
    private Spinner<Integer> shiftNumber;

    @FXML
    private Spinner<Integer> driverNumber;

    @FXML
    private DatePicker conditionDate;

    @FXML
    private RadioButton wantedShift;

    @FXML
    private RadioButton unwantedShift;

    public Condition processResult(){
        int shift = shiftNumber.getValue();
        int driver = driverNumber.getValue();
        LocalDate date = conditionDate.getValue();
        boolean wanted = wantedShift.isSelected();
        boolean unwanted = unwantedShift.isSelected();
        if(wanted){
            return new Condition(shift, driver, date, true);
        } else if(unwanted){
            return new Condition(shift, driver, date, false);
        }
        return null;
    }
}
