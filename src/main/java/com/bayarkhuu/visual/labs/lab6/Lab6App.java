package com.bayarkhuu.visual.labs.lab6;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lab6App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        ListView<String> listView = new ListView<>();
        Label label = new Label();

        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        listView.setItems(list);

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends String> c) -> {
            label.setText(String.join(", ", c.getList()));
        });

        root.getChildren().addAll(listView, label);

        Scene scene = new Scene(root, 320, 175);
        primaryStage.setTitle("ListView");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
