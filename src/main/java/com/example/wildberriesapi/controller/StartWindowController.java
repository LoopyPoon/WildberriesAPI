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
    public Button supplierIncomesButton;
    @FXML
    public Button supplierStockButton;
    @FXML
    public Button supplierOrdersButton;

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

//        supplierOrdersButton.setOnAction(event -> {
//            try {
//                SupplierOrderController.open((Stage)supplierOrdersButton.getScene().getWindow());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }

}
