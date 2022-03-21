package com.bayarkhuu.visual.labs.lab7;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lab7AppScene extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setScene(getMainScene("..."));
        primaryStage.show();
    }

    private Scene getMainScene(String from) {
        VBox root = getRoot("Main Window", from);

        Button next = new Button("Next");
        next.setOnAction(e -> primaryStage.setScene(getChild1("Main")));

        root.getChildren().addAll(next);
        return new Scene(root, 300, 100);
    }

    private Scene getChild1(String from) {
        VBox root = getRoot("Child1", from);

        Button back = new Button("Back");
        back.setOnAction(e -> primaryStage.setScene(getMainScene("Child1")));

        Button next = new Button("Next");
        next.setOnAction(e -> primaryStage.setScene(getChild2()));

        root.getChildren().add(new HBox(back, next));
        return new Scene(root, 300, 100);
    }

    private Scene getChild2() {
        VBox root = getRoot("Child2", "Child1");

        Button back = new Button("Back");
        back.setOnAction(e -> primaryStage.setScene(getChild1("Child2")));

        root.getChildren().add(back);
        return new Scene(root, 300, 100);
    }

    private VBox getRoot(String caption, String from) {
        Label captionLabel = new Label(caption);
        captionLabel.setStyle("-fx-border-color: transparent transparent black transparent;");
        captionLabel.setPrefWidth(300);
        captionLabel.setAlignment(Pos.CENTER);
        return new VBox(captionLabel, new Label("Hello from " + from));
    }
}
