package com.project4.group33project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ControllerDonut {
    private ControllerMain mainController;
    private static final int one = 1;
    private static final int two = 2;
    private static final int three = 3;
    private int donutType = 0;
    private double currentPrice = 1.59;
    private double currentDonutPrice;
    private int donutFlavor = 3;
    private int donutQuantity = 1;


    @FXML
    private ToggleGroup type;

    @FXML
    private ToggleGroup flavor;

    @FXML
    private ToggleGroup quantity;

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
        textArea.appendText("Donut(s): " + toOrder.toString() + " added to order\n");
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
        currentDonutPrice = currentPrice * donutQuantity;
        String amount = "$" + String.format("%,.2f", currentDonutPrice);
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
        currentDonutPrice = currentPrice * donutQuantity;
        String amount = "$" + String.format("%,.2f", currentDonutPrice);
        subtotal.appendText(amount);
    }
    @FXML
    void onQuantitySelect(ActionEvent event){
        RadioButton selected = (RadioButton) quantity.getSelectedToggle();
        String quantity = selected.getText();
        if(quantity.equals("1")){
            donutQuantity = one;
        }else if(quantity.equals("2")){
            donutQuantity = two;
        }else if(quantity.equals("3")){
            donutQuantity = three;

        }

        subtotal.clear();// make this a method
        currentDonutPrice = currentPrice * donutQuantity;
        String amount = "$" + String.format("%,.2f", currentDonutPrice);
        subtotal.appendText(amount);
    }
}
