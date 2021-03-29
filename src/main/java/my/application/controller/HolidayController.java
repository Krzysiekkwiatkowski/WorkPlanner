package my.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import my.application.helper.LoggingHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HolidayController {

    private static final Logger logger = Logger.getLogger(HolidayController.class.getName());

    static {
        logger.addHandler(LoggingHelper.getFileHandler());
    }

    private static List<LocalDate> holidays = new ArrayList<>();

    @FXML
    private DatePicker holidayDate;

    void processResult() {
        LocalDate date = holidayDate.getValue().minusDays(1);
        holidays.add(date);
        logger.info("Added holiday " + date.toString());
    }

    public static List<LocalDate> getHolidays(){
        return holidays;
    }

}
