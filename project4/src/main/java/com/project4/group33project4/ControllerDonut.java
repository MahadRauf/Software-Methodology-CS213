package com.project4.group33project4;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDonut implements Initializable {
    private ControllerMain mainController;
    private static final int one = 1;
    private static final int two = 2;
    private static final int three = 3;
    private static final int four = 4;
    private static final int five = 5;
    private static final int six = 6;
    private int donutType = 0;
    private double currentPrice = 1.59;
    private double currentDonutPrice;
    private double currentSubPrice = 1.59;
    private double removePrice;
    private int donutFlavor = 3;
    private int donutQuantity = 1;


    @FXML
    private ToggleGroup type;

    @FXML
    private ToggleGroup flavor;

    @FXML
    private TextField subtotal;

    @FXML
    private TextArea textArea;

    public void setMainController(ControllerMain mainController) {
        this.mainController = mainController;
    }
    @FXML
    void onOrder(ActionEvent event){
        Donut toOrder = new Donut(currentPrice, donutQuantity, donutType, donutFlavor);
        mainController.addToOrder(toOrder);
        subtotal.clear();
        currentDonutPrice += currentPrice * donutQuantity;
        String amount = "$" + String.format("%,.2f", currentDonutPrice);
        String subAmount = "$" + String.format("%,.2f", currentSubPrice);
        subtotal.appendText(amount);
        textArea.appendText("Donut(s): " + toOrder.toString() + " : " + subAmount + " added to order\n");
    }

    @FXML
    void offOrder(ActionEvent event){
        Donut offOrder = new Donut(currentPrice, donutQuantity, donutType, donutFlavor);
        if(mainController.removeItem(offOrder)){
            subtotal.clear();
            removePrice = currentPrice * donutQuantity;
            currentDonutPrice -= removePrice;
            String amount = "$" + String.format("%,.2f", currentDonutPrice);
            String removeAmount = "$" + String.format("%,.2f", removePrice);
            subtotal.appendText(amount);
            textArea.appendText("Donut(s): " + offOrder.toString() + " : " + removeAmount + " removed from order\n");
        }
        else{
            textArea.appendText("Invalid: Order not found\n");
        }
    }

    @FXML
    void onTypeSelect(ActionEvent event){
        RadioButton selected = (RadioButton) type.getSelectedToggle();
        String type = selected.getText();
        if(type.equals("YEAST")){
            donutType = Donut.YEAST;
            currentPrice = Donut.YEAST_PRICE;
        }else if(type.equals("CAKE")){
            donutType = Donut.CAKE;
            currentPrice = Donut.CAKE_PRICE;
        }else if(type.equals("HOLE")){
            donutType = Donut.HOLE;
            currentPrice = Donut.HOLE_PRICE;
        }
        subtotal.clear();// make this a method
        currentSubPrice = currentPrice * donutQuantity;
        String amount = "$" + String.format("%,.2f", currentSubPrice);
        subtotal.appendText(amount);
    }
    @FXML
    void onFlavorSelect(ActionEvent event){
        RadioButton selected = (RadioButton) flavor.getSelectedToggle();
        String flavor = selected.getText();
        if(flavor.equals("SUGAR")){
            donutFlavor = Donut.SUGAR;
        }else if(flavor.equals("GLAZED")){
            donutFlavor = Donut.GLAZED;
        }else if(flavor.equals("CHOCOLATE")){
            donutFlavor = Donut.CHOCOLATE;

        }

        subtotal.clear();// make this a method
        currentSubPrice = currentPrice * donutQuantity;
        String amount = "$" + String.format("%,.2f", currentSubPrice);
        subtotal.appendText(amount);
    }
    @FXML
    private ComboBox<String> donutAmount;
    ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");

    @FXML
    void onQuantitySelect(ActionEvent event){
        String quantity = donutAmount.getSelectionModel().getSelectedItem();
        System.out.println(quantity);
        if(quantity.equals("1")){
            donutQuantity = one;
        }else if(quantity.equals("2")){
            donutQuantity = two;
        }else if(quantity.equals("3")){
            donutQuantity = three;
        }else if(quantity.equals("4")){
            donutQuantity = four;
        }else if(quantity.equals("5")){
            donutQuantity = five;
        }else if(quantity.equals("6")){
            donutQuantity = six;
        }
        subtotal.clear();// make this a method
        currentSubPrice = currentPrice * donutQuantity;
        String amount = "$" + String.format("%,.2f", currentSubPrice);
        subtotal.appendText(amount);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        donutAmount.setItems(list);
    }
}
