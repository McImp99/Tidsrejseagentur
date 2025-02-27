package com.example.tidsrejseagentur.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.function.Function;
import java.util.function.Consumer;

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

    public void guidesButton(ActionEvent actionEvent) {
        SceneController.switchScene("SceneGuides.fxml");
    }


    public <T> void setupListView(ListView<T> listView, ObservableList<T> items, Function<T, String> toStringFunction, Consumer<T> onSelection) {
        listView.setItems(items);
        listView.setCellFactory(param -> new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(toStringFunction.apply(item));
                }
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                onSelection.accept(newValue); // Call the provided function when an item is selected
            }
        });
    }

    public <T> void setupComboBox(ComboBox<T> comboBox, ObservableList<T> items, Function<T, String> toStringFunction, Consumer<T> onSelection) {
        comboBox.setItems(items);
        comboBox.setCellFactory(param -> new ListCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(toStringFunction.apply(item));
                }
            }
        });

        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                onSelection.accept(newValue); // Call the provided function when an item is selected
            }
        });
    }
}

