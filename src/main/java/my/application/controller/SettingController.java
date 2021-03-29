package my.application.controller;

import my.application.helper.LoggingHelper;
import my.application.pojo.Setting;
import my.application.pojo.Shift;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class SettingController {
    private static final Logger logger = Logger.getLogger(SettingController.class.getName());

    static {
        logger.addHandler(LoggingHelper.getFileHandler());
    }

    private Setting setting;
    private Controller controller;
    private List<RadioButton> rdRequiredList;
    private List<RadioButton> rdOptionalList;
    private List<RadioButton> rdLackList;
    private List<Spinner<Integer>> sRequiredList;
    private List<Spinner<Integer>> sOptionalList;

    @FXML
    private RadioButton rdMonR1;

    @FXML
    private Spinner<Integer> sMonR1;

    @FXML
    private RadioButton rdMonO1;

    @FXML
    private Spinner<Integer> sMonO1;

    @FXML
    private RadioButton rdMonL1;

    @FXML
    private RadioButton rdMonR2;

    @FXML
    private Spinner<Integer> sMonR2;

    @FXML
    private RadioButton rdMonO2;

    @FXML
    private Spinner<Integer> sMonO2;

    @FXML
    private RadioButton rdMonL2;

    @FXML
    private RadioButton rdMonR3;

    @FXML
    private Spinner<Integer> sMonR3;

    @FXML
    private RadioButton rdMonO3;

    @FXML
    private Spinner<Integer> sMonO3;

    @FXML
    private RadioButton rdMonL3;

    @FXML
    private RadioButton rdMonR4;

    @FXML
    private Spinner<Integer> sMonR4;

    @FXML
    private RadioButton rdMonO4;

    @FXML
    private Spinner<Integer> sMonO4;

    @FXML
    private RadioButton rdMonL4;

    @FXML
    private RadioButton rdMonR5;

    @FXML
    private Spinner<Integer> sMonR5;

    @FXML
    private RadioButton rdMonO5;

    @FXML
    private Spinner<Integer> sMonO5;

    @FXML
    private RadioButton rdMonL5;

    @FXML
    private RadioButton rdMonR6;

    @FXML
    private Spinner<Integer> sMonR6;

    @FXML
    private RadioButton rdMonO6;

    @FXML
    private Spinner<Integer> sMonO6;

    @FXML
    private RadioButton rdMonL6;

    @FXML
    private RadioButton rdMonR7;

    @FXML
    private Spinner<Integer> sMonR7;

    @FXML
    private RadioButton rdMonO7;

    @FXML
    private Spinner<Integer> sMonO7;

    @FXML
    private RadioButton rdMonL7;

    @FXML
    private RadioButton rdMonR8;

    @FXML
    private Spinner<Integer> sMonR8;

    @FXML
    private RadioButton rdMonO8;

    @FXML
    private Spinner<Integer> sMonO8;

    @FXML
    private RadioButton rdMonL8;

    @FXML
    private RadioButton rdMonR9;

    @FXML
    private Spinner<Integer> sMonR9;

    @FXML
    private RadioButton rdMonO9;

    @FXML
    private Spinner<Integer> sMonO9;

    @FXML
    private RadioButton rdMonL9;

    @FXML
    private RadioButton rdMonR10;

    @FXML
    private Spinner<Integer> sMonR10;

    @FXML
    private RadioButton rdMonO10;

    @FXML
    private Spinner<Integer> sMonO10;

    @FXML
    private RadioButton rdMonL10;

    @FXML
    private RadioButton rdTueR1;

    @FXML
    private Spinner<Integer> sTueR1;

    @FXML
    private RadioButton rdTueO1;

    @FXML
    private Spinner<Integer> sTueO1;

    @FXML
    private RadioButton rdTueL1;

    @FXML
    private RadioButton rdTueR2;

    @FXML
    private Spinner<Integer> sTueR2;

    @FXML
    private RadioButton rdTueO2;

    @FXML
    private Spinner<Integer> sTueO2;

    @FXML
    private RadioButton rdTueL2;

    @FXML
    private RadioButton rdTueR3;

    @FXML
    private Spinner<Integer> sTueR3;

    @FXML
    private RadioButton rdTueO3;

    @FXML
    private Spinner<Integer> sTueO3;

    @FXML
    private RadioButton rdTueL3;

    @FXML
    private RadioButton rdTueR4;

    @FXML
    private Spinner<Integer> sTueR4;

    @FXML
    private RadioButton rdTueO4;

    @FXML
    private Spinner<Integer> sTueO4;

    @FXML
    private RadioButton rdTueL4;

    @FXML
    private RadioButton rdTueR5;

    @FXML
    private Spinner<Integer> sTueR5;

    @FXML
    private RadioButton rdTueO5;

    @FXML
    private Spinner<Integer> sTueO5;

    @FXML
    private RadioButton rdTueL5;

    @FXML
    private RadioButton rdTueR6;

    @FXML
    private Spinner<Integer> sTueR6;

    @FXML
    private RadioButton rdTueO6;

    @FXML
    private Spinner<Integer> sTueO6;

    @FXML
    private RadioButton rdTueL6;

    @FXML
    private RadioButton rdTueR7;

    @FXML
    private Spinner<Integer> sTueR7;

    @FXML
    private RadioButton rdTueO7;

    @FXML
    private Spinner<Integer> sTueO7;

    @FXML
    private RadioButton rdTueL7;

    @FXML
    private RadioButton rdTueR8;

    @FXML
    private Spinner<Integer> sTueR8;

    @FXML
    private RadioButton rdTueO8;

    @FXML
    private Spinner<Integer> sTueO8;

    @FXML
    private RadioButton rdTueL8;

    @FXML
    private RadioButton rdTueR9;

    @FXML
    private Spinner<Integer> sTueR9;

    @FXML
    private RadioButton rdTueO9;

    @FXML
    private Spinner<Integer> sTueO9;

    @FXML
    private RadioButton rdTueL9;

    @FXML
    private RadioButton rdTueR10;

    @FXML
    private Spinner<Integer> sTueR10;

    @FXML
    private RadioButton rdTueO10;

    @FXML
    private Spinner<Integer> sTueO10;

    @FXML
    private RadioButton rdTueL10;

    @FXML
    private RadioButton rdWedR1;

    @FXML
    private Spinner<Integer> sWedR1;

    @FXML
    private RadioButton rdWedO1;

    @FXML
    private Spinner<Integer> sWedO1;

    @FXML
    private RadioButton rdWedL1;

    @FXML
    private RadioButton rdWedR2;

    @FXML
    private Spinner<Integer> sWedR2;

    @FXML
    private RadioButton rdWedO2;

    @FXML
    private Spinner<Integer> sWedO2;

    @FXML
    private RadioButton rdWedL2;

    @FXML
    private RadioButton rdWedR3;

    @FXML
    private Spinner<Integer> sWedR3;

    @FXML
    private RadioButton rdWedO3;

    @FXML
    private Spinner<Integer> sWedO3;

    @FXML
    private RadioButton rdWedL3;

    @FXML
    private RadioButton rdWedR4;

    @FXML
    private Spinner<Integer> sWedR4;

    @FXML
    private RadioButton rdWedO4;

    @FXML
    private Spinner<Integer> sWedO4;

    @FXML
    private RadioButton rdWedL4;

    @FXML
    private RadioButton rdWedR5;

    @FXML
    private Spinner<Integer> sWedR5;

    @FXML
    private RadioButton rdWedO5;

    @FXML
    private Spinner<Integer> sWedO5;

    @FXML
    private RadioButton rdWedL5;

    @FXML
    private RadioButton rdWedR6;

    @FXML
    private Spinner<Integer> sWedR6;

    @FXML
    private RadioButton rdWedO6;

    @FXML
    private Spinner<Integer> sWedO6;

    @FXML
    private RadioButton rdWedL6;

    @FXML
    private RadioButton rdWedR7;

    @FXML
    private Spinner<Integer> sWedR7;

    @FXML
    private RadioButton rdWedO7;

    @FXML
    private Spinner<Integer> sWedO7;

    @FXML
    private RadioButton rdWedL7;

    @FXML
    private RadioButton rdWedR8;

    @FXML
    private Spinner<Integer> sWedR8;

    @FXML
    private RadioButton rdWedO8;

    @FXML
    private Spinner<Integer> sWedO8;

    @FXML
    private RadioButton rdWedL8;

    @FXML
    private RadioButton rdWedR9;

    @FXML
    private Spinner<Integer> sWedR9;

    @FXML
    private RadioButton rdWedO9;

    @FXML
    private Spinner<Integer> sWedO9;

    @FXML
    private RadioButton rdWedL9;

    @FXML
    private RadioButton rdWedR10;

    @FXML
    private Spinner<Integer> sWedR10;

    @FXML
    private RadioButton rdWedO10;

    @FXML
    private Spinner<Integer> sWedO10;

    @FXML
    private RadioButton rdWedL10;

    @FXML
    private RadioButton rdThuR1;

    @FXML
    private Spinner<Integer> sThuR1;

    @FXML
    private RadioButton rdThuO1;

    @FXML
    private Spinner<Integer> sThuO1;

    @FXML
    private RadioButton rdThuL1;

    @FXML
    private RadioButton rdThuR2;

    @FXML
    private Spinner<Integer> sThuR2;

    @FXML
    private RadioButton rdThuO2;

    @FXML
    private Spinner<Integer> sThuO2;

    @FXML
    private RadioButton rdThuL2;

    @FXML
    private RadioButton rdThuR3;

    @FXML
    private Spinner<Integer> sThuR3;

    @FXML
    private RadioButton rdThuO3;

    @FXML
    private Spinner<Integer> sThuO3;

    @FXML
    private RadioButton rdThuL3;

    @FXML
    private RadioButton rdThuR4;

    @FXML
    private Spinner<Integer> sThuR4;

    @FXML
    private RadioButton rdThuO4;

    @FXML
    private Spinner<Integer> sThuO4;

    @FXML
    private RadioButton rdThuL4;

    @FXML
    private RadioButton rdThuR5;

    @FXML
    private Spinner<Integer> sThuR5;

    @FXML
    private RadioButton rdThuO5;

    @FXML
    private Spinner<Integer> sThuO5;

    @FXML
    private RadioButton rdThuL5;

    @FXML
    private RadioButton rdThuR6;

    @FXML
    private Spinner<Integer> sThuR6;

    @FXML
    private RadioButton rdThuO6;

    @FXML
    private Spinner<Integer> sThuO6;

    @FXML
    private RadioButton rdThuL6;

    @FXML
    private RadioButton rdThuR7;

    @FXML
    private Spinner<Integer> sThuR7;

    @FXML
    private RadioButton rdThuO7;

    @FXML
    private Spinner<Integer> sThuO7;

    @FXML
    private RadioButton rdThuL7;

    @FXML
    private RadioButton rdThuR8;

    @FXML
    private Spinner<Integer> sThuR8;

    @FXML
    private RadioButton rdThuO8;

    @FXML
    private Spinner<Integer> sThuO8;

    @FXML
    private RadioButton rdThuL8;

    @FXML
    private RadioButton rdThuR9;

    @FXML
    private Spinner<Integer> sThuR9;

    @FXML
    private RadioButton rdThuO9;

    @FXML
    private Spinner<Integer> sThuO9;

    @FXML
    private RadioButton rdThuL9;

    @FXML
    private RadioButton rdThuR10;

    @FXML
    private Spinner<Integer> sThuR10;

    @FXML
    private RadioButton rdThuO10;

    @FXML
    private Spinner<Integer> sThuO10;

    @FXML
    private RadioButton rdThuL10;

    @FXML
    private RadioButton rdFriR1;

    @FXML
    private Spinner<Integer> sFriR1;

    @FXML
    private RadioButton rdFriO1;

    @FXML
    private Spinner<Integer> sFriO1;

    @FXML
    private RadioButton rdFriL1;

    @FXML
    private RadioButton rdFriR2;

    @FXML
    private Spinner<Integer> sFriR2;

    @FXML
    private RadioButton rdFriO2;

    @FXML
    private Spinner<Integer> sFriO2;

    @FXML
    private RadioButton rdFriL2;

    @FXML
    private RadioButton rdFriR3;

    @FXML
    private Spinner<Integer> sFriR3;

    @FXML
    private RadioButton rdFriO3;

    @FXML
    private Spinner<Integer> sFriO3;

    @FXML
    private RadioButton rdFriL3;

    @FXML
    private RadioButton rdFriR4;

    @FXML
    private Spinner<Integer> sFriR4;

    @FXML
    private RadioButton rdFriO4;

    @FXML
    private Spinner<Integer> sFriO4;

    @FXML
    private RadioButton rdFriL4;

    @FXML
    private RadioButton rdFriR5;

    @FXML
    private Spinner<Integer> sFriR5;

    @FXML
    private RadioButton rdFriO5;

    @FXML
    private Spinner<Integer> sFriO5;

    @FXML
    private RadioButton rdFriL5;

    @FXML
    private RadioButton rdFriR6;

    @FXML
    private Spinner<Integer> sFriR6;

    @FXML
    private RadioButton rdFriO6;

    @FXML
    private Spinner<Integer> sFriO6;

    @FXML
    private RadioButton rdFriL6;

    @FXML
    private RadioButton rdFriR7;

    @FXML
    private Spinner<Integer> sFriR7;

    @FXML
    private RadioButton rdFriO7;

    @FXML
    private Spinner<Integer> sFriO7;

    @FXML
    private RadioButton rdFriL7;

    @FXML
    private RadioButton rdFriR8;

    @FXML
    private Spinner<Integer> sFriR8;

    @FXML
    private RadioButton rdFriO8;

    @FXML
    private Spinner<Integer> sFriO8;

    @FXML
    private RadioButton rdFriL8;

    @FXML
    private RadioButton rdFriR9;

    @FXML
    private Spinner<Integer> sFriR9;

    @FXML
    private RadioButton rdFriO9;

    @FXML
    private Spinner<Integer> sFriO9;

    @FXML
    private RadioButton rdFriL9;

    @FXML
    private RadioButton rdFriR10;

    @FXML
    private Spinner<Integer> sFriR10;

    @FXML
    private RadioButton rdFriO10;

    @FXML
    private Spinner<Integer> sFriO10;

    @FXML
    private RadioButton rdFriL10;

    @FXML
    private RadioButton rdSatR1;

    @FXML
    private Spinner<Integer> sSatR1;

    @FXML
    private RadioButton rdSatO1;

    @FXML
    private Spinner<Integer> sSatO1;

    @FXML
    private RadioButton rdSatL1;

    @FXML
    private RadioButton rdSatR2;

    @FXML
    private Spinner<Integer> sSatR2;

    @FXML
    private RadioButton rdSatO2;

    @FXML
    private Spinner<Integer> sSatO2;

    @FXML
    private RadioButton rdSatL2;

    @FXML
    private RadioButton rdSatR3;

    @FXML
    private Spinner<Integer> sSatR3;

    @FXML
    private RadioButton rdSatO3;

    @FXML
    private Spinner<Integer> sSatO3;

    @FXML
    private RadioButton rdSatL3;

    @FXML
    private RadioButton rdSatR4;

    @FXML
    private Spinner<Integer> sSatR4;

    @FXML
    private RadioButton rdSatO4;

    @FXML
    private Spinner<Integer> sSatO4;

    @FXML
    private RadioButton rdSatL4;

    @FXML
    private RadioButton rdSatR5;

    @FXML
    private Spinner<Integer> sSatR5;

    @FXML
    private RadioButton rdSatO5;

    @FXML
    private Spinner<Integer> sSatO5;

    @FXML
    private RadioButton rdSatL5;

    @FXML
    private RadioButton rdSatR6;

    @FXML
    private Spinner<Integer> sSatR6;

    @FXML
    private RadioButton rdSatO6;

    @FXML
    private Spinner<Integer> sSatO6;

    @FXML
    private RadioButton rdSatL6;

    @FXML
    private RadioButton rdSatR7;

    @FXML
    private Spinner<Integer> sSatR7;

    @FXML
    private RadioButton rdSatO7;

    @FXML
    private Spinner<Integer> sSatO7;

    @FXML
    private RadioButton rdSatL7;

    @FXML
    private RadioButton rdSatR8;

    @FXML
    private Spinner<Integer> sSatR8;

    @FXML
    private RadioButton rdSatO8;

    @FXML
    private Spinner<Integer> sSatO8;

    @FXML
    private RadioButton rdSatL8;

    @FXML
    private RadioButton rdSatR9;

    @FXML
    private Spinner<Integer> sSatR9;

    @FXML
    private RadioButton rdSatO9;

    @FXML
    private Spinner<Integer> sSatO9;

    @FXML
    private RadioButton rdSatL9;

    @FXML
    private RadioButton rdSatR10;

    @FXML
    private Spinner<Integer> sSatR10;

    @FXML
    private RadioButton rdSatO10;

    @FXML
    private Spinner<Integer> sSatO10;

    @FXML
    private RadioButton rdSatL10;

    @FXML
    private RadioButton rdSunR1;

    @FXML
    private Spinner<Integer> sSunR1;

    @FXML
    private RadioButton rdSunO1;

    @FXML
    private Spinner<Integer> sSunO1;

    @FXML
    private RadioButton rdSunL1;

    @FXML
    private RadioButton rdSunR2;

    @FXML
    private Spinner<Integer> sSunR2;

    @FXML
    private RadioButton rdSunO2;

    @FXML
    private Spinner<Integer> sSunO2;

    @FXML
    private RadioButton rdSunL2;

    @FXML
    private RadioButton rdSunR3;

    @FXML
    private Spinner<Integer> sSunR3;

    @FXML
    private RadioButton rdSunO3;

    @FXML
    private Spinner<Integer> sSunO3;

    @FXML
    private RadioButton rdSunL3;

    @FXML
    private RadioButton rdSunR4;

    @FXML
    private Spinner<Integer> sSunR4;

    @FXML
    private RadioButton rdSunO4;

    @FXML
    private Spinner<Integer> sSunO4;

    @FXML
    private RadioButton rdSunL4;

    @FXML
    private RadioButton rdSunR5;

    @FXML
    private Spinner<Integer> sSunR5;

    @FXML
    private RadioButton rdSunO5;

    @FXML
    private Spinner<Integer> sSunO5;

    @FXML
    private RadioButton rdSunL5;

    @FXML
    private RadioButton rdSunR6;

    @FXML
    private Spinner<Integer> sSunR6;

    @FXML
    private RadioButton rdSunO6;

    @FXML
    private Spinner<Integer> sSunO6;

    @FXML
    private RadioButton rdSunL6;

    @FXML
    private RadioButton rdSunR7;

    @FXML
    private Spinner<Integer> sSunR7;

    @FXML
    private RadioButton rdSunO7;

    @FXML
    private Spinner<Integer> sSunO7;

    @FXML
    private RadioButton rdSunL7;

    @FXML
    private RadioButton rdSunR8;

    @FXML
    private Spinner<Integer> sSunR8;

    @FXML
    private RadioButton rdSunO8;

    @FXML
    private Spinner<Integer> sSunO8;

    @FXML
    private RadioButton rdSunL8;

    @FXML
    private RadioButton rdSunR9;

    @FXML
    private Spinner<Integer> sSunR9;

    @FXML
    private RadioButton rdSunO9;

    @FXML
    private Spinner<Integer> sSunO9;

    @FXML
    private RadioButton rdSunL9;

    @FXML
    private RadioButton rdSunR10;

    @FXML
    private Spinner<Integer> sSunR10;

    @FXML
    private RadioButton rdSunO10;

    @FXML
    private Spinner<Integer> sSunO10;

    @FXML
    private RadioButton rdSunL10;

    public void initialize(){
        setting = new Setting();
        rdRequiredList = new ArrayList<>();
        rdOptionalList = new ArrayList<>();
        rdLackList = new ArrayList<>();
        sRequiredList = new ArrayList<>();
        sOptionalList = new ArrayList<>();
        rdRequiredList.add(rdMonR1);
        rdOptionalList.add(rdMonO1);
        rdLackList.add(rdMonL1);
        sRequiredList.add(sMonR1);
        sOptionalList.add(sMonO1);
        rdRequiredList.add(rdMonR2);
        rdOptionalList.add(rdMonO2);
        rdLackList.add(rdMonL2);
        sRequiredList.add(sMonR2);
        sOptionalList.add(sMonO2);
        rdRequiredList.add(rdMonR3);
        rdOptionalList.add(rdMonO3);
        rdLackList.add(rdMonL3);
        sRequiredList.add(sMonR3);
        sOptionalList.add(sMonO3);
        rdRequiredList.add(rdMonR4);
        rdOptionalList.add(rdMonO4);
        rdLackList.add(rdMonL4);
        sRequiredList.add(sMonR4);
        sOptionalList.add(sMonO4);
        rdRequiredList.add(rdMonR5);
        rdOptionalList.add(rdMonO5);
        rdLackList.add(rdMonL5);
        sRequiredList.add(sMonR5);
        sOptionalList.add(sMonO5);
        rdRequiredList.add(rdMonR6);
        rdOptionalList.add(rdMonO6);
        rdLackList.add(rdMonL6);
        sRequiredList.add(sMonR6);
        sOptionalList.add(sMonO6);
        rdRequiredList.add(rdMonR7);
        rdOptionalList.add(rdMonO7);
        rdLackList.add(rdMonL7);
        sRequiredList.add(sMonR7);
        sOptionalList.add(sMonO7);
        rdRequiredList.add(rdMonR8);
        rdOptionalList.add(rdMonO8);
        rdLackList.add(rdMonL8);
        sRequiredList.add(sMonR8);
        sOptionalList.add(sMonO8);
        rdRequiredList.add(rdMonR9);
        rdOptionalList.add(rdMonO9);
        rdLackList.add(rdMonL9);
        sRequiredList.add(sMonR9);
        sOptionalList.add(sMonO9);
        rdRequiredList.add(rdMonR10);
        rdOptionalList.add(rdMonO10);
        rdLackList.add(rdMonL10);
        sRequiredList.add(sMonR10);
        sOptionalList.add(sMonO10);
        rdRequiredList.add(rdTueR1);
        rdOptionalList.add(rdTueO1);
        rdLackList.add(rdTueL1);
        sRequiredList.add(sTueR1);
        sOptionalList.add(sTueO1);
        rdRequiredList.add(rdTueR2);
        rdOptionalList.add(rdTueO2);
        rdLackList.add(rdTueL2);
        sRequiredList.add(sTueR2);
        sOptionalList.add(sTueO2);
        rdRequiredList.add(rdTueR3);
        rdOptionalList.add(rdTueO3);
        rdLackList.add(rdTueL3);
        sRequiredList.add(sTueR3);
        sOptionalList.add(sTueO3);
        rdRequiredList.add(rdTueR4);
        rdOptionalList.add(rdTueO4);
        rdLackList.add(rdTueL4);
        sRequiredList.add(sTueR4);
        sOptionalList.add(sTueO4);
        rdRequiredList.add(rdTueR5);
        rdOptionalList.add(rdTueO5);
        rdLackList.add(rdTueL5);
        sRequiredList.add(sTueR5);
        sOptionalList.add(sTueO5);
        rdRequiredList.add(rdTueR6);
        rdOptionalList.add(rdTueO6);
        rdLackList.add(rdTueL6);
        sRequiredList.add(sTueR6);
        sOptionalList.add(sTueO6);
        rdRequiredList.add(rdTueR7);
        rdOptionalList.add(rdTueO7);
        rdLackList.add(rdTueL7);
        sRequiredList.add(sTueR7);
        sOptionalList.add(sTueO7);
        rdRequiredList.add(rdTueR8);
        rdOptionalList.add(rdTueO8);
        rdLackList.add(rdTueL8);
        sRequiredList.add(sTueR8);
        sOptionalList.add(sTueO8);
        rdRequiredList.add(rdTueR9);
        rdOptionalList.add(rdTueO9);
        rdLackList.add(rdTueL9);
        sRequiredList.add(sTueR9);
        sOptionalList.add(sTueO9);
        rdRequiredList.add(rdTueR10);
        rdOptionalList.add(rdTueO10);
        rdLackList.add(rdTueL10);
        sRequiredList.add(sTueR10);
        sOptionalList.add(sTueO10);
        rdRequiredList.add(rdWedR1);
        rdOptionalList.add(rdWedO1);
        rdLackList.add(rdWedL1);
        sRequiredList.add(sWedR1);
        sOptionalList.add(sWedO1);
        rdRequiredList.add(rdWedR2);
        rdOptionalList.add(rdWedO2);
        rdLackList.add(rdWedL2);
        sRequiredList.add(sWedR2);
        sOptionalList.add(sWedO2);
        rdRequiredList.add(rdWedR3);
        rdOptionalList.add(rdWedO3);
        rdLackList.add(rdWedL3);
        sRequiredList.add(sWedR3);
        sOptionalList.add(sWedO3);
        rdRequiredList.add(rdWedR4);
        rdOptionalList.add(rdWedO4);
        rdLackList.add(rdWedL4);
        sRequiredList.add(sWedR4);
        sOptionalList.add(sWedO4);
        rdRequiredList.add(rdWedR5);
        rdOptionalList.add(rdWedO5);
        rdLackList.add(rdWedL5);
        sRequiredList.add(sWedR5);
        sOptionalList.add(sWedO5);
        rdRequiredList.add(rdWedR6);
        rdOptionalList.add(rdWedO6);
        rdLackList.add(rdWedL6);
        sRequiredList.add(sWedR6);
        sOptionalList.add(sWedO6);
        rdRequiredList.add(rdWedR7);
        rdOptionalList.add(rdWedO7);
        rdLackList.add(rdWedL7);
        sRequiredList.add(sWedR7);
        sOptionalList.add(sWedO7);
        rdRequiredList.add(rdWedR8);
        rdOptionalList.add(rdWedO8);
        rdLackList.add(rdWedL8);
        sRequiredList.add(sWedR8);
        sOptionalList.add(sWedO8);
        rdRequiredList.add(rdWedR9);
        rdOptionalList.add(rdWedO9);
        rdLackList.add(rdWedL9);
        sRequiredList.add(sWedR9);
        sOptionalList.add(sWedO9);
        rdRequiredList.add(rdWedR10);
        rdOptionalList.add(rdWedO10);
        rdLackList.add(rdWedL10);
        sRequiredList.add(sWedR10);
        sOptionalList.add(sWedO10);
        rdRequiredList.add(rdThuR1);
        rdOptionalList.add(rdThuO1);
        rdLackList.add(rdThuL1);
        sRequiredList.add(sThuR1);
        sOptionalList.add(sThuO1);
        rdRequiredList.add(rdThuR2);
        rdOptionalList.add(rdThuO2);
        rdLackList.add(rdThuL2);
        sRequiredList.add(sThuR2);
        sOptionalList.add(sThuO2);
        rdRequiredList.add(rdThuR3);
        rdOptionalList.add(rdThuO3);
        rdLackList.add(rdThuL3);
        sRequiredList.add(sThuR3);
        sOptionalList.add(sThuO3);
        rdRequiredList.add(rdThuR4);
        rdOptionalList.add(rdThuO4);
        rdLackList.add(rdThuL4);
        sRequiredList.add(sThuR4);
        sOptionalList.add(sThuO4);
        rdRequiredList.add(rdThuR5);
        rdOptionalList.add(rdThuO5);
        rdLackList.add(rdThuL5);
        sRequiredList.add(sThuR5);
        sOptionalList.add(sThuO5);
        rdRequiredList.add(rdThuR6);
        rdOptionalList.add(rdThuO6);
        rdLackList.add(rdThuL6);
        sRequiredList.add(sThuR6);
        sOptionalList.add(sThuO6);
        rdRequiredList.add(rdThuR7);
        rdOptionalList.add(rdThuO7);
        rdLackList.add(rdThuL7);
        sRequiredList.add(sThuR7);
        sOptionalList.add(sThuO7);
        rdRequiredList.add(rdThuR8);
        rdOptionalList.add(rdThuO8);
        rdLackList.add(rdThuL8);
        sRequiredList.add(sThuR8);
        sOptionalList.add(sThuO8);
        rdRequiredList.add(rdThuR9);
        rdOptionalList.add(rdThuO9);
        rdLackList.add(rdThuL9);
        sRequiredList.add(sThuR9);
        sOptionalList.add(sThuO9);
        rdRequiredList.add(rdThuR10);
        rdOptionalList.add(rdThuO10);
        rdLackList.add(rdThuL10);
        sRequiredList.add(sThuR10);
        sOptionalList.add(sThuO10);
        rdRequiredList.add(rdFriR1);
        rdOptionalList.add(rdFriO1);
        rdLackList.add(rdFriL1);
        sRequiredList.add(sFriR1);
        sOptionalList.add(sFriO1);
        rdRequiredList.add(rdFriR2);
        rdOptionalList.add(rdFriO2);
        rdLackList.add(rdFriL2);
        sRequiredList.add(sFriR2);
        sOptionalList.add(sFriO2);
        rdRequiredList.add(rdFriR3);
        rdOptionalList.add(rdFriO3);
        rdLackList.add(rdFriL3);
        sRequiredList.add(sFriR3);
        sOptionalList.add(sFriO3);
        rdRequiredList.add(rdFriR4);
        rdOptionalList.add(rdFriO4);
        rdLackList.add(rdFriL4);
        sRequiredList.add(sFriR4);
        sOptionalList.add(sFriO4);
        rdRequiredList.add(rdFriR5);
        rdOptionalList.add(rdFriO5);
        rdLackList.add(rdFriL5);
        sRequiredList.add(sFriR5);
        sOptionalList.add(sFriO5);
        rdRequiredList.add(rdFriR6);
        rdOptionalList.add(rdFriO6);
        rdLackList.add(rdFriL6);
        sRequiredList.add(sFriR6);
        sOptionalList.add(sFriO6);
        rdRequiredList.add(rdFriR7);
        rdOptionalList.add(rdFriO7);
        rdLackList.add(rdFriL7);
        sRequiredList.add(sFriR7);
        sOptionalList.add(sFriO7);
        rdRequiredList.add(rdFriR8);
        rdOptionalList.add(rdFriO8);
        rdLackList.add(rdFriL8);
        sRequiredList.add(sFriR8);
        sOptionalList.add(sFriO8);
        rdRequiredList.add(rdFriR9);
        rdOptionalList.add(rdFriO9);
        rdLackList.add(rdFriL9);
        sRequiredList.add(sFriR9);
        sOptionalList.add(sFriO9);
        rdRequiredList.add(rdFriR10);
        rdOptionalList.add(rdFriO10);
        rdLackList.add(rdFriL10);
        sRequiredList.add(sFriR10);
        sOptionalList.add(sFriO10);
        rdRequiredList.add(rdSatR1);
        rdOptionalList.add(rdSatO1);
        rdLackList.add(rdSatL1);
        sRequiredList.add(sSatR1);
        sOptionalList.add(sSatO1);
        rdRequiredList.add(rdSatR2);
        rdOptionalList.add(rdSatO2);
        rdLackList.add(rdSatL2);
        sRequiredList.add(sSatR2);
        sOptionalList.add(sSatO2);
        rdRequiredList.add(rdSatR3);
        rdOptionalList.add(rdSatO3);
        rdLackList.add(rdSatL3);
        sRequiredList.add(sSatR3);
        sOptionalList.add(sSatO3);
        rdRequiredList.add(rdSatR4);
        rdOptionalList.add(rdSatO4);
        rdLackList.add(rdSatL4);
        sRequiredList.add(sSatR4);
        sOptionalList.add(sSatO4);
        rdRequiredList.add(rdSatR5);
        rdOptionalList.add(rdSatO5);
        rdLackList.add(rdSatL5);
        sRequiredList.add(sSatR5);
        sOptionalList.add(sSatO5);
        rdRequiredList.add(rdSatR6);
        rdOptionalList.add(rdSatO6);
        rdLackList.add(rdSatL6);
        sRequiredList.add(sSatR6);
        sOptionalList.add(sSatO6);
        rdRequiredList.add(rdSatR7);
        rdOptionalList.add(rdSatO7);
        rdLackList.add(rdSatL7);
        sRequiredList.add(sSatR7);
        sOptionalList.add(sSatO7);
        rdRequiredList.add(rdSatR8);
        rdOptionalList.add(rdSatO8);
        rdLackList.add(rdSatL8);
        sRequiredList.add(sSatR8);
        sOptionalList.add(sSatO8);
        rdRequiredList.add(rdSatR9);
        rdOptionalList.add(rdSatO9);
        rdLackList.add(rdSatL9);
        sRequiredList.add(sSatR9);
        sOptionalList.add(sSatO9);
        rdRequiredList.add(rdSatR10);
        rdOptionalList.add(rdSatO10);
        rdLackList.add(rdSatL10);
        sRequiredList.add(sSatR10);
        sOptionalList.add(sSatO10);
        rdRequiredList.add(rdSunR1);
        rdOptionalList.add(rdSunO1);
        rdLackList.add(rdSunL1);
        sRequiredList.add(sSunR1);
        sOptionalList.add(sSunO1);
        rdRequiredList.add(rdSunR2);
        rdOptionalList.add(rdSunO2);
        rdLackList.add(rdSunL2);
        sRequiredList.add(sSunR2);
        sOptionalList.add(sSunO2);
        rdRequiredList.add(rdSunR3);
        rdOptionalList.add(rdSunO3);
        rdLackList.add(rdSunL3);
        sRequiredList.add(sSunR3);
        sOptionalList.add(sSunO3);
        rdRequiredList.add(rdSunR4);
        rdOptionalList.add(rdSunO4);
        rdLackList.add(rdSunL4);
        sRequiredList.add(sSunR4);
        sOptionalList.add(sSunO4);
        rdRequiredList.add(rdSunR5);
        rdOptionalList.add(rdSunO5);
        rdLackList.add(rdSunL5);
        sRequiredList.add(sSunR5);
        sOptionalList.add(sSunO5);
        rdRequiredList.add(rdSunR6);
        rdOptionalList.add(rdSunO6);
        rdLackList.add(rdSunL6);
        sRequiredList.add(sSunR6);
        sOptionalList.add(sSunO6);
        rdRequiredList.add(rdSunR7);
        rdOptionalList.add(rdSunO7);
        rdLackList.add(rdSunL7);
        sRequiredList.add(sSunR7);
        sOptionalList.add(sSunO7);
        rdRequiredList.add(rdSunR8);
        rdOptionalList.add(rdSunO8);
        rdLackList.add(rdSunL8);
        sRequiredList.add(sSunR8);
        sOptionalList.add(sSunO8);
        rdRequiredList.add(rdSunR9);
        rdOptionalList.add(rdSunO9);
        rdLackList.add(rdSunL9);
        sRequiredList.add(sSunR9);
        sOptionalList.add(sSunO9);
        rdRequiredList.add(rdSunR10);
        rdOptionalList.add(rdSunO10);
        rdLackList.add(rdSunL10);
        sRequiredList.add(sSunR10);
        sOptionalList.add(sSunO10);
        for (int i = 0; i < (7 * Shift.getShifts().size()); i++) {
            initializeEvent(i);
        }
    }

    void loadValues(){
        DayOfWeek[] daysOfWeek = DayOfWeek.values();
        Map<DayOfWeek, Map<String, Integer>> requiredShifts = setting.getRequiredShifts();
        Map<DayOfWeek, Map<String, Integer>> optionalShifts = setting.getOptionalShifts();
        int j = 0;
        for (int i = 0; i < daysOfWeek.length; i++) {
            for (String shift : requiredShifts.get(daysOfWeek[i]).keySet()) {
                if(requiredShifts.get(daysOfWeek[i]).get(shift) != 0){
                    rdRequiredList.get(j).selectedProperty().setValue(true);
                    sRequiredList.get(j).getValueFactory().setValue(requiredShifts.get(daysOfWeek[i]).get(shift));
                } else if(optionalShifts.get(daysOfWeek[i]).get(shift) != 0){
                    rdOptionalList.get(j).selectedProperty().setValue(true);
                    sOptionalList.get(j).getValueFactory().setValue(optionalShifts.get(daysOfWeek[i]).get(shift));
                } else {
                    rdLackList.get(j).selectedProperty().setValue(true);
                }
                j++;
            }
        }
    }

    Setting processResult(){
        int index = 0;
        for (int i = 1; i < 8; i++) {
            DayOfWeek dayOfWeek = DayOfWeek.of(i);
            for (int j = 0; j < Shift.getShifts().size(); j++) {
                if(rdRequiredList.get(index).isSelected()){
                    setting.getRequiredShifts().get(dayOfWeek).put(Shift.getShift(j + 1).getHours(), sRequiredList.get(index).getValue());
                    setting.getOptionalShifts().get(dayOfWeek).put(Shift.getShift(j + 1).getHours(), 0);
                } else if(rdOptionalList.get(index).isSelected()) {
                    setting.getRequiredShifts().get(dayOfWeek).put(Shift.getShift(j + 1).getHours(), 0);
                    setting.getOptionalShifts().get(dayOfWeek).put(Shift.getShift(j + 1).getHours(), sOptionalList.get(index).getValue());
                } else {
                    setting.getRequiredShifts().get(dayOfWeek).put(Shift.getShift(j + 1).getHours(), 0);
                    setting.getOptionalShifts().get(dayOfWeek).put(Shift.getShift(j + 1).getHours(), 0);
                }
                index++;
            }
        }
        return setting;
    }

    private void initializeEvent(int number){
        rdRequiredList.get(number).selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sRequiredList.get(number).visibleProperty().setValue(true);
                sOptionalList.get(number).visibleProperty().setValue(false);
            }
        });

        rdOptionalList.get(number).selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sRequiredList.get(number).visibleProperty().setValue(false);
                sOptionalList.get(number).visibleProperty().setValue(true);
            }
        });

        rdLackList.get(number).selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                sRequiredList.get(number).visibleProperty().setValue(false);
                sOptionalList.get(number).visibleProperty().setValue(false);
            }
        });
    }

    @FXML
    private void okButtonPressed(){
        controller.okPressed();
        logger.info("Settings updated ...");
    }

    @FXML
    private void cancelButtonPressed(){
        controller.cancelPressed();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
