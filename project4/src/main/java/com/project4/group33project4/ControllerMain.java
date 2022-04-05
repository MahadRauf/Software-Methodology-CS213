package com.project4.group33project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerMain {
    private Parent root;
    private Stage stage;
    private StoreOrders orders = new StoreOrders();
    private Order order = new Order();

    public void addToOrder(MenuItem item){
        order.add(item);
    }

    public void addOrder(){
        orders.add(order);
        this.order = new Order();
    }

    // MOEEZ LOOK HERE
    // this might be useful for the view all orders part so putting it in now
    public boolean removeOrder(Order toRemove){
        return this.orders.remove(toRemove);
    }

    // MOEEZ LOOK HERE
    // this might be useful for removing donuts
    public boolean removeItem(MenuItem toRemove){
        return this.order.remove(toRemove);
    }

    // MOEEZ LOOK HERE
    // this might be useful for the view all orders part so putting it in now
    public StoreOrders getOrders(){
        return this.orders;
    }

    public Order getOrder(){
        return this.order;
    }

    @FXML
    void addCoffee(ActionEvent event) throws IOException {
        if(stage != null){
            stage.close();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setX(0);
        stage.setY(0);// make this not magic number \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        stage.setTitle("Coffee");
        stage.setScene(new Scene(root));
        ControllerCoffee controller = (ControllerCoffee) fxmlLoader.getController();
        controller.setMainController(this);
        stage.show();
    }

    @FXML
    void addDonuts(ActionEvent event) throws IOException{
        if(stage != null){
            stage.close();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setX(0);
        stage.setY(0);// make this not magic number \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        stage.setTitle("Donuts");
        stage.setScene(new Scene(root));
        ControllerDonut controller = (ControllerDonut) fxmlLoader.getController();
        controller.setMainController(this);
        stage.show();
    }

    @FXML
    void viewAllOrders(ActionEvent event) throws IOException{
        if(stage != null){
            stage.close();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("all-orders-view.fxml"));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setX(0);
        stage.setY(0);// make this not magic number \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        stage.setTitle("All Orders");
        stage.setScene(new Scene(root));
        ControllerAllOrders controller = (ControllerAllOrders) fxmlLoader.getController();
        controller.setMainController(this);
        stage.show();
    }

    @FXML
    void viewOrder(ActionEvent event) throws IOException{
        if(stage != null){
            stage.close();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order-view.fxml"));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setX(0);
        stage.setY(0);// make this not magic number \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        stage.setTitle("Current Order");
        stage.setScene(new Scene(root));
        ControllerOrder controller = (ControllerOrder) fxmlLoader.getController();
        controller.setMainController(this);
        stage.show();
    }
}