package com.project4.group33project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ControllerAllOrders {
    private ControllerMain mainController;
    public static final String EXPORT_PATH = "orders.txt";
    @FXML
    private ListView<Order> listAllOrders;

    @FXML
    private TextField subtotal;

    @FXML
    private TextField tax;

    @FXML
    private TextField total;

    @FXML
    private TextArea textArea;

    @FXML
    private Button printButton;

    @FXML
    private Button removeButton;



    public void setMainController(ControllerMain mainController) {
        this.mainController = mainController;
    }


    @FXML
    public void showAllOrders(ActionEvent event){
        StoreOrders allOrders = mainController.getOrders();
        ArrayList<Order> allOrder = allOrders.getOrders();
        ObservableList<Order> observableList = FXCollections.observableList(allOrder);
        listAllOrders.setItems(observableList);
    }

    @FXML
    public void removeOrders(ActionEvent event){
        Order toRemove = listAllOrders.getSelectionModel().getSelectedItem();
        mainController.removeOrder(toRemove);
        showAllOrders(event);
    }
    @FXML
    public void exportOrders(ActionEvent event) {

        mainController.getOrders().export(EXPORT_PATH);
        
    }
}
