package com.example.wildberriesapi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class StartWindowController {

    @FXML
    public Button statisticsButton;
    @FXML
    public Button analyticsButton;
    @FXML
    public Button advanceButton;

    public static void open(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartWindowController.class.getResource("fxml/StartWindow.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
        String styles = Objects.requireNonNull(StartWindowController.class.getResource("fxml/styles.css")).toExternalForm();
        scene.getStylesheets().add(styles);
    }

    @FXML
    void initialize() {
        statisticsButton.setOnAction(event -> {
            try {
                StatisticController.open((Stage)statisticsButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        analyticsButton.setOnAction(event -> {
            try {
                AnalyticController.open((Stage)analyticsButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        advanceButton.setOnAction(event -> {
            try {
                AdvanceController.open((Stage)advanceButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
