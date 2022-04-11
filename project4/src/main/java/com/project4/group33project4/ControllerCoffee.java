package com.project4.group33project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;

import java.util.HashSet;

/**
 * Controller for coffee-view.fxml
 * @author Mahad Rauf, Moeez Shahid
 */
public class ControllerCoffee {
    /** controller of the main view */
    private ControllerMain mainController;
    /** Base price of Short coffee with no add-ins */
    private double currentPrice = 1.69;
    /** Base size of the coffee: Short */
    private int coffeeSize = Coffee.SHORT;
    /** add-ins ot be added to the coffee */
    private HashSet<String> addIns = new HashSet<String>();

    private static final double BASE_PRICE = 1.69;
    private static final double ADD_IN_PRICE = Coffee.ADD_IN_PRICE;
    private static final double SIZE_INCREASE_PRICE = Coffee.SIZE_INCREASE_PRICE;

    /**
     * Group of the size RadioButtons: Short, Tall, Grande, and Venti
     */
    @FXML
    private ToggleGroup size;

    /**
     * subtotal TextField in Coffee window
     */
    @FXML
    private TextField subtotal;

    /**
     * TextArea in coffee window
     */
    @FXML
    private TextArea textArea;

    /**
     * sets mainController with the parameter given
     * @param mainController controller of main view
     */
    public void setMainController(ControllerMain mainController) {
        this.mainController = mainController;
    }

    /**
     * adds the coffee to the order
     * @param event upon selecting 'Order' Button
     */
    @FXML
    void onOrder(ActionEvent event){
        Coffee toOrder = new Coffee(currentPrice, coffeeSize);
        for(String s : addIns){
            toOrder.add(s);
        }
        mainController.addToOrder(toOrder);
        textArea.appendText( toOrder.toString() + " added to order\n");
    }

    /**
     * Computes the price after selecting a size
     * @param event upon selecting any of the 4 size RadioButtons
     */
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
        setPrice();
    }

    /**
     * Computes the price after selecting/deselecting an add-in
     * @param event upon selecting and of the five add-in CheckBoxes
     */
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
        setPrice();
    }

    /**
     * sets the price in the subtotal TextField
     */
    private void setPrice(){
        subtotal.clear();
        String amount = "$" + String.format("%,.2f", currentPrice);
        subtotal.appendText(amount);
    }

}
