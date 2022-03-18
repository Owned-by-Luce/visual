package com.bayarkhuu.visual.home.home5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Home5App
 *
 * @author Баярхүү.Лув, 2022.03.18 14:46
 */
public class Home5App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Home5App.class.getResource("FontDesign.fxml"));
        Scene scene = new Scene(loader.load(), 414, 325);
        primaryStage.setTitle("Font");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
