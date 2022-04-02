package com.bayarkhuu.visual.exam.yawts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExamApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ExamApp.class.getResource("ExamDesign.fxml"));
        Scene scene = new Scene(loader.load(), 707, 828);
        primaryStage.setTitle("Grading System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
