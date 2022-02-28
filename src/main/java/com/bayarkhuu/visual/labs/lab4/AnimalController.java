package com.bayarkhuu.visual.labs.lab4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AnimalController extends Application {

    @FXML
    private ImageView imageView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(AnimalController.class.getResource("AnimalDesign.fxml"));
        Scene scene = new Scene(loader.load(), 450, 300);
        primaryStage.setTitle("Radio");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    protected void change(ActionEvent e) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("F:/" + ((Labeled) e.getSource()).getText() + ".gif");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        Image image = new Image(inputStream);
        imageView.setImage(image);
    }
}
