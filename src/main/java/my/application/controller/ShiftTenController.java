package my.application.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import my.application.helper.LoggingHelper;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ShiftTenController {

    private static final Logger logger = Logger.getLogger(ShiftTenController.class.getName());

    static {
        logger.addHandler(LoggingHelper.getFileHandler());
    }

    @FXML
    private ComboBox<String> comboBox;

    String processResult(){
        logger.info("Updated setting for ten shift");
        return comboBox.getValue();
    }

    void loadValues(String freeTime){
        List<String> possibilities = Arrays.asList("2,5 dnia", "1,5 dnia", "1 dzień");
        comboBox.setItems(FXCollections.observableArrayList(possibilities));
        if(freeTime != null){
            comboBox.getSelectionModel().select(freeTime);
        }
    }
}