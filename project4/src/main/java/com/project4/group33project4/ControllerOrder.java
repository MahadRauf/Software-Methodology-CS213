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

/**
 * Controller for order-view.fxml
 * @author Mahad Rauf, Moeez Shahid
 */
public class ControllerOrder {
    /** controller of the main view */
    private ControllerMain mainController;
    /** boolean to suppress an output */
    private boolean suppress = false;
    /** total price of the order with tax */
    private double totl = 0;

    private static final double SALES_TAX = 0.06625;

    /**
     * ListView in order window
     */
    @FXML
    private ListView<MenuItem> orderItems;

    /**
     * subtotal TextField in order window
     */
    @FXML
    private TextField subtotal;

    /**
     * sales tax TextField in order window
     */
    @FXML
    private TextField tax;

    /**
     * total TextField in order window
     */
    @FXML
    private TextField total;

    /**
     * TextArea in order window
     */
    @FXML
    private TextArea textArea;

    /**
     * 'Place Order' Button in order window
     */
    @FXML
    private Button orderButton;

    /**
     * 'Remove Selected Item' Button in order window
     */
    @FXML
    private Button removeButton;

    /**
     * adds the order to all orders
     * @param event upon selecting 'Place Order' Button
     */
    @FXML
    void addOrder(ActionEvent event) {
        mainController.addOrder(totl);
        textArea.appendText("Order has been placed.\n");
        suppressedShow(event);
    }

    /**
     * removes selected item in ListView from order
     * @param event upon selecting 'Remove Selected Item' Button
     */
    @FXML
    void removeItem(ActionEvent event) {
        MenuItem toRemove = orderItems.getSelectionModel().getSelectedItem();
        if(toRemove == null){
            textArea.appendText("No item selected to remove.\n");
            return;
        }
        mainController.removeItem(toRemove);
        textArea.appendText(toRemove.toString() + " removed from order.\n");
        suppressedShow(event);
        orderItems.getSelectionModel().clearSelection();
    }

    /**
     * shows the items in the order while suppressing an output
     * @param event from removeItem or addOrder
     */
    private void suppressedShow(ActionEvent event){
        this.suppress = true;
        showOrder(event);
        this.suppress = false;
    }

    /**
     * sets mainController with the parameter given
     * @param mainController controller of main view
     */
    public void setMainController(ControllerMain mainController) {
        this.mainController = mainController;
    }

    /**
     * Puts all the items in the current order into the ListView and enables buttons
     * @param event upon selecting 'Show Current Order' Button
     */
    @FXML
    void showOrder(ActionEvent event) {
        Order currentOrder = mainController.getOrder();
        ArrayList<MenuItem> items = currentOrder.getItems();

        ObservableList<MenuItem> observableList = FXCollections.observableList(items);
        orderItems.setItems(observableList);
        double subTot = 0;
        for(MenuItem i : items){
            subTot += i.itemPrice();
        }
        double sTax = subTot * SALES_TAX;
        this.totl = subTot + sTax;
        subtotal.setText("$" + String.format("%,.2f", subTot));
        tax.setText("$" + String.format("%,.2f", sTax));
        total.setText("$" + String.format("%,.2f", this.totl));
        if(items.size() == 0){
            orderButton.setDisable(true);
            removeButton.setDisable(true);
            if(!suppress){
                textArea.appendText("No items currently in order.\n");
            }
            return;
        }
        orderButton.setDisable(false);
        removeButton.setDisable(false);
    }

}
