package com.bayarkhuu.visual.home.home6;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Editor
 *
 * @author Баярхүү.Лув 2022.03.30 10:53
 */
public record Editor(String fxml, double w, double h) implements Creatable {

    @Override
    public void init(String title, Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(Editor.class.getResource(fxml));

        VBox root = new VBox();
        Button btnOk = new Button("OK");
        Button btnCancel = new Button("Cancel");

        HBox box = new HBox(btnOk, btnCancel);
        box.setPadding(new Insets(0, 15, 15, 15));
        box.setSpacing(10);
        box.setAlignment(Pos.TOP_RIGHT);

        try {
            root.getChildren().addAll(loader.load(), box);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle("Ceil Inn - " + title + " editor");
        stage.setScene(new Scene(root, w, h));
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();

        btnOk.setOnAction(e -> stage.close());
        btnCancel.setOnAction(e -> stage.close());
    }
}
