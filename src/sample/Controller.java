package sample;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import sample.data.Condition;
import sample.data.Driver;
import sample.data.DriverData;
import sample.data.WorkSchedule;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class Controller {

    private WorkSchedule schedule;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ListView<Driver> driversList;

    @FXML
    private ContextMenu listContextMenu;

    public void initialize() {
        schedule = new WorkSchedule();
        listContextMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edytuj");
        MenuItem deleteMenuItem = new MenuItem("Usuń");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Driver driver = driversList.getSelectionModel().getSelectedItem();
                confirmDeletion(driver);
            }
        });

        editMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Driver driver = driversList.getSelectionModel().getSelectedItem();
                showEditDialog(driver);
            }
        });

        listContextMenu.getItems().addAll(editMenuItem);
        listContextMenu.getItems().addAll(deleteMenuItem);
        driversList.getItems().setAll(sortList(DriverData.getDrivers()));
        driversList.setCellFactory(new Callback<ListView<Driver>, ListCell<Driver>>() {
            @Override
            public ListCell<Driver> call(ListView<Driver> param) {
                ListCell<Driver> cell = new ListCell<Driver>() {
                    @Override
                    protected void updateItem(Driver item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(listContextMenu);
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
            driversList.getItems().add(controller.processResult());
            sortList(driversList.getItems());
        }
    }

    @FXML
    private void showEditDialog(Driver driver){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("driverDialog.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        DriverController controller = fxmlLoader.getController();
        controller.loadValues(driver);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && (result.get() == ButtonType.OK)){
            controller.editDriver(driver);
            DriverData.addDriver(driver);
            driversList.refresh();
            sortList(driversList.getItems());
        }
    }

    @FXML
    private void showNewConditionDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Dodaj żądanie");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("conditionDialog.fxml"));
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
            ConditionController controller = fxmlLoader.getController();
            Condition condition = controller.processResult();
            schedule.addCondition(DriverData.getDriver(condition.getDriverNumber()), condition);
        }
    }

    @FXML
    private void confirmDeletion(Driver driver) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usunąć kierowcę?");
        alert.setHeaderText("Usunąć " + driver.toString());
        alert.setContentText("Kliknij OK żeby potwierdzić, Cancel żeby anulować");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            DriverData.deleteDriver(driver);
            driversList.getItems().removeAll(driver);
            sortList(driversList.getItems());
        }
    }

    @FXML
    public void clearConditions(){
        System.out.println();
        schedule.clearConditions();
    }

    @FXML
    public void exitAction() {
        DriverData.saveDrivers();
        Platform.exit();
    }

    private ObservableList<Driver> sortList(ObservableList<Driver> drivers){
        Collections.sort(drivers);
        return drivers;
    }
}
