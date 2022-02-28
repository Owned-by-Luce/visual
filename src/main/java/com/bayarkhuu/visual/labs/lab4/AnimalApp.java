package com.bayarkhuu.visual.labs.lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AnimalApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(AnimalController.class.getResource("AnimalDesign.fxml"));
        Scene scene = new Scene(loader.load(), 200, 150);
        primaryStage.setTitle("Radio");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
