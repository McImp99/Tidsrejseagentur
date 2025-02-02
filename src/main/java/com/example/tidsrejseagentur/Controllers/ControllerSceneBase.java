package com.example.tidsrejseagentur.Controllers;

public class ControllerSceneBase {

    public void hovedmenuButton() {
        SceneController.switchScene("SceneLandingPage.fxml");
    }

    public void bookingButton() {
        SceneController.switchScene("SceneBooking.fxml");
    }

    public void tidsperioderButton() {
        SceneController.switchScene("SceneTidsPerioder.fxml");
    }

    public void tidsrejsemaskinerButton() {
        SceneController.switchScene("SceneTidsrejseMaskiner.fxml");
    }

    public void kundeAdministrationButton() {
        SceneController.switchScene("SceneKundeadministration.fxml");
    }
}
