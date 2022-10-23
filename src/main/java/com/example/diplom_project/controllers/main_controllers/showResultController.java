package com.example.diplom_project.controllers.main_controllers;

import com.example.diplom_project.data_base.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class showResultController {
    @FXML
    private TextArea final_result;
    @FXML
    private Button back_button, beginning_button, exit_button;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        dataBaseHandler.finalRanks();

        final_result.setText("Студент: " + dataBaseHandler.name1 + " с рангом: " + dataBaseHandler.rank1 + "\n" + "\n" +
                "Студент: " + dataBaseHandler.name2 + " с рангом: " + dataBaseHandler.rank2 + "\n" + "\n" +
                "Студент: " + dataBaseHandler.name3 + " с рангом: " + dataBaseHandler.rank3);

        exit_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            exit_button.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/com/example/diplom_project/quitMessageFourthWindow.fxml"));
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

        beginning_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            beginning_button.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/com/example/diplom_project/beginningMessageWindow.fxml"));
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