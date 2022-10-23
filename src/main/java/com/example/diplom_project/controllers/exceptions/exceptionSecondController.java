package com.example.diplom_project.controllers.exceptions;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class exceptionSecondController {

    @FXML
    private Button OK_choice;

    @FXML
    void initialize() {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();

        OK_choice.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            OK_choice.getScene().getWindow().hide();
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
    }
}
