package my.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import my.application.pojo.DriverData;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Planer Grafiku");
        primaryStage.setScene(new Scene(root, 1200, 620));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        if(DriverData.getDrivers().size() != 0) {
            DriverData.saveDrivers();
        }
    }
}
