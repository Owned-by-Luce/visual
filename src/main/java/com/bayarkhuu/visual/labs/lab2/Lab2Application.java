package com.bayarkhuu.visual.labs.lab2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class Lab2Application extends Application {
    private final List<List<String>> captions = Arrays.asList(
            Arrays.asList("<-", "C", "%", "+"),
            Arrays.asList("7", "8", "9", "-"),
            Arrays.asList("4", "5", "6", "*"),
            Arrays.asList("1", "2", "3", "/"),
            Arrays.asList("0", ".", "+-", "=")
    );

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getCalculatorBody(), 226, 326);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent getCalculatorBody() {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(12);

        TextField inputTf = new TextField();
        inputTf.setAlignment(Pos.CENTER_RIGHT);
        inputTf.setFont(new Font(18));

        root.getChildren().addAll(inputTf, getButtonsTilePane());
        return root;
    }

    /**
     * TilePane-д товчлуурийг үүсгэж нэмэх
     *
     * @return TilePane
     */
    private Parent getButtonsTilePane() {
        TilePane root = new TilePane();
        root.setHgap(2);
        root.setVgap(2);

        for (List<String> caption : captions) {
            for (String s : caption) {
                Button btn = new Button(s);
                btn.setPrefWidth(50);
                btn.setPrefHeight(50);
                btn.setFont(new Font(18));
                btn.setStyle("-fx-font-weight: bold");
                root.getChildren().add(btn);
            }
        }

        return root;
    }
}
