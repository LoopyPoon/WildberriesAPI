package com.example.wildberriesapi.controller;

import com.example.wildberriesapi.controller.statistic_controller.*;
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
public class StatisticController {

    @FXML
    public Button supplierIncomesButton;
    @FXML
    public Button supplierStockButton;
    @FXML
    public Button supplierOrdersButton;
    @FXML
    public Button supplierSalesButton;
    @FXML
    public Button supplierReportButton;
    @FXML
    public Button backButton;

    public static void open(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(StartWindowController.class.getResource("fxml/StatisticWindow.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
        String styles = Objects.requireNonNull(StartWindowController.class.getResource("fxml/styles.css")).toExternalForm();
        scene.getStylesheets().add(styles);
    }

    @FXML
    void initialize() {
        supplierIncomesButton.setOnAction(event -> {
            try {
                SupplierIncomesController.open((Stage)supplierIncomesButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        supplierStockButton.setOnAction(event -> {
            try {
                SupplierStocksController.open((Stage)supplierStockButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        supplierOrdersButton.setOnAction(event -> {
            try {
                SupplierOrdersController.open((Stage)supplierOrdersButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        supplierSalesButton.setOnAction(event -> {
            try {
                SupplierSalesController.open((Stage)supplierSalesButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        supplierReportButton.setOnAction(event -> {
            try {
                SupplierReportController.open((Stage)supplierReportButton.getScene().getWindow());
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
