package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.bookings.BookingAccess;
import com.example.tidsrejseagentur.backend.domain.bookings.IBookingAccess;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingCreate;
import com.example.tidsrejseagentur.backend.domain.customers.CustomerAccess;
import com.example.tidsrejseagentur.backend.domain.customers.ICustomerAccess;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.guides.GuideAccess;
import com.example.tidsrejseagentur.backend.domain.guides.IGuideAccess;
import com.example.tidsrejseagentur.backend.domain.guides.models.GuideRead;
import com.example.tidsrejseagentur.backend.domain.time_machines.ITimeMachineAccess;
import com.example.tidsrejseagentur.backend.domain.time_machines.TimeMachineAccess;
import com.example.tidsrejseagentur.backend.domain.time_machines.models.TimeMachineRead;
import com.example.tidsrejseagentur.backend.domain.time_periods.ITimePeriodAccess;
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

    private ITimeMachineAccess timeMachineAccess;
    private ITimePeriodAccess timePeriodAccess;
    private IGuideAccess guideAccess;
    private ICustomerAccess customerAccess;
    private IBookingAccess bookingAccess;

    public void initialize() {
        try {
            // Get the access layers from Database instance, without casting to specific classes
            timeMachineAccess = Database.getInstance().timeMachines;
            timePeriodAccess = Database.getInstance().timePeriods;
            guideAccess = Database.getInstance().guides;
            customerAccess = Database.getInstance().customers;
            bookingAccess = Database.getInstance().bookings;

            // Fetch data from each access layer
            List<TimeMachineRead> machines = timeMachineAccess.readAll();
            List<TimePeriodRead> timePeriods = timePeriodAccess.readAll();
            List<GuideRead> guides = guideAccess.readAll();
            List<CustomerRead> customers = customerAccess.readAll();

            // Set data to combo boxes
            machineComboBox.setUserData(machines);
            guideComboBox.setUserData(guides);
            customerComboBox.setUserData(customers);
            timePeriodComboBox.setUserData(timePeriods);

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

