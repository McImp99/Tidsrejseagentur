package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineCreate;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineDelete;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineRead;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class ControllerSceneTidsrejseMaskiner extends ControllerSceneBase{
    // GUI textfields for entering a new timemachine/updating
    @FXML    private TextField timemachineCapacity;
    @FXML    private TextField timemachineName;


    @FXML private ListView<TimeMachineRead> listofTimeMachines;
    private ObservableList<TimeMachineRead> timemachines = FXCollections.observableArrayList();

    //setting up the listview in a specific way as being declared
    public void initialize() {
        setupListView(
                listofTimeMachines,
                timemachines,
                timemachine -> STR."\{timemachine.name()} - Capacity: \{timemachine.capacity()} (\{timemachine.status()})",
                //Method reference to populateTimeMachineFields()
                this::populateTimeMachineFields
        );
        //Updates the list
        loadTimeMachines();
    }

    //Method for the editButton, checks for a chosen machine and edits with the newly input text into textfields
    public void editTimeMachineButton(ActionEvent actionEvent) {
        TimeMachineRead selectedTimeMachine = listofTimeMachines.getSelectionModel().getSelectedItem();

        if (selectedTimeMachine == null) {
            System.out.println("No time machine selected");
            return;
        }

        String name = timemachineName.getText();
        int capacity = Integer.parseInt(timemachineCapacity.getText());
        String status = "In use";

        var timemachineUpdate = new TimeMachineUpdate(
                selectedTimeMachine.id(),
                name,
                capacity,
                status
        );

        try {
            Database.getInstance().timeMachines.update(timemachineUpdate);
            System.out.println("Time machine updated successfully");
            loadTimeMachines();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    Method for removeButton also makes sure that when clicking it, that the list gets updated by using
    the loadTimeMachines() function
     */
    public void removeTimeMachineButton(ActionEvent actionEvent) throws SQLException {
        TimeMachineRead selectedTimeMachine = listofTimeMachines.getSelectionModel().getSelectedItem();

        if (selectedTimeMachine == null) {
            System.out.println("No timemachine selected");
            return;
        }
        TimeMachineDelete timeMachineToDelete = new TimeMachineDelete(selectedTimeMachine.id());

        Database.getInstance().timeMachines.delete(timeMachineToDelete);

        loadTimeMachines();
    }

    /*
    Method for the addButton, sets machine to Not in use by default, but has no more function in the program
    as of yet. Could be implemented later, but currently its just a status being shown but not used.
     */
    public void addTimeTimeMachineButton(ActionEvent actionEvent) throws SQLException {
        var timemachine = new TimeMachineCreate(
                timemachineName.getText(),
                Integer.parseInt(timemachineCapacity.getText()),
                "Not in use"
        );
        Database.getInstance().timeMachines.add(timemachine);
        loadTimeMachines();
        }

        /*
        a function that updates the viewed list of TimeMachines by clearing it and then refilling the list.
         */
        private void loadTimeMachines() {
        timemachines.clear();
            try {
                List<TimeMachineRead> timemachinelist = Database.getInstance().timeMachines.readAll();
                timemachines.addAll(timemachinelist);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /*
    Method to help the user when updating TimeMachine to
    autofill into the textfields with the stored text that they want to edit.
     */
        private void populateTimeMachineFields(TimeMachineRead selectedTimeMachine) {
            if (selectedTimeMachine != null) {
            timemachineName.setText(selectedTimeMachine.name());
            timemachineCapacity.setText(String.valueOf(selectedTimeMachine.capacity()));
        }
    }

}
