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

public class ControllerOrder {
    private ControllerMain mainController;
    private boolean orderPlaced = false;
    private static final double SALES_TAX = 0.06625;

    @FXML
    private ListView<MenuItem> orderItems;

    @FXML
    private TextField subtotal;

    @FXML
    private TextField tax;

    @FXML
    private TextField total;

    @FXML
    private TextArea textArea;

    @FXML
    private Button orderButton;

    @FXML
    private Button removeButton;

    @FXML
    void addOrder(ActionEvent event) {
        mainController.addOrder();
        this.orderPlaced = true;
        textArea.appendText("Order has been placed.\n");
        showOrder(event);
        this.orderPlaced = false;
    }

    @FXML
    void removeItem(ActionEvent event) {
        MenuItem toRemove = orderItems.getSelectionModel().getSelectedItem();
        mainController.removeItem(toRemove);
        showOrder(event);
    }

    public void setMainController(ControllerMain mainController) {
        this.mainController = mainController;
    }

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
        double tot = subTot + sTax;
        subtotal.setText("$" + String.format("%,.2f", subTot));
        tax.setText("$" + String.format("%,.2f", sTax));
        total.setText("$" + String.format("%,.2f", tot));
        if(items.size() == 0){
            orderButton.setDisable(true);
            removeButton.setDisable(true);
            if(!orderPlaced){
                textArea.appendText("No items currently in order.\n");
            }
            return;

        }
        orderButton.setDisable(false);
        removeButton.setDisable(false);
    }

}
