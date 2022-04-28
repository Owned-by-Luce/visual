package com.bayarkhuu.visual.home.home8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home8App extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Home8App.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(Home8App.class.getResource("PartView.fxml"));
        Scene scene = new Scene(loader.load(), 809,658);
        primaryStage.setTitle("College Park Auto-Parts");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
