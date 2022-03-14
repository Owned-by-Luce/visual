package com.bayarkhuu.visual.labs.lab6;

import com.bayarkhuu.visual.labs.lab5.Lab5App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lab6AppTwo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Lab6AppTwo.class.getResource("SwapDesign.fxml"));
        Scene scene = new Scene(loader.load(), 320, 175);
        primaryStage.setTitle("Swap");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
