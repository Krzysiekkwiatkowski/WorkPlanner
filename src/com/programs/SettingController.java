package com.programs;

import com.programs.data.Setting;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;

public class SettingController {
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

    public void initialize(){
        rdMonR1.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR1.visibleProperty().setValue(true);
                sMonO1.visibleProperty().set(false);
            }
        });

        rdMonO1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR1.visibleProperty().setValue(false);
                sMonO1.visibleProperty().setValue(true);
            }
        });

        rdMonL1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR1.visibleProperty().setValue(false);
                sMonO1.visibleProperty().setValue(false);
            }
        });

        rdMonR2.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR2.visibleProperty().setValue(true);
                sMonO2.visibleProperty().set(false);
            }
        });

        rdMonO2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR2.visibleProperty().setValue(false);
                sMonO2.visibleProperty().setValue(true);
            }
        });

        rdMonL2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR2.visibleProperty().setValue(false);
                sMonO2.visibleProperty().setValue(false);
            }
        });

        rdMonR3.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR3.visibleProperty().setValue(true);
                sMonO3.visibleProperty().set(false);
            }
        });

        rdMonO3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR3.visibleProperty().setValue(false);
                sMonO3.visibleProperty().setValue(true);
            }
        });

        rdMonL3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR3.visibleProperty().setValue(false);
                sMonO3.visibleProperty().setValue(false);
            }
        });

        rdMonR4.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR4.visibleProperty().setValue(true);
                sMonO4.visibleProperty().set(false);
            }
        });

        rdMonO4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR4.visibleProperty().setValue(false);
                sMonO4.visibleProperty().setValue(true);
            }
        });

        rdMonL4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR4.visibleProperty().setValue(false);
                sMonO4.visibleProperty().setValue(false);
            }
        });

        rdMonR5.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR5.visibleProperty().setValue(true);
                sMonO5.visibleProperty().set(false);
            }
        });

        rdMonO5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR5.visibleProperty().setValue(false);
                sMonO5.visibleProperty().setValue(true);
            }
        });

        rdMonL5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR5.visibleProperty().setValue(false);
                sMonO5.visibleProperty().setValue(false);
            }
        });

        rdMonR6.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR6.visibleProperty().setValue(true);
                sMonO6.visibleProperty().set(false);
            }
        });

        rdMonO6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR6.visibleProperty().setValue(false);
                sMonO6.visibleProperty().setValue(true);
            }
        });

        rdMonL6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR6.visibleProperty().setValue(false);
                sMonO6.visibleProperty().setValue(false);
            }
        });

        rdMonR7.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR7.visibleProperty().setValue(true);
                sMonO7.visibleProperty().set(false);
            }
        });

        rdMonO7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR7.visibleProperty().setValue(false);
                sMonO7.visibleProperty().setValue(true);
            }
        });

        rdMonL7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR7.visibleProperty().setValue(false);
                sMonO7.visibleProperty().setValue(false);
            }
        });

        rdMonR8.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR8.visibleProperty().setValue(true);
                sMonO8.visibleProperty().set(false);
            }
        });

        rdMonO8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR8.visibleProperty().setValue(false);
                sMonO8.visibleProperty().setValue(true);
            }
        });

        rdMonL8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR8.visibleProperty().setValue(false);
                sMonO8.visibleProperty().setValue(false);
            }
        });

        rdMonR9.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR9.visibleProperty().setValue(true);
                sMonO9.visibleProperty().set(false);
            }
        });

        rdMonO9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR9.visibleProperty().setValue(false);
                sMonO9.visibleProperty().setValue(true);
            }
        });

        rdMonL9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR9.visibleProperty().setValue(false);
                sMonO9.visibleProperty().setValue(false);
            }
        });

        rdMonR10.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR10.visibleProperty().setValue(true);
                sMonO10.visibleProperty().set(false);
            }
        });

        rdMonO10.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR10.visibleProperty().setValue(false);
                sMonO10.visibleProperty().setValue(true);
            }
        });

        rdMonL10.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sMonR10.visibleProperty().setValue(false);
                sMonO10.visibleProperty().setValue(false);
            }
        });
    }

    protected Setting processResult(){
        return null;
    }
}
