module com.example.diplom_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.datatransfer;

    opens com.example.diplom_project to javafx.fxml;
    opens com.example.diplom_project.data_base to javafx.fxml;
    exports com.example.diplom_project;
    exports com.example.diplom_project.data_base;
    exports com.example.diplom_project.controllers.exceptions;
    opens com.example.diplom_project.controllers.exceptions to javafx.fxml;
    exports com.example.diplom_project.controllers.messages.quits;
    opens com.example.diplom_project.controllers.messages.quits to javafx.fxml;
    exports com.example.diplom_project.controllers.main_controllers;
    opens com.example.diplom_project.controllers.main_controllers to javafx.fxml;
    exports com.example.diplom_project.controllers.messages;
    opens com.example.diplom_project.controllers.messages to javafx.fxml;
}