package com.example.wildberriesapi.controller;

import com.example.wildberriesapi.statistics.incomes.ExcelExporter;
import com.example.wildberriesapi.statistics.incomes.SupplierIncomesService;
import com.example.wildberriesapi.statistics.incomes.SupplyIncomes;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class SupplierIncomesController {
    SupplierIncomesService supplierIncomesService = new SupplierIncomesService();

    @FXML
    private DatePicker dateFiled;
    @FXML
    private TextField pathField;
    @FXML
    private Button changeDirectoryButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button getButton;

    File directory;

    DirectoryChooser directoryChooser = new DirectoryChooser();


    public static void open(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(SupplierIncomesController.class.getResource("fxml/SupplierIncomesWindow.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent );
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    @FXML
    void initialize() {
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::close);

        changeDirectoryButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            directory = directoryChooser.showDialog(((Node) mouseEvent.getSource()).getScene().getWindow());
            if (directory == null) return;
            pathField.setText(directory.getAbsolutePath());
        });

        getButton.setOnAction(event -> {
            if (directory == null) return;
            List<SupplyIncomes> supplyIncomesList = supplierIncomesService.getSuppliers();

            ExcelExporter.exportToExcel(supplyIncomesList, directory);
        });
    }

    private void close(Event event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}