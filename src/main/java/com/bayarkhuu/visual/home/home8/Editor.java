package com.bayarkhuu.visual.home.home8;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Editor extends VBox {
    public Editor(String name) {
        setPadding(new Insets(10));
        setSpacing(10);

        HBox one = new HBox();
        one.setSpacing(10);

        Label label = new Label(name + ":");
        TextField input = new TextField();

        one.getChildren().addAll(label, input);

        HBox two = new HBox();
        two.setSpacing(10);

        Button btnOk = new Button("OK");
        btnOk.setPrefWidth(65);

        Button btnCancel = new Button("Cancel");
        btnCancel.setPrefWidth(65);
        btnCancel.setOnAction(e -> ((Button) e.getTarget()).getScene().getWindow().hide());

        two.setAlignment(Pos.TOP_RIGHT);
        two.getChildren().addAll(btnOk, btnCancel);
        getChildren().addAll(one, two);
    }
}
