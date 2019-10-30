package com.programs;

import com.programs.data.LastDays;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.List;


public class LastDaysController {
    @FXML
    private Label thirdLastDay;

    @FXML
    private Label secondLastDay;

    @FXML
    private Label lastDay;

    public List<LastDays> processResult(){
        return null;
    }

    protected void loadValues(){
        int monthLength = LocalDate.now().lengthOfMonth();
        thirdLastDay.setText(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), monthLength - 2).toString());
        secondLastDay.setText(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), monthLength - 1).toString());
        lastDay.setText(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), monthLength).toString());
    }
}
