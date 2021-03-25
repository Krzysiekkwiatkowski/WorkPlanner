package my.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HolidayController {

    private static List<LocalDate> holidays = new ArrayList<>();

    @FXML
    private DatePicker holidayDate;

    public void processResult() {
        LocalDate date = holidayDate.getValue().minusDays(1);
        holidays.add(date);
    }

    public static List<LocalDate> getHolidays(){
        return holidays;
    }

}
