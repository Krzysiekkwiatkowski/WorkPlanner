package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import sample.data.Driver;
import sample.data.DriverData;
import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ListView<Driver> driversList;

    public void initialize(){
        driversList.getItems().setAll(DriverData.getDrivers());
    }

    @FXML
    public void exitAction(){
        DriverData.saveDrivers();
        Platform.exit();
    }

    @FXML
    public void showNewDriverDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Dodaj nowego kierowcę");
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
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            DriverController controller = fxmlLoader.getController();
            driversList.getItems().add(controller.processResult());
        }
    }

    @FXML
    public void onRightClick(MouseEvent event){
        Driver driver = driversList.getSelectionModel().getSelectedItem();
        if(driver != null){
            if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                confirmDeletion(driver);
            }
        }
    }

    @FXML
    private void confirmDeletion(Driver driver) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usunąć kierowcę?");
        alert.setContentText("Kliknij OK żeby potwierdzić, Cancel żeby anulować");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            DriverData.deleteDriver(driver);
            driversList.getItems().removeAll(driver);
        }
    }
}
