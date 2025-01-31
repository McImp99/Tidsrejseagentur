module com.example.tidsrejseagentur {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tidsrejseagentur to javafx.fxml;
    exports com.example.tidsrejseagentur;
}