package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SceneController {

    public static void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/com/example/tidsrejseagentur/" + fxmlFile));
            Pane pane = loader.load();
            Scene scene = new Scene(pane, 500, 500);
            HelloApplication.getPrimaryStage().setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
