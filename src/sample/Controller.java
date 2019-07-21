package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import sample.data.Driver;
import sample.data.DriverData;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ListView<Driver> driversList;

    @FXML
    private ContextMenu listContextMenu;

    public void initialize() {
        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Usuń");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Driver driver = driversList.getSelectionModel().getSelectedItem();
                confirmDeletion(driver);
            }
        });

        listContextMenu.getItems().addAll(deleteMenuItem);
        driversList.getItems().setAll(DriverData.getDrivers());
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
    public void exitAction() {
        DriverData.saveDrivers();
        Platform.exit();
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
        }
    }
}
