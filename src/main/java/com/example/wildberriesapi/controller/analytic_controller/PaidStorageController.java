package com.example.wildberriesapi.controller.analytic_controller;

import com.example.wildberriesapi.controller.StartWindowController;
import com.example.wildberriesapi.service.analytics.paid_storage.PaidStorageExcelExporter;
import com.example.wildberriesapi.service.analytics.paid_storage.PaidStorageReport;
import com.example.wildberriesapi.service.analytics.paid_storage.PaidStorageReportService;

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
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class PaidStorageController {
    PaidStorageReportService paidStorageReportService = new PaidStorageReportService();

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

    File directory;

    DirectoryChooser directoryChooser = new DirectoryChooser();


    public static void open(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(PaidStorageController.class.getResource("/com/example/wildberriesapi/controller/fxml/PaidStorageWindow.fxml"));
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

            CompletableFuture.supplyAsync(() -> paidStorageReportService.createPaidStorageReport(dateFrom, dateTo))
                    .thenCompose(taskId -> CompletableFuture.supplyAsync(() -> {
                        String status;
                        do {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            status = paidStorageReportService.checkPaidStorageReportStatus(taskId);
                        } while (!"done".equalsIgnoreCase(status));
                        return taskId;
                    }))
                    .thenAccept(taskId -> {
                        List<PaidStorageReport> reportData = paidStorageReportService.downloadPaidStorageReport(taskId);
                        PaidStorageExcelExporter.exportToExcel(reportData, directory, dateFrom, dateTo);
                    })
                    .exceptionally(ex -> {
                        log.error("Ошибка при выполнении операции: ", ex);
                        return null;
                    });

        });
    }
}
