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


    @FXML
    void addCoffee(ActionEvent event) throws IOException {
        if(stage != null){
            stage.close();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
        root = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setY(stage.getY() + 700);
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
        stage.setY(stage.getY() + 700);
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
        stage.setY(stage.getY() + 700);
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
        stage.setY(stage.getY() + 700);
        stage.setTitle("Current Order");
        stage.setScene(new Scene(root));
        ControllerOrder controller = (ControllerOrder) fxmlLoader.getController();
        controller.setMainController(this);
        stage.show();
    }
}