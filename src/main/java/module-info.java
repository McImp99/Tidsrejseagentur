module com.example.tidsrejseagentur {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jshell;


    opens com.example.tidsrejseagentur to javafx.fxml;
    exports com.example.tidsrejseagentur;
    exports com.example.tidsrejseagentur.Controllers;
    opens com.example.tidsrejseagentur.Controllers to javafx.fxml;
}