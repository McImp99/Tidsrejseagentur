package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.bookings.BookingAccess;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingCreate;
import com.example.tidsrejseagentur.backend.domain.customers.CustomerAccess;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.guides.GuideAccess;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideRead;
import com.example.tidsrejseagentur.backend.domain.time_machines.TimeMachineAccess;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.TimePeriodAccess;
import com.example.tidsrejseagentur.backend.domain.time_periods.models.TimePeriodRead;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ControllerSceneBooking extends ControllerSceneBase {



    @FXML private ComboBox<String> machineComboBox;
    @FXML private ComboBox<String> guideComboBox;
    @FXML private ComboBox<String> customerComboBox;
    @FXML private ComboBox<String> timePeriodComboBox;
    @FXML private Text confirmationMessage;

    private TimeMachineAccess timeMachineAccess;
    private TimePeriodAccess timePeriodAccess;
    private GuideAccess guideAccess;
    private CustomerAccess customerAccess;
    private BookingAccess bookingAccess;

    public void initialize() {
        try {

            Connection conn = Database.getInstance().getConnection();


            timeMachineAccess = new TimeMachineAccess(conn);
            timePeriodAccess = new TimePeriodAccess(conn);
            guideAccess = new GuideAccess(conn);
            customerAccess = new CustomerAccess(conn);
            bookingAccess = new BookingAccess(conn);

            List<TimeMachineRead> machines = timeMachineAccess.readAll();
            List<TimePeriodRead> timePeriods = timePeriodAccess.readAll();
            List<GuideRead> guides = guideAccess.readAll();
            List<CustomerRead> customers = customerAccess.readAll();

            for (TimeMachineRead machine : machines) {
                machineComboBox.getItems().add(machine.name());
            }
            for (TimePeriodRead period : timePeriods) {
                timePeriodComboBox.getItems().add(period.name());
            }
            for (GuideRead guide : guides) {
                guideComboBox.getItems().add(guide.name());
            }
            for (CustomerRead customer : customers) {
                customerComboBox.getItems().add(customer.name());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadComboBoxes() throws SQLException {

        List<TimeMachineRead> machines = timeMachineAccess.readAll();
        List<TimePeriodRead> timePeriods = timePeriodAccess.readAll();
        List<GuideRead> guides = guideAccess.readAll();
        List<CustomerRead> customers = customerAccess.readAll();

        for (TimeMachineRead machine : machines) {
            machineComboBox.getItems().add(machine.name());
        }
        for (TimePeriodRead period : timePeriods) {
            timePeriodComboBox.getItems().add(period.name());
        }
        for (GuideRead guide : guides) {
            guideComboBox.getItems().add(guide.name());
        }
        for (CustomerRead customer : customers) {
            customerComboBox.getItems().add(customer.name());
        }
    }

    @FXML
    public void bookButtonAction(ActionEvent event) {

        int selectedMachineId = ((List<TimeMachineRead>) machineComboBox.getUserData())
                .get(machineComboBox.getSelectionModel().getSelectedIndex()).id();
        int selectedGuideId = ((List<GuideRead>) guideComboBox.getUserData())
                .get(guideComboBox.getSelectionModel().getSelectedIndex()).id();
        int selectedCustomerId = ((List<CustomerRead>) customerComboBox.getUserData())
                .get(customerComboBox.getSelectionModel().getSelectedIndex()).id();
        int selectedTimePeriodId = ((List<TimePeriodRead>) timePeriodComboBox.getUserData())
                .get(timePeriodComboBox.getSelectionModel().getSelectedIndex()).id();


        try {

            BookingCreate bookingCreate = new BookingCreate(selectedCustomerId, selectedMachineId, selectedTimePeriodId, selectedGuideId);
            int bookingId = bookingAccess.add(bookingCreate);

            confirmationMessage.setText("Booking successful! Booking ID: " + bookingId);

        } catch (SQLException e) {
            confirmationMessage.setText("Booking failed due to an error.");
            e.printStackTrace();
        }
    }
}

