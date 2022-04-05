package com.bayarkhuu.visual.home.home7;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * PickColorDialog
 *
 * @author Баярхүү.Лув 2022.04.05 08:55
 */
public class PickColorDialog extends Stage {

    public PickColorDialog(Stage primaryStage, Parent parent) {
        setScene(new Scene(parent, 150, 30));
        setTitle("Pick a Color");
        initOwner(primaryStage);
        initModality(Modality.WINDOW_MODAL);
        show();
    }
}
