package com.example.tidsrejseagentur.Controllers;

public class ControllerSceneBase {
    public void hovedmenuButton() {
        SceneController.switchScene("Scene1Hello.fxml");
    }

    public void bookingButton() {
        SceneController.switchScene("Scene2Booking.fxml");
    }

    public void tidsperioderButton() {
        SceneController.switchScene("Scene3TidsPerioder.fxml");
    }

    public void tidsrejsemaskinerButton() {
        SceneController.switchScene("Scene4Tidsrejser.fxml");
    }

    public void kundeadministrationButton() {
        SceneController.switchScene("Scene5Kundeadministration.fxml");

    }
}
