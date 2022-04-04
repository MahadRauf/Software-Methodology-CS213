package com.project4.group33project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;

import java.util.HashSet;

public class ControllerCoffee {
    private ControllerMain mainController;
    private double currentPrice = 1.69;
    private int coffeeSize = 0;
    private HashSet<String> addIns = new HashSet<String>();

    private static final double BASE_PRICE = 1.69;
    private static final double ADD_IN_PRICE = Coffee.ADD_IN_PRICE;
    private static final double SIZE_INCREASE_PRICE = Coffee.SIZE_INCREASE_PRICE;

    @FXML
    private ToggleGroup size;

    @FXML
    private TextField subtotal;

    @FXML
    private TextArea textArea;

    public void setMainController(ControllerMain mainController) {
        this.mainController = mainController;
    }

    @FXML
    void onOrder(ActionEvent event){
        Coffee toOrder = new Coffee(currentPrice, coffeeSize);
        for(String s : addIns){
            toOrder.add(s);
        }
        mainController.addToOrder(toOrder);
        textArea.appendText( toOrder.toString() + " added to order\n");
    }

    @FXML
    void onSizeSelect(ActionEvent event){
        RadioButton selected = (RadioButton) size.getSelectedToggle();
        String size = selected.getText();
        if(size.equals("Short")){
            coffeeSize = Coffee.SHORT;
            currentPrice = BASE_PRICE + addIns.size() * ADD_IN_PRICE;
        }else if(size.equals("Tall")){
            coffeeSize = Coffee.TALL;
            currentPrice = BASE_PRICE + Coffee.TALL * SIZE_INCREASE_PRICE + addIns.size() * ADD_IN_PRICE;
        }else if(size.equals("Grande")){
            coffeeSize = Coffee.GRANDE;
            currentPrice = BASE_PRICE + Coffee.GRANDE * SIZE_INCREASE_PRICE + addIns.size() * ADD_IN_PRICE;
        }else if(size.equals("Venti")){
            coffeeSize = Coffee.VENTI;
            currentPrice = BASE_PRICE + Coffee.VENTI * SIZE_INCREASE_PRICE + addIns.size() * ADD_IN_PRICE;
        }
        subtotal.clear();// make this a method
        String amount = "$" + String.format("%,.2f", currentPrice);
        subtotal.appendText(amount);
    }

    @FXML
    void onAddInSelect(ActionEvent event){
        CheckBox action = (CheckBox) event.getSource();
        if(action.isSelected()){
            addIns.add(action.getText());
            currentPrice += ADD_IN_PRICE;
        }else{
            addIns.remove(action.getText());
            currentPrice -= ADD_IN_PRICE;
        }
        subtotal.clear();// make this a method
        String amount = "$" + String.format("%,.2f", currentPrice);
        subtotal.appendText(amount);
    }


}
