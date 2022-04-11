package com.project4.group33project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for main-view.fxml
 * @author Mahad Rauf, Moeez Shahid
 */
public class ControllerMain {
    /** parent of new window */
    private Parent root;
    /** stage of new window */
    private Stage stage;
    /** stores all orders placed */
    private StoreOrders orders = new StoreOrders();
    /** order to be placed */
    private Order order = new Order();
    /** total price of all the donuts in the current order */
    private double donutTotal = 0;

    private static final double WINDOW_LOCATION = 0;

    /**
     * adds parameter MenuItem to order
     * @param item item to add
     */
    public void addToOrder(MenuItem item){
        order.add(item);
    }

    /**
     * sets donutTotal to parameter amount
     * @param amount amount to set donutTotal
     */
    public void setDonutTotal(double amount){
        this.donutTotal = amount;
    }

    /**
     * return the value of donutTotal
     * @return current value in donutTotal
     */
    public double getDonutTotal(){
        return this.donutTotal;
    }

    /**
     * add the current order to set of all orders
     * @param amount total price of the order
     */
    public void addOrder(double amount){
        order.setTotal(amount);
        orders.add(order);
        this.donutTotal = 0;
        this.order = new Order();
    }

    /**
     * removes parameter order from all orders
     * @param toRemove order to remove
     * @return true of removed, false otherwise
     */
    public boolean removeOrder(Order toRemove){
        return this.orders.remove(toRemove);
    }

    /**
     * removes parameter MenuItem form current order
     * @param toRemove item to remove
     * @return true if removed, false otherwise
     */
    public boolean removeItem(MenuItem toRemove){
        return this.order.remove(toRemove);
    }

    /**
     * returns all the orders currently placed
     * @return all the orders currently placed
     */
    public StoreOrders getOrders(){
        return this.orders;
    }

    /**
     * returns the current order
     * @return the current order
     */
    public Order getOrder(){
        return this.order;
    }

    /**
     * loads the coffee window
     * @param event upon selecting 'Add Coffee to Order' Button
     * @throws IOException throws Exception to caller when encountered
     */
    @FXML
    void addCoffee(ActionEvent event) throws IOException {
        load("coffee-view.fxml", "Coffee");
    }

    /**
     * loads the donut window
     * @param event upon selecting 'Add Donuts to Order' Button
     * @throws IOException throws Exception to caller when encountered
     */
    @FXML
    void addDonuts(ActionEvent event) throws IOException{
        load("donut-view.fxml", "Donuts");
    }

    /**
     * loads the order window
     * @param event upon selecting 'View Current Order' Button
     * @throws IOException throws Exception to caller when encountered
     */
    @FXML
    void viewAllOrders(ActionEvent event) throws IOException{
        load("all-orders-view.fxml", "All Orders");
    }

    /**
     * loads the all orders window
     * @param event upon selecting 'View All Orders' Button
     * @throws IOException throws Exception to caller when encountered
     */
    @FXML
    void viewOrder(ActionEvent event) throws IOException{
        load("order-view.fxml", "Current Order");
    }

    /**
     * loads the window based on the given parameters
     * @param fxml name of .fxml file
     * @param title title of the stage
     * @throws IOException throws Exception to caller when encountered
     */
    private void load(String fxml, String title) throws IOException{
        if(stage != null){
            stage.close();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setX(WINDOW_LOCATION);
        stage.setY(WINDOW_LOCATION);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        if(title.equals("Coffee")){
            ControllerCoffee controller = (ControllerCoffee) fxmlLoader.getController();
            controller.setMainController(this);
        }else if(title.equals("Donuts")){
            ControllerDonut controller = (ControllerDonut) fxmlLoader.getController();
            controller.setMainController(this);
        }else if(title.equals("Current Order")){
            ControllerOrder controller = (ControllerOrder) fxmlLoader.getController();
            controller.setMainController(this);
        }else if(title.equals("All Orders")){
            ControllerAllOrders controller = (ControllerAllOrders) fxmlLoader.getController();
            controller.setMainController(this);
        }
        stage.show();
    }
}