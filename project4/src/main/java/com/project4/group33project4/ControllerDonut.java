package com.project4.group33project4;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for donut-view.fxml
 * @author Mahad Rauf, Moeez Shahid
 */
public class ControllerDonut implements Initializable {
    /** controller of the main view */
    private ControllerMain mainController;

    private static final int one = 1;
    private static final int two = 2;
    private static final int three = 3;
    private static final int four = 4;
    private static final int five = 5;
    private static final int six = 6;

    /** donut type selected: Yeast by default */
    private int donutType = Donut.YEAST;
    /** price of current donut selected */
    private double currentPrice = 1.59;
    /** current total of all donuts added to the order */
    private double currentDonutPrice;
    /** current price of the donut selected */
    private double currentSubPrice = 1.59;
    /** donut flavor selected: Sugar */
    private int donutFlavor = Donut.SUGAR;
    /** quantity of donut selected */
    private int donutQuantity = 1;

    /** group of the donut type RadioButtons: Yeast, Cake, Hole */
    @FXML
    private ToggleGroup type;

    /** group of the donut flavor RadioButtons: Sugar, Glazed, Chocolate */
    @FXML
    private ToggleGroup flavor;

    /** subtotal TextField in donut window */
    @FXML
    private TextField subtotal;

    /** item price TextField in donut window */
    @FXML
    private TextField priceItem;

    /** TextArea in donut window */
    @FXML
    private TextArea textArea;

    /**
     * sets mainController with the parameter given and also sets the donut total from it
     * @param mainController controller of main view
     */
    public void setMainController(ControllerMain mainController) {
        this.mainController = mainController;
        currentDonutPrice = mainController.getDonutTotal();
        subtotal.setText("$" + String.format("%,.2f", currentDonutPrice));
    }

    /**
     * adds the donut to the order
     * @param event upon selecting 'Order' Button
     */
    @FXML
    void onOrder(ActionEvent event){
        subtotal.clear();
        currentDonutPrice += currentPrice * donutQuantity;
        mainController.setDonutTotal(currentDonutPrice);
        String amount = "$" + String.format("%,.2f", currentDonutPrice);
        String subAmount = "$" + String.format("%,.2f", currentSubPrice);
        subtotal.appendText(amount);
        Donut toOrder = new Donut(currentSubPrice, donutQuantity, donutType, donutFlavor);
        mainController.addToOrder(toOrder);
        textArea.appendText(toOrder.toString() + " : " + subAmount + " added to order\n");
    }

    /**
     * removes the donut form the order
     * @param event upon selecting 'Remove' Button
     */
    @FXML
    void offOrder(ActionEvent event){
        Donut offOrder = new Donut(currentSubPrice, donutQuantity, donutType, donutFlavor);
        if(mainController.removeItem(offOrder)){
            subtotal.clear();
            double removePrice = currentPrice * donutQuantity;
            currentDonutPrice -= removePrice;
            mainController.setDonutTotal(currentDonutPrice);
            String amount = "$" + String.format("%,.2f", currentDonutPrice);
            String removeAmount = "$" + String.format("%,.2f", removePrice);
            subtotal.appendText(amount);
            textArea.appendText(offOrder.toString() + " : " + removeAmount + " removed from order\n");
        }
        else{
            textArea.appendText("Invalid: Order not found\n");
        }
    }

    /**
     * sets item price TextField
     */
    private void setItemPrice(){
        priceItem.clear();
        currentSubPrice = currentPrice * donutQuantity;
        String amount = "$" + String.format("%,.2f", currentSubPrice);
        priceItem.appendText(amount);
    }

    /**
     * computes item price after changing donut type and displays it
     * @param event upon selecting one of the donut type RadioButtons
     */
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
        setItemPrice();
    }

    /**
     * sets the donut flavor of the donut
     * @param event upon selecting one of the flavor RadioButtons
     */
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
        setItemPrice();
    }

    /** Donut Quantity ComboBox */
    @FXML
    private ComboBox<String> donutAmount;

    private static final ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");

    /**
     * computes the price after changing the quantity of donuts and displays it
     * @param event upon selecting anything from donut quantity ComboBox
     */
    @FXML
    void onQuantitySelect(ActionEvent event){
        String quantity = donutAmount.getSelectionModel().getSelectedItem();
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
        setItemPrice();
    }

    /**
     * Initializes the window to populate the items in the quantity ComboBox with a list
     * @param url the URL from fxml to initialize
     * @param resourceBundle the ResourceBundle from fxml to initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        donutAmount.setItems(list);
    }
}