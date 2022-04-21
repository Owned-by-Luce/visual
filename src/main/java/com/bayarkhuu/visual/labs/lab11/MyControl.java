package com.bayarkhuu.visual.labs.lab11;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MyControl extends AnchorPane {

    @FXML
    private CheckBox answer;

    public MyControl()
            throws IOException {

        // attach FXML to this control instance
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyControl.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    @FXML
    public void initialize() {

        // listen for checkbox changes
        answer.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Answer changed to: " + (newVal ? "Yes" : "No"));
        });
    }
}
