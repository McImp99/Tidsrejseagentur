package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodCreate;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodDelete;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerSceneTidsperioder extends ControllerSceneBase {

    @FXML public TextField timeperiodName;
    @FXML public TextField timeperiodDescription;

    @FXML private ComboBox<TimePeriodRead> timePeriodComboBox;

    private ObservableList<TimePeriodRead> listoftimePeriods = FXCollections.observableArrayList();


    public void initialize() {
        loadTimePeriods();

        setupComboBox(timePeriodComboBox, listoftimePeriods, item -> item.name(), selectedItem -> populateTimePeriodFields(selectedItem));
    }

    public void editTimePeriodButton(ActionEvent actionEvent) throws SQLException {
        TimePeriodRead selected = timePeriodComboBox.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No time period selected");
            return;
        }


        var updatedTimePeriod = new TimePeriodUpdate(
                selected.id(),
                timeperiodName.getText(),
                timeperiodDescription.getText()
        );

        try {

            int rowsAffected = Database.getInstance().timePeriods.update(updatedTimePeriod);

            if (rowsAffected > 0) {
                System.out.println("Time period updated successfully!");
                loadTimePeriods();
            } else {
                System.out.println("Update failed: No rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating time period: " + e.getMessage());
        }
    }

    public void removeTimePeriodButton(ActionEvent actionEvent) throws SQLException {
        TimePeriodRead selected = timePeriodComboBox.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No time period selected");
            return;
        }
        var timePeriodToDelete = new TimePeriodDelete(selected.id());
        Database.getInstance().timePeriods.delete(timePeriodToDelete);
        loadTimePeriods();
    }

    public void addTimePeriodButton(ActionEvent actionEvent) throws SQLException {
        var timeperiod = new TimePeriodCreate(
                timeperiodName.getText(),
                timeperiodDescription.getText()
        );
        Database.getInstance().timePeriods.add(timeperiod);
        loadTimePeriods();
    }

    private void loadTimePeriods() {

        listoftimePeriods.clear();
        try {

            List<TimePeriodRead> timeperiodList = Database.getInstance().timePeriods.readAll();

            listoftimePeriods.addAll(timeperiodList);

            timePeriodComboBox.setItems(listoftimePeriods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateTimePeriodFields(TimePeriodRead selectedTimePeriod) {
        if (selectedTimePeriod != null) {

            timeperiodName.setText(selectedTimePeriod.name());
            timeperiodDescription.setText(selectedTimePeriod.description());
        }
    }

}
