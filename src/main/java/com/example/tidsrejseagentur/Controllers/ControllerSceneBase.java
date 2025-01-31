package com.example.tidsrejseagentur.Controllers;

import javafx.event.ActionEvent;

public class ControllerSceneBase {
    public void hovedmenuButton(ActionEvent actionEvent) {
        SceneController.switchScene("Scene1Hello.fxml");
    }

    public void bookingButton(ActionEvent actionEvent) {
        SceneController.switchScene("Scene2Booking.fxml");
    }

    public void tidsperioderButton(ActionEvent actionEvent) {
        SceneController.switchScene("Scene3TidsPerioder.fxml");
    }

    public void tidsrejsemaskinerButton(ActionEvent actionEvent) {
        SceneController.switchScene("Scene4Tidsrejser.fxml");
    }

    public void kundeadministrationButton(ActionEvent actionEvent) {
        SceneController.switchScene("Scene5Kundeadministration.fxml");

    }
}
