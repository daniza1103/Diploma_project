package com.example.diplom_project.controllers.main_controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class helpController {
    @FXML
    private Button back_button, exit_button;

    @FXML
    void initialize() {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();

        exit_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            exit_button.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/com/example/diplom_project/quitMessageThirdWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });

        back_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            back_button.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/com/example/diplom_project/helloWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}