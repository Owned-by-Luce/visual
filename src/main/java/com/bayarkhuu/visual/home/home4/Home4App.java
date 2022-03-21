package com.bayarkhuu.visual.home.home4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Home4App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Home4App.class.getResource("InterestDesign.fxml"));
        Scene scene = new Scene(loader.load(), 375, 255);
        primaryStage.setTitle("Simple Interest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
