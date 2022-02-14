package com.bayarkhuu.visual.lab1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 2022.02.07 (Лаборатори - 1)
 */
public class Lab1Application extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        //Ташуу зураас
        root.getChildren().add(new Line(0, 0, 450, 450));

        //Том дөрвөлжин
        Rectangle rectangle = new Rectangle(0, 0, 450, 450);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.TRANSPARENT);
        root.getChildren().add(rectangle);

        //Жижиг дөрвөлжин
        Rectangle rectangleSmall = new Rectangle(0, 0, 400, 400);
        rectangleSmall.setStroke(Color.BLACK);
        rectangleSmall.setFill(Color.TRANSPARENT);
        root.getChildren().add(rectangleSmall);

        //Тойрог
        Circle circle = new Circle(150, 150, 100);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.TRANSPARENT);
        root.getChildren().add(circle);

        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        primaryStage.show();
    }
}