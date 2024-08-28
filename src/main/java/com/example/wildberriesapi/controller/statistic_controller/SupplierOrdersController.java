package com.example.wildberriesapi.controller.statistic_controller;

import com.example.wildberriesapi.controller.StartWindowController;
import com.example.wildberriesapi.service.statistics.orders.OrdersExcelExporter;
import com.example.wildberriesapi.service.statistics.orders.SupplierOrdersService;
import com.example.wildberriesapi.service.statistics.orders.SupplyOrders;
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
public class SupplierOrdersController {
    SupplierOrdersService supplierOrdersService = new SupplierOrdersService();

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
        FXMLLoader loader = new FXMLLoader(SupplierOrdersController.class.getResource("/com/example/wildberriesapi/controller/fxml/SupplierOrdersWindow.fxml"));
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
            List<SupplyOrders> supplyOrdersList = supplierOrdersService.getOrders(selectedDate, flag);

            OrdersExcelExporter.exportToExcel(supplyOrdersList, directory, selectedDate);
        });
    }
}
