package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerUpdate;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineCreate;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineDelete;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineUpdate;
import javafx.event.ActionEvent;

import java.sql.SQLException;

public class ControllerSceneTidsrejseMaskiner extends ControllerSceneBase{


    public void editTimeMachineButton(ActionEvent actionEvent) {
        var timemachine = new TimeMachineUpdate(0, null, 0, null);
        Database.getInstance().timeMachines.update(timemachine);
    }

    public void removeTimeMachineButton(ActionEvent actionEvent) throws SQLException {
        var timemachine = new TimeMachineDelete(0);
        Database.getInstance().timeMachines.delete(timemachine);
    }

    public void addTimeTimeMachineButton(ActionEvent actionEvent) throws SQLException {
        var timemachine = new TimeMachineCreate(null, 0, null);
        Database.getInstance().timeMachines.add(timemachine);
    }
}
