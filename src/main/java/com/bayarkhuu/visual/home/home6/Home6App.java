package com.bayarkhuu.visual.home.home6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home6App extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Home6App.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Home6App.class.getResource("HotelDesign.fxml"));
        Scene scene = new Scene(loader.load(), 417, 260);
        primaryStage.setTitle("Ceil Inn");
        primaryStage.setScene(scene);
        primaryStage.show();
        setPrimaryStage(primaryStage);
    }
}
