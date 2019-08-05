package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import sample.data.Day;
import java.time.LocalDate;
import java.util.List;

public class HolidayController {

    @FXML
    private DatePicker holidayDate;

    public void processResult(List<Day> days) {
        LocalDate date = holidayDate.getValue();
        for (Day day : days) {
            if(day.getDate().toString().equals(date.toString())){
                day.setHoliday(true);
                break;
            }
        }
    }
}
