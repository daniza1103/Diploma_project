package com.example.diplom_project.controllers.main_controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class helloController {
    @FXML
    private Button continue_button, help_button, exit_button;

    @FXML
    void initialize() {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();

        exit_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            exit_button.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/com/example/diplom_project/quitMessageFirstWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });

        continue_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            continue_button.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/com/example/diplom_project/inputInformationWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });

        help_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            help_button.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/com/example/diplom_project/helpWindow.fxml"));
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