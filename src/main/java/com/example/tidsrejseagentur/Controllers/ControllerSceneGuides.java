package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideCreate;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideDelete;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideRead;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideUpdate;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodCreate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class ControllerSceneGuides extends ControllerSceneBase {

    @FXML    private TextField guideName;
    @FXML    private TextField guideSpecialty;

    @FXML    private ComboBox<GuideRead> guideComboBox;

    private ObservableList<GuideRead> listofGuides = FXCollections.observableArrayList();


    public void initialize() {
        loadGuides();

        setupComboBox(guideComboBox, listofGuides, item -> item.name(), selectedItem -> populateGuideFields(selectedItem));
    }

    public void editGuideButton(ActionEvent actionEvent) {
        GuideRead selected = guideComboBox.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No guide selected");
            return;
        }


        var updatedGuide = new GuideUpdate(
                selected.id(),
                guideName.getText(),
                guideSpecialty.getText()
        );

        int rowsAffected = Database.getInstance().guides.update(updatedGuide);

        if (rowsAffected > 0) {
            System.out.println("Guide updated successfully!");
            loadGuides();
        } else {
            System.out.println("Update failed: No rows affected.");
        }
    }



    public void removeGuideButton(ActionEvent actionEvent) {
        try {
            GuideRead selected = guideComboBox.getSelectionModel().getSelectedItem();
            if (selected == null) {
                System.out.println("No guide selected");
                return;
            }
            var guideToDelete = new GuideDelete(selected.id());
            int rowsAffected = Database.getInstance().guides.delete(guideToDelete);
            if (rowsAffected > 0) {
                System.out.println("Guide deleted successfully!");
            } else {
                System.out.println("Guide deletion failed.");
            }
            loadGuides();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting guide.");
        }
    }

    public void addGuideButton(ActionEvent actionEvent)  {
        try {
            var guide = new GuideCreate(
                    guideName.getText(),
                    guideSpecialty.getText()
            );
            int id = Database.getInstance().guides.add(guide);
            if (id != -1) {
                System.out.println("Guide added with ID: " + id);
            } else {
                System.out.println("Failed to add guide.");
            }
            loadGuides();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding guide.");
        }
    }



    private void loadGuides() {

        listofGuides.clear();
        try {

            List<GuideRead> guideList = Database.getInstance().guides.readAll();

            listofGuides.addAll(guideList);

            guideComboBox.setItems(listofGuides);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateGuideFields(GuideRead selectedGuide) {
        if (selectedGuide != null) {

            guideName.setText(selectedGuide.name());
            guideSpecialty.setText(selectedGuide.speciality());
        }
    }
}
