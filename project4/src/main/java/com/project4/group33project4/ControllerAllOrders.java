package com.project4.group33project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

/**
 * Controller for all-orders-view.fxml
 * @author Mahad Rauf, Moeez Shahid
 */
public class ControllerAllOrders {
    /** controller of the main view */
    private ControllerMain mainController;
    /** boolean to suppress an output when a remove is being done */
    private boolean remove = false;

    public static final String EXPORT_PATH = "orders.txt";

    /** ListView in all orders window */
    @FXML
    private ListView<Order> listAllOrders;

    /** TextArea in all orders window */
    @FXML
    private TextArea textArea;

    /** remove Button in all orders window */
    @FXML
    private Button removeButton;

    /** export Button in all orders window */
    @FXML
    private Button exportButton;

    /**
     * sets mainController with the parameter given
     * @param mainController controller of main view
     */
    public void setMainController(ControllerMain mainController) {
        this.mainController = mainController;
    }

    /**
     * Shows all orders currently placed in the listView
     * @param event upon selecting 'Show All Orders' Button
     */
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

    /**
     * Removes selected order from orders
     * @param event upon selecting 'Remove Order' Button
     */
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
        listAllOrders.getSelectionModel().clearSelection();
    }

    /**
     * Exports all orders to a .txt file
     * @param event upon selecting 'Print Orders' Button
     */
    @FXML
    public void exportOrders(ActionEvent event) {
        textArea.appendText(mainController.getOrders().export(EXPORT_PATH));
    }
}