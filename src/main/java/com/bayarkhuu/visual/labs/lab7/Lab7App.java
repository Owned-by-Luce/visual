package com.bayarkhuu.visual.labs.lab7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Lab7App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Label("hi"), 320, 175);
        primaryStage.setTitle("lab 7");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
