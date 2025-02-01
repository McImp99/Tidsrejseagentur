package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodRead;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerScene3Tidsperioder extends ControllerSceneBase {

    @FXML
    private ComboBox<String> timePeriodComboBox;

    private ObservableList<String> timePeriods;


    /* @FXML
    public void initialize() {
        try {
            List<TimePeriodRead> periodObjects = Database.getInstance().timePeriods.readAll();

            List<String> periodNames = periodObjects.stream()
                    .map(TimePeriodRead::name)
                    .collect(Collectors.toList());

            timePeriods = FXCollections.observableArrayList(periodNames);
            timePeriodComboBox.setItems(timePeriods);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR: Could not load time periods from database.");

    }
}*/

}
