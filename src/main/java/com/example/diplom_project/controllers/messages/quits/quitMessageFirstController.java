package com.example.diplom_project.controllers.messages.quits;

import com.example.diplom_project.data_base.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class quitMessageFirstController {
    @FXML
    private Button no_choice, yes_choice;

    @FXML
    void initialize() {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        DataBaseHandler dataBaseHandler = new DataBaseHandler();

        yes_choice.setOnAction(event -> {
            yes_choice.getScene().getWindow().hide();
            try {
                dataBaseHandler.deleteAll();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        no_choice.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            no_choice.getScene().getWindow().hide();
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