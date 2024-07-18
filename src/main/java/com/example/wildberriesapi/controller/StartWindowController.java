package com.example.wildberriesapi.controller;

import com.example.wildberriesapi.statistics.incomes.SupplierIncomesService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class StartWindowController {
    SupplierIncomesService supplierIncomesService = new SupplierIncomesService();

    @FXML
    private Button changeDirectoryButton;

    @FXML
    private TextField pathField;

    @FXML
    public Button supplierIncomesButton;

    DirectoryChooser directoryChooser = new DirectoryChooser();


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
    }
//    @FXML
//    void initialize() {
//        changeDirectoryButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
//                    File selectedDirectory = directoryChooser.showDialog(((Node) mouseEvent.getSource()).getScene().getWindow());
//                    if (selectedDirectory == null) return;
//                    pathField.setText(selectedDirectory.getAbsolutePath());
//                });
//        completeButton.setOnAction(event -> {
//            List<Supply> supplyList = supplierService.getSuppliers();
//            File file = new File(String.valueOf(pathField));
//            ExcelExporter.exportToExcel(supplyList, file);
//        });
//    }
}
