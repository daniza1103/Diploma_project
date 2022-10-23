package com.example.diplom_project.controllers.main_controllers;

import com.example.diplom_project.data_base.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class inputInformationController {
    @FXML
    private Button back_button, cancel_button, exit_button, next_button, save_button;
    @FXML
    private TextField Tcomm, Tdign, Tdiploma, Tfails, Tmark2, Tmark3, Tmark4, Tmark5, Tname, Tpasses, Tresp;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        int counter = dataBaseHandler.countRows();

        exit_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            exit_button.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/com/example/diplom_project/quitMessageSecondWindow.fxml"));
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

        save_button.setOnAction(event -> {
            String name = Tname.getText();
            String mark5 = Tmark5.getText();
            String mark4 = Tmark4.getText();
            String mark3 = Tmark3.getText();
            String mark2 = Tmark2.getText();
            String passes = Tpasses.getText();
            String fails = Tfails.getText();
            String resp = Tresp.getText();
            String dign = Tdign.getText();
            String comm = Tcomm.getText();
            String diploma = Tdiploma.getText();

            stage.initStyle(StageStyle.UNDECORATED);
            save_button.getScene().getWindow().hide();

            String regex = "[0-9]+";
            int k = 0;
            if (mark5.trim().matches(regex) & mark4.trim().matches(regex) & mark3.trim().matches(regex) & mark2.trim().matches(regex) &
                    passes.trim().matches(regex) & fails.trim().matches(regex) & diploma.trim().matches(regex)) {
                k++;
            }

            if (!(name.isEmpty()) | !(mark5.isEmpty()) | !(mark4.isEmpty()) | !(mark3.isEmpty()) | !(mark2.isEmpty()) | !(passes.isEmpty()) |
                    !(fails.isEmpty()) | !(resp.isEmpty()) | !(dign.isEmpty()) | !(comm.isEmpty()) | !(diploma.isEmpty())) {
                if (k == 1) {
                    if (!((mark5.equals("0")) & (mark4.equals("0")) & (mark3.equals("0")) & (mark2.equals("0")) & (passes.equals("0")) & (fails.equals("0")))) {
                        switch (resp.trim()) {
                            case "определенно, да", "Определенно, да", "скорее да, чем нет", "Скорее да, чем нет", "скорее нет, чем да", "Скорее нет, чем да", "определенно, нет", "Определенно, нет" -> {
                                switch (dign.trim()) {
                                    case "определенно, да", "Определенно, да", "скорее да, чем нет", "Скорее да, чем нет", "скорее нет, чем да", "Скорее нет, чем да", "определенно, нет", "Определенно, нет" -> {
                                        switch (comm.trim()) {
                                            case "определенно, да", "Определенно, да", "скорее да, чем нет", "Скорее да, чем нет", "скорее нет, чем да", "Скорее нет, чем да", "определенно, нет", "Определенно, нет" -> {
                                                try {
                                                    dataBaseHandler.inputInfo(name, mark5, mark4, mark3, mark2, passes, fails, resp, dign, comm, diploma);
                                                } catch (SQLException | ClassNotFoundException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                loader.setLocation(getClass().getResource("/com/example/diplom_project/saveMessageWindow.fxml"));
                                                try {
                                                    loader.load();
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                            default -> {
                                                loader.setLocation(getClass().getResource("/com/example/diplom_project/exceptionThirdWindow.fxml"));
                                                try {
                                                    loader.load();
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        }
                                    }
                                    default -> {
                                        loader.setLocation(getClass().getResource("/com/example/diplom_project/exceptionThirdWindow.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            }
                            default -> {
                                loader.setLocation(getClass().getResource("/com/example/diplom_project/exceptionThirdWindow.fxml"));
                                try {
                                    loader.load();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    } else {
                        loader.setLocation(getClass().getResource("/com/example/diplom_project/exceptionFirstWindow.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    loader.setLocation(getClass().getResource("/com/example/diplom_project/exceptionFifthWindow.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                loader.setLocation(getClass().getResource("/com/example/diplom_project/exceptionSecondWindow.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });

        cancel_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            cancel_button.getScene().getWindow().hide();
            try {
                dataBaseHandler.deleteLine();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            loader.setLocation(getClass().getResource("/com/example/diplom_project/deleteMessageWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });

        next_button.setOnAction(event -> {
            stage.initStyle(StageStyle.UNDECORATED);
            next_button.getScene().getWindow().hide();
            if (counter <= 3) {
                loader.setLocation(getClass().getResource("/com/example/diplom_project/exceptionFourthWindow.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                loader.setLocation(getClass().getResource("/com/example/diplom_project/showResultWindow.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}