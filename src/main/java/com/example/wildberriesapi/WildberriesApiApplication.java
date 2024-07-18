package com.example.wildberriesapi;

import com.example.wildberriesapi.controller.StartWindowController;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class WildberriesApiApplication extends javafx.application.Application {

    public static void main(String[] args) {
//        SpringApplication.run(WildberriesApiApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StartWindowController.open(stage);
        stage.setResizable(false);
        stage.show();
    }
}
