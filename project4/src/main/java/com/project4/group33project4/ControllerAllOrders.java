package com.project4.group33project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class ControllerAllOrders {
    private ControllerMain mainController;
    private boolean remove = false;
    public static final String EXPORT_PATH = "orders.txt";
    @FXML
    private ListView<Order> listAllOrders;

    @FXML
    private TextArea textArea;

    /*@FXML
    private Button printButton;*/

    @FXML
    private Button removeButton;

    @FXML
    private Button exportButton;



    public void setMainController(ControllerMain mainController) {
        this.mainController = mainController;
    }


    @FXML
    public void showAllOrders(ActionEvent event){
        StoreOrders allOrders = mainController.getOrders();
        ArrayList<Order> allOrder = allOrders.getOrders();
        if(allOrder.size() == 0){
            removeButton.setDisable(true);
            exportButton.setDisable(true);
            if(!remove){
                textArea.appendText("No orders currently placed.\n");
            }
        }else{
            removeButton.setDisable(false);
            exportButton.setDisable(false);
        }
        ObservableList<Order> observableList = FXCollections.observableList(allOrder);
        listAllOrders.setItems(observableList);
    }

    @FXML
    public void removeOrders(ActionEvent event){
        Order toRemove = listAllOrders.getSelectionModel().getSelectedItem();
        if(toRemove == null){
            textArea.appendText("No item selected to remove.\n");
            return;
        }
        textArea.appendText("Order Number: " + toRemove.getOrderNum() + " cancelled.\n");
        mainController.removeOrder(toRemove);
        remove = true;
        showAllOrders(event);
        remove = false;
    }
    @FXML
    public void exportOrders(ActionEvent event) {
        textArea.appendText(mainController.getOrders().export(EXPORT_PATH));
    }
}