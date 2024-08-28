package com.example.wildberriesapi.controller.statistic_controller;

import com.example.wildberriesapi.controller.StartWindowController;
import com.example.wildberriesapi.service.statistics.report.ReportExcelExporter;
import com.example.wildberriesapi.service.statistics.report.SupplierReportService;
import com.example.wildberriesapi.service.statistics.report.SupplyReport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class SupplierReportController {
    SupplierReportService supplierReportService = new SupplierReportService();

    @FXML
    private DatePicker dateFromFiled;
    @FXML
    private DatePicker dateToFiled;
    @FXML
    private TextField pathField;
    @FXML
    private Button changeDirectoryButton;
    @FXML
    private Button backButton;
    @FXML
    private Button getButton;
    @FXML
    private TextField limitField;
    @FXML
    private TextField rrdidField;

    File directory;

    DirectoryChooser directoryChooser = new DirectoryChooser();


    public static void open(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(SupplierReportController.class.getResource("/com/example/wildberriesapi/controller/fxml/SupplierReportWindow.fxml"));
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

            LocalDate dateFrom = dateFromFiled.getValue();
            LocalDate dateTo = dateToFiled.getValue();
            if (dateFrom == null || dateTo == null) return;

            int limit;
            try {
                limit = Integer.parseInt(limitField.getText());
            } catch (NumberFormatException e) {
                limit = 10000;
            }

            int rrdid;
            try {
                rrdid = Integer.parseInt(rrdidField.getText());
            } catch (NumberFormatException e) {
                rrdid = 0;
            }

            List<SupplyReport> supplyReportList = supplierReportService.getReport(dateFrom, limit, dateTo, rrdid);

            ReportExcelExporter.exportToExcel(supplyReportList, directory, dateFrom, dateTo);
        });
    }
}
