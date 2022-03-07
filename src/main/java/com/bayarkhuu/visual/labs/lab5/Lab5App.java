package com.bayarkhuu.visual.labs.lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Lab5App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Lab5App.class.getResource("CalculatorDesign.fxml"));
        Scene scene = new Scene(loader.load(), 320, 175);
        primaryStage.setTitle("Tax calculation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
