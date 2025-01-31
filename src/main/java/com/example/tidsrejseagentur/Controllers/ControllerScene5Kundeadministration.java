package com.example.tidsrejseagentur.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControllerScene5Kundeadministration extends ControllerSceneBase {

    @FXML
    private TextField customerFirstName;
    @FXML
    private TextField customerLastName;
    @FXML
    private TextField customerEmail;

    @FXML private ListView<String> listofCustomers;


    private ObservableList<String> customers = FXCollections.observableArrayList();




    public void addCustomerButton(ActionEvent actionEvent) {
    }

    public void removeCustomerButton(ActionEvent actionEvent) {
    }

    public void editCustomerButton(ActionEvent actionEvent) {
    }
}
