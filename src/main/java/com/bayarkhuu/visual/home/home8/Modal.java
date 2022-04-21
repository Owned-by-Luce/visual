package com.bayarkhuu.visual.home.home8;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Modal extends Stage {

    public Modal(String title, Parent component, double w, double h) {
        setTitle(title);
        setScene(new Scene(component, w, h));
        initOwner(Home8App.getPrimaryStage());
        initModality(Modality.WINDOW_MODAL);
        show();
    }
}
