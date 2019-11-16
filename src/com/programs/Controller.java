package com.programs;

import com.programs.data.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import java.io.IOException;
import java.util.*;

public class Controller {

    private WorkSchedule schedule;
    private ObservableList<Condition> conditions;
    private static Dialog<ButtonType> actualDialog;
    private static SettingController actualController;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ListView<Driver> driversList;

    @FXML
    private ListView<Condition> conditionList;

    @FXML
    private ContextMenu driversContextMenu;

    @FXML
    private ContextMenu conditionsContextMenu;

    @FXML
    private Label displaySchedule;

    public void initialize() {
        schedule = new WorkSchedule(this);
        driversContextMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edytuj");
        MenuItem deleteDriverItem = new MenuItem("Usuń");
        deleteDriverItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Driver driver = driversList.getSelectionModel().getSelectedItem();
                confirmDriverDeletion(driver);
            }
        });

        editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Driver driver = driversList.getSelectionModel().getSelectedItem();
                showEditDialog(driver);
            }
        });

        driversContextMenu.getItems().addAll(editMenuItem);
        driversContextMenu.getItems().addAll(deleteDriverItem);
        driversList.getItems().setAll(sortList(DriverData.getDrivers()));
        driversList.setCellFactory(new Callback<ListView<Driver>, ListCell<Driver>>() {
            @Override
            public ListCell<Driver> call(ListView<Driver> param) {
                ListCell<Driver> cell = new ListCell<Driver>() {
                    @Override
                    protected void updateItem(Driver driver, boolean empty) {
                        super.updateItem(driver, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(driver.toString());
                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(driversContextMenu);
                            }
                        }
                );
                return cell;
            }
        });
        conditions = FXCollections.observableArrayList();
        conditionsContextMenu = new ContextMenu();
        MenuItem deleteConditionItem = new MenuItem("Usuń");
        deleteConditionItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Condition condition = conditionList.getSelectionModel().getSelectedItem();
                confirmConditionDeletion(condition);
            }
        });
        conditionsContextMenu.getItems().addAll(deleteConditionItem);
        conditionList.getItems().setAll(conditions);
        conditionList.setCellFactory(new Callback<ListView<Condition>, ListCell<Condition>>() {
            @Override
            public ListCell<Condition> call(ListView<Condition> param) {
                ListCell<Condition> cell = new ListCell<Condition>() {
                    @Override
                    protected void updateItem(Condition condition, boolean empty) {
                        super.updateItem(condition, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(condition.toString());
                            if(condition.isPossibleShift()){
                                setTextFill(Color.GREEN);
                            } else {
                                if(!condition.isAllDayLong()) {
                                    setTextFill(Color.RED);
                                } else {
                                    setTextFill(Color.BLUE);
                                }
                            }
                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(conditionsContextMenu);
                            }
                        }
                );
                return cell;
            }
        });
    }

    @FXML
    public void showNewDriverDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Dodaj nowego kierowcę");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("driverDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DriverController controller = fxmlLoader.getController();
            Driver driver = controller.processResult();
            driversList.getItems().add(driver);
            sortList(driversList.getItems());
            schedule.addDriverToUpdate(driver, false);
        }
    }

    @FXML
    private void showEditDialog(Driver driver) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("driverDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        DriverController controller = fxmlLoader.getController();
        controller.loadValues(driver);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            controller.editDriver(driver);
            DriverData.addDriver(driver);
            driversList.refresh();
            sortList(driversList.getItems());
        }
    }

    @FXML
    private void showNewConditionDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Dodaj żądanie");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("conditionDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
        }
        ConditionController controller = fxmlLoader.getController();
        controller.loadShifts();
        controller.getComboBox().getSelectionModel().selectFirst();
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Condition condition = controller.processResult();
            if (condition != null) {
                schedule.addCondition(DriverData.getDriver(condition.getDriverNumber()), condition);
                conditions.add(condition);
                conditionList.getItems().add(condition);
            } else {
                System.out.println("Necessary data was not present");
            }
        }
    }

    @FXML
    private void showHolidayDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Dodaj czerwoną kartkę");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("holidayDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            HolidayController holidayController = fxmlLoader.getController();
            holidayController.processResult();
        }
    }

    @FXML
    private void showSettingDialog(){
        actualDialog = new Dialog<>();
        actualDialog.initOwner(mainBorderPane.getScene().getWindow());
        actualDialog.setTitle("Aktualne ustawienia grafiku");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("settingDialog.fxml"));
        try {
            actualDialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            System.out.println("Couldn't load the dialog");
        }
        SettingController controller = fxmlLoader.getController();
        controller.setController(this);
        actualController = controller;
        controller.loadValues();
        actualDialog.show();
        actualDialog.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> {
            actualDialog.setResult(ButtonType.CANCEL);
            actualDialog.close();
        });
    }

    protected void okPressed(){
        Setting setting = actualController.processResult();
        setting.saveSetting();
        actualDialog.setResult(ButtonType.OK);
        actualDialog.close();
    }

    protected void cancelPressed(){
        actualDialog.setResult(ButtonType.CANCEL);
        actualDialog.close();
    }

    @FXML
    private void showLastDaysDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Uzupełnij ostatnie dni");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("lastDaysDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            System.out.println("Couldn't load the dialog");
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        LastDaysController controller = fxmlLoader.getController();
        controller.loadValues();
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            schedule.setLastDays(controller.processResult());
        }
    }

    @FXML
    private void showShiftTenSettingDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Wybierz wolne po zmianie 22-6");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("shiftTenSettingDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        ShiftTenController controller = fxmlLoader.getController();
        controller.loadValues(schedule.getFreeTime());
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            String freeTime = controller.processResult();
            schedule.setFreeTime(freeTime);
        }
    }

    @FXML
    private void confirmDriverDeletion(Driver driver) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usunąć kierowcę?");
        alert.setHeaderText("Usunąć " + driver.toString());
        alert.setContentText("Kliknij OK żeby potwierdzić, Cancel żeby anulować");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            DriverData.deleteDriver(driver);
            driversList.getItems().removeAll(driver);
            sortList(driversList.getItems());
            schedule.addDriverToUpdate(driver, true);
        }
    }

    @FXML
    private void confirmConditionDeletion(Condition condition) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usunąć żądanie?");
        alert.setHeaderText("Usunąć " + condition.toString());
        alert.setContentText("Kliknij OK żeby potwierdzić, Cancel żeby anulować");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            schedule.getConditions().get(DriverData.getDriver(condition.getDriverNumber())).remove(condition);
            conditions.remove(condition);
            conditionList.getItems().remove(condition);
        }
    }

    @FXML
    public void clearConditions() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wyczyścić żądania?");
        alert.setHeaderText("Czy chcesz usunąć wszystkie żadania?");
        alert.setContentText("Kliknij OK żeby potwierdzić, Cancel żeby anulować");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            schedule.clearConditions();
            conditions.clear();
            conditionList.getItems().clear();
        }
    }

    @FXML
    public void exitAction() {
        DriverData.saveDrivers();
        Platform.exit();
    }

    @FXML
    public void startGenerating(){
        schedule.generateWorkSchedule();
    }

    private ObservableList<Driver> sortList(ObservableList<Driver> drivers) {
        Collections.sort(drivers);
        return drivers;
    }

    public Label getDisplaySchedule() {
        return displaySchedule;
    }

    protected Controller getController(){
        return this;
    }
}
