package com.example.tidsrejseagentur;

import com.example.tidsrejseagentur.Controllers.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage primaryStage;

    //Sets the start of the program
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        SceneController.switchScene("SceneLandingPage.fxml");
        primaryStage.setTitle("Velkommen til kodekonstruktørernes Time Travel Agency");
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }


}
