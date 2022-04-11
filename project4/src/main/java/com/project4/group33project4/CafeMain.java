package com.project4.group33project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main for cafe application
 * @author Mahad Rauf, Moeez Shahid
 */
public class CafeMain extends Application {
    private static final double DIMENSION1 = 600;
    private static final double DIMENSION2 = 400;

    /**
     * Overrides start method
     * @param stage stage to be set
     * @throws IOException returns Exception to caller when encountered
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeMain.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), DIMENSION1, DIMENSION2);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * launches the cafe application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}