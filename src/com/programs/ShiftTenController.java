package com.programs;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import java.util.Arrays;
import java.util.List;

public class ShiftTenController {
    @FXML
    private ComboBox<String> comboBox;

    protected String processResult(){
        return comboBox.getValue();
    }

    protected void loadValues(String freeTime){
        List<String> possibilities = Arrays.asList("2,5 dnia", "1,5 dnia", "1 dzień");
        comboBox.setItems(FXCollections.observableArrayList(possibilities));
        if(freeTime != null){
            comboBox.getSelectionModel().select(freeTime);
        }
    }
}