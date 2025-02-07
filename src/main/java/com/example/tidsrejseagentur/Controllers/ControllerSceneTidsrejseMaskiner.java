package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerUpdate;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineCreate;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineDelete;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineRead;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class ControllerSceneTidsrejseMaskiner extends ControllerSceneBase{



    @FXML    private TextField timemachineCapacity;
    @FXML    private TextField timemachineName;


    @FXML private ListView<TimeMachineRead> listofTimeMachines;

    private ObservableList<TimeMachineRead> timemachines = FXCollections.observableArrayList();

    public void initialize() {
        setupListView(
                listofTimeMachines,
                timemachines,
                timemachine -> timemachine.name(),
                this::populateTimeMachineFields
        );
        loadTimeMachines();
    }


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


    public void addTimeTimeMachineButton(ActionEvent actionEvent) throws SQLException {
        var timemachine = new TimeMachineCreate(
                timemachineName.getText(),
                Integer.parseInt(timemachineCapacity.getText()),
                "Not in use"
        );
        Database.getInstance().timeMachines.add(timemachine);
        loadTimeMachines();
        }





    private void loadTimeMachines() {
        timemachines.clear();
            try {
                List<TimeMachineRead> timemachinelist = Database.getInstance().timeMachines.readAll();
                timemachines.addAll(timemachinelist);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    private void populateTimeMachineFields(TimeMachineRead selectedTimeMachine) {
        if (selectedTimeMachine != null) {
            timemachineName.setText(selectedTimeMachine.name());
            timemachineCapacity.setText(String.valueOf(selectedTimeMachine.capacity()));
        }
    }

}
