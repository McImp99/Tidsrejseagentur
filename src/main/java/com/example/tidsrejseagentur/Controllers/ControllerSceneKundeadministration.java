package com.example.tidsrejseagentur.Controllers;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerCreate;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerDelete;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class ControllerSceneKundeadministration extends ControllerSceneBase {

    @FXML   private TextField customerFirstName;
    @FXML   private TextField customerLastName;
    @FXML   private TextField customerEmail;

    @FXML   private ListView<CustomerRead> listofCustomers;


    private ObservableList<CustomerRead> customers = FXCollections.observableArrayList();

    public void initialize() {
        setupListView(
                listofCustomers,
                customers,
                customer -> customer.id()
                        + " - " + customer.name() + " (" + customer.email() + ")",
                this::populateCustomerFields
        );
        loadCustomers();
    }


    public void addCustomerButton(ActionEvent actionEvent) throws SQLException {
        var customer = new CustomerCreate(
                customerFirstName.getText() + " " + customerLastName.getText(),
                customerEmail.getText());
        Database.getInstance().customers.add(customer);
        loadCustomers();

    }

    public void removeCustomerButton(ActionEvent actionEvent) throws SQLException {
        CustomerRead selectedCustomer = listofCustomers.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            System.out.println("No customer selected!");
            return;
        }

        CustomerDelete customerToDelete = new CustomerDelete(selectedCustomer.id());

        Database.getInstance().customers.delete(customerToDelete);

        loadCustomers();
    }

    public void editCustomerButton(ActionEvent actionEvent) throws SQLException {

        CustomerRead selectedCustomer = listofCustomers.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            System.out.println("No customer selected!");
            return;
        }

        String updatedName = customerFirstName.getText() + " " + customerLastName.getText();
        String updatedEmail = customerEmail.getText();

        if (updatedName.trim().isEmpty() || updatedEmail.trim().isEmpty()) {
            System.out.println("Name and Email cannot be empty!");
            return;
        }

        var customerUpdate = new CustomerUpdate(selectedCustomer.id(), updatedName, updatedEmail);
        Database.getInstance().customers.update(customerUpdate);

        loadCustomers();
    }


    private void loadCustomers() {
        customers.clear();
        try {
            List<CustomerRead> customerlist = Database.getInstance().customers.readAll();
            customers.addAll(customerlist);  // Add CustomerRead objects
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateCustomerFields(CustomerRead selectedCustomer) {
        if (selectedCustomer != null) {
            String[] names = selectedCustomer.name().split(" ", 2);
            customerFirstName.setText(names.length > 0 ? names[0] : "");
            customerLastName.setText(names.length > 1 ? names[1] : "");
            customerEmail.setText(selectedCustomer.email());
        }
    }
}
