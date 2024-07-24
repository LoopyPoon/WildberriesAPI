package com.example.wildberriesapi.controller;

import com.example.wildberriesapi.statistics.orders.OrdersExcelExporter;
import com.example.wildberriesapi.statistics.orders.SupplierOrdersService;
import com.example.wildberriesapi.statistics.orders.SupplyOrders;
import com.example.wildberriesapi.statistics.sales.SalesExcelExporter;
import com.example.wildberriesapi.statistics.sales.SupplierSalesService;
import com.example.wildberriesapi.statistics.sales.SupplySales;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class SupplierSalesController {
    SupplierSalesService supplierSalesService = new SupplierSalesService();

    @FXML
    private DatePicker dateFiled;
    @FXML
    private TextField pathField;
    @FXML
    private Button changeDirectoryButton;
    @FXML
    private Button backButton;
    @FXML
    private Button getButton;
    @FXML
    private CheckBox flagCheckbox;

    File directory;

    DirectoryChooser directoryChooser = new DirectoryChooser();


    public static void open(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(SupplierSalesController.class.getResource("fxml/SupplierSalesWindow.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();

    }

    @FXML
    void initialize() {
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                StartWindowController.open((Stage) backButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        changeDirectoryButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            directory = directoryChooser.showDialog(((Node) mouseEvent.getSource()).getScene().getWindow());
            if (directory == null) return;
            pathField.setText(directory.getAbsolutePath());
        });

        getButton.setOnAction(event -> {
            if (directory == null) return;
            LocalDate selectedDate = dateFiled.getValue();
            if (selectedDate == null) return;

            int flag = flagCheckbox.isSelected() ? 1 : 0;
            List<SupplySales> supplySalesList = supplierSalesService.getSales(selectedDate, flag);

            SalesExcelExporter.exportToExcel(supplySalesList, directory, selectedDate);
        });
    }
}
