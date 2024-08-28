package com.example.wildberriesapi.controller;

import com.example.wildberriesapi.controller.advance_controller.CostHistoryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class AdvanceController {

    @FXML
    public Button paidStorageButton;
    @FXML
    public Button backButton;

    public static void open(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartWindowController.class.getResource("/com/example/wildberriesapi/controller/fxml/AdvanceWindow.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
        String styles = Objects.requireNonNull(StartWindowController.class.getResource("fxml/styles.css")).toExternalForm();
        scene.getStylesheets().add(styles);
    }

    @FXML
    void initialize() {
        paidStorageButton.setOnAction(event -> {
            try {
                CostHistoryController.open((Stage)paidStorageButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                StartWindowController.open((Stage) backButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
