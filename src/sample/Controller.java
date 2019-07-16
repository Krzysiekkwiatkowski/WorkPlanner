package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class Controller {

    @FXML
    public void exitAction(){
        Platform.exit();
    }
}
