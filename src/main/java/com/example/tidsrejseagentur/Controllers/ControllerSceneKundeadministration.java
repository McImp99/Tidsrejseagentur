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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class ControllerSceneKundeadministration extends ControllerSceneBase {

    @FXML   private TextField customerFirstName;
    @FXML   private TextField customerLastName;
    @FXML   private TextField customerEmail;

    @FXML   private ListView<String> listofCustomers;


    private ObservableList<String> customers = FXCollections.observableArrayList();

    public void initialize() {
        listofCustomers.setItems(customers);
        loadCustomers();
    }


    public void addCustomerButton(ActionEvent actionEvent) throws SQLException {
        var customer = new CustomerCreate(customerFirstName.getText() + " " + customerLastName.getText(), customerEmail.getText());
        Database.getInstance().customers.add(customer);
        loadCustomers();

    }

    public void removeCustomerButton(ActionEvent actionEvent) throws SQLException {
        var customer = new CustomerDelete(0);
        Database.getInstance().customers.delete(customer);
        loadCustomers();

    }

    public void editCustomerButton(ActionEvent actionEvent) {
        var customer = new CustomerUpdate(0, null, null);
        Database.getInstance().customers.update(customer);

        loadCustomers();
    }



    private void loadCustomers() {
        customers.clear();
        try {
            List<CustomerRead> customerlist = Database.getInstance().customers.readAll();

            if (customerlist.isEmpty()) {

                customers.add("No customers available");
            } else {
                for (CustomerRead customer : customerlist) {
                    customers.add(customer.id() + " - " + customer.name() + " ( " + customer.email() + " )");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
