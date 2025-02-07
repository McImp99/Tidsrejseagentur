package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodCreate;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodDelete;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerSceneTidsperioder extends ControllerSceneBase {

    @FXML
    private ComboBox<String> timePeriodComboBox;

    private ObservableList<String> timePeriods;

    public void editTimePeriodButton(ActionEvent actionEvent) {
        var timeperiod = new TimePeriodUpdate(0, null, null);
        Database.getInstance().timePeriods.update(timeperiod);
    }

    public void removeTimePeriodButton(ActionEvent actionEvent) throws SQLException {
        var timeperiod = new TimePeriodDelete(0);
        Database.getInstance().timePeriods.delete(timeperiod);
    }

    public void addTimePeriodButton(ActionEvent actionEvent) throws SQLException {
        var timeperiod = new TimePeriodCreate(null, null);
        Database.getInstance().timePeriods.add(timeperiod);
    }



}
