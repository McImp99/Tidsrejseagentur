package com.example.tidsrejseagentur.Controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

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


    protected <T> void setupListView(ListView<T> listView, ObservableList<T> items, java.util.function.Function<T, String> toStringFunction) {
        listView.setItems(items);

        listView.setCellFactory(lv -> new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : toStringFunction.apply(item));
            }
        });
    }


    protected <T> void setupSelectionListener(ListView<T> listView, java.util.function.Consumer<T> selectionAction) {
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectionAction.accept(newSelection);
            }
        });
    }


}
