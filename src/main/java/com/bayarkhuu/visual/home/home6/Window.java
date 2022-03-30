package com.bayarkhuu.visual.home.home6;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Window
 *
 * @author Баярхүү.Лув 2022.03.30 10:29
 */
public class Window<T> extends VBox {
    public TableCreator<T> table = new TableCreator<>();

    public Window(String title, Editor editor, double w, double h) {
        setPadding(new Insets(15));
        setSpacing(12);

        Button btnNew = new Button("New " + title);
        Button btnClose = new Button("Close");

        HBox box = new HBox(btnNew, btnClose);
        box.setSpacing(8);
        box.setAlignment(Pos.TOP_RIGHT);

        getChildren().addAll(table, box);

        Stage stage = new Stage();
        stage.setTitle("Ceil Inn - " + title);
        stage.setScene(new Scene(this, w, h));
        stage.initOwner(Home6App.getPrimaryStage());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();

        btnNew.setOnAction(e -> editor.init(title, stage));
        btnClose.setOnAction(e -> stage.close());
    }
}
