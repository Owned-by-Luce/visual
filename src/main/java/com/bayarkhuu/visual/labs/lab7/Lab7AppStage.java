package com.bayarkhuu.visual.labs.lab7;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Lab7AppStage extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setScene(new Scene(new Label("HAHa"), 300, 150));
        primaryStage.show();

        openMain("...");
    }

    private void openMain(String from) {
        VBox root = getRoot("Main Window", from);

        Button next = new Button("Next");
        next.setOnAction(e -> openChild1("Main"));

        root.getChildren().addAll(next);

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 150));
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    private void openChild1(String from) {
        Stage stage = new Stage();

        VBox root = getRoot("Child1", from);

        Button back = new Button("Back");
        back.setOnAction(e -> {
            openMain("Child1");
            stage.close();
        });

        Button next = new Button("Next");
        next.setOnAction(e -> openChild2("Child1"));

        root.getChildren().add(new HBox(back, next));

        stage.setScene(new Scene(root, 300, 150));
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    private void openChild2(String from) {
        Stage stage = new Stage();
        VBox root = getRoot("Child2", from);

        Button back = new Button("Back");
        back.setOnAction(e -> {
            openChild1("Child2");
            stage.close();
        });

        root.getChildren().add(back);

        stage.setScene(new Scene(root, 300, 150));
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    private VBox getRoot(String caption, String from) {
        Label captionLabel = new Label(caption);
        captionLabel.setStyle("-fx-border-color: transparent transparent black transparent;");
        captionLabel.setPrefWidth(300);
        captionLabel.setAlignment(Pos.CENTER);
        return new VBox(captionLabel, new Label("Hello from " + from));
    }
}