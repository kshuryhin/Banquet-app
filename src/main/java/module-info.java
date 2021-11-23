module com.example.banquet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.banquet to javafx.fxml;
    exports com.example.banquet;

    opens main.Controller to javafx.fxml;
    exports main.Controller;
    exports main.Model;
    opens main.Model to javafx.fxml;


}