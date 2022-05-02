package com.bayarkhuu.visual.labs.lab13;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Lab13App extends Application {
    private Drop drop;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final Label label = new Label("Шоо шидэх:");
        final ProgressBar progressBar = new ProgressBar(0);
        final ProgressIndicator progressIndicator = new ProgressIndicator(0);

        final Button startButton = new Button("Эхлэх");
        final Button cancelButton = new Button("Зогсоох");

        final Label statusLabel = new Label();
        statusLabel.setMinWidth(250);
        statusLabel.setTextFill(Color.BLUE);

        startButton.setOnAction(event -> {
            startButton.setDisable(true);
            progressBar.setProgress(0);
            progressIndicator.setProgress(0);
            cancelButton.setDisable(false);

            drop = new Drop();

            progressBar.progressProperty().unbind();
            progressBar.progressProperty().bind(drop.progressProperty());
            progressIndicator.progressProperty().unbind();
            progressIndicator.progressProperty().bind(drop.progressProperty());
            statusLabel.textProperty().unbind();
            statusLabel.textProperty().bind(drop.messageProperty());

            drop.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                    t -> {
                        statusLabel.textProperty().unbind();
                        statusLabel.setText("Нийт шидсэн шооны тоо: " + drop.getValue());
                    });

            new Thread(drop).start();

        });

        cancelButton.setOnAction(event -> {
            startButton.setDisable(false);
            cancelButton.setDisable(true);
            drop.cancel(true);
            progressBar.progressProperty().unbind();
            progressIndicator.progressProperty().unbind();
            statusLabel.textProperty().unbind();

            progressBar.setProgress(0);
            progressIndicator.setProgress(0);
        });

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);

        root.getChildren().addAll(label, progressBar, progressIndicator,
                statusLabel, startButton, cancelButton);

        Scene scene = new Scene(root, 515, 120);
        primaryStage.setTitle("Lab 13");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
