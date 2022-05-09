package com.bayarkhuu.visual.labs.lab14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Lab14pp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Label("LAb 14"), 515, 120);
        primaryStage.setTitle("Lab 14");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
