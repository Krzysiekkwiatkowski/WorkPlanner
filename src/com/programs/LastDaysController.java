package com.programs;

import com.programs.data.LastDays;
import com.programs.data.Shift;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class LastDaysController {
    @FXML
    private Label thirdLastDay;

    @FXML
    private Label secondLastDay;

    @FXML
    private Label lastDay;

    @FXML
    private TextField thirdDayFirst;

    @FXML
    private TextField thirdDaySecond;

    @FXML
    private TextField thirdDayThird;

    @FXML
    private TextField thirdDayFourth;

    @FXML
    private TextField thirdDayFifth;

    @FXML
    private TextField thirdDaySixth;

    @FXML
    private TextField thirdDaySeventh;

    @FXML
    private TextField thirdDayEighth;

    @FXML
    private TextField thirdDayNinth;

    @FXML
    private TextField thirdDayTenth;

    @FXML
    private TextField secondDayFirst;

    @FXML
    private TextField secondDaySecond;

    @FXML
    private TextField secondDayThird;

    @FXML
    private TextField secondDayFourth;

    @FXML
    private TextField secondDayFifth;

    @FXML
    private TextField secondDaySixth;

    @FXML
    private TextField secondDaySeventh;

    @FXML
    private TextField secondDayEighth;

    @FXML
    private TextField secondDayNinth;

    @FXML
    private TextField secondDayTenth;

    @FXML
    private TextField lastDayFirst;

    @FXML
    private TextField lastDaySecond;

    @FXML
    private TextField lastDayThird;

    @FXML
    private TextField lastDayFourth;

    @FXML
    private TextField lastDayFifth;

    @FXML
    private TextField lastDaySixth;

    @FXML
    private TextField lastDaySeventh;

    @FXML
    private TextField lastDayEighth;

    @FXML
    private TextField lastDayNinth;

    @FXML
    private TextField lastDayTenth;

    protected List<LastDays> processResult(){
        List<String> shiftsThird = new ArrayList<>();
        List<String> shiftsSecond = new ArrayList<>();
        List<String> shiftsLast = new ArrayList<>();
        List<LastDays> days = new ArrayList<>();
        shiftsThird.add(thirdDayFirst.getText());
        shiftsThird.add(thirdDaySecond.getText());
        shiftsThird.add(thirdDayThird.getText());
        shiftsThird.add(thirdDayFourth.getText());
        shiftsThird.add(thirdDayFifth.getText());
        shiftsThird.add(thirdDaySixth.getText());
        shiftsThird.add(thirdDaySeventh.getText());
        shiftsThird.add(thirdDayEighth.getText());
        shiftsThird.add(thirdDayNinth.getText());
        shiftsThird.add(thirdDayTenth.getText());
        shiftsSecond.add(secondDayFirst.getText());
        shiftsSecond.add(secondDaySecond.getText());
        shiftsSecond.add(secondDayThird.getText());
        shiftsSecond.add(secondDayFourth.getText());
        shiftsSecond.add(secondDayFifth.getText());
        shiftsSecond.add(secondDaySixth.getText());
        shiftsSecond.add(secondDaySeventh.getText());
        shiftsSecond.add(secondDayEighth.getText());
        shiftsSecond.add(secondDayNinth.getText());
        shiftsSecond.add(secondDayTenth.getText());
        shiftsLast.add(lastDayFirst.getText());
        shiftsLast.add(lastDaySecond.getText());
        shiftsLast.add(lastDayThird.getText());
        shiftsLast.add(lastDayFourth.getText());
        shiftsLast.add(lastDayFifth.getText());
        shiftsLast.add(lastDaySixth.getText());
        shiftsLast.add(lastDaySeventh.getText());
        shiftsLast.add(lastDayEighth.getText());
        shiftsLast.add(lastDayNinth.getText());
        shiftsLast.add(lastDayTenth.getText());
        LastDays thirdDay = new LastDays(LocalDate.parse(thirdLastDay.getText()));
        LastDays secondDay = new LastDays(LocalDate.parse(secondLastDay.getText()));
        LastDays firstDay = new LastDays(LocalDate.parse(lastDay.getText()));
        days.add(thirdDay);
        days.add(secondDay);
        days.add(firstDay);
        int i = 0;
        for (Shift shift : Shift.getShifts()) {
            thirdDay.getShifts().put(shift.getHours(), shiftsThird.get(i));
            secondDay.getShifts().put(shift.getHours(), shiftsSecond.get(i));
            firstDay.getShifts().put(shift.getHours(), shiftsLast.get(i));
            i++;
        }
        return days;
    }

    protected void loadValues(){
        int monthLength = LocalDate.now().lengthOfMonth();
        thirdLastDay.setText(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), monthLength - 2).toString());
        secondLastDay.setText(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), monthLength - 1).toString());
        lastDay.setText(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), monthLength).toString());
    }
}
