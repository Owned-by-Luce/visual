package com.bayarkhuu.visual.labs.lab4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimalController {
    @FXML
    private ImageView imageView;

    @FXML
    protected void change(ActionEvent e) {
        imageView.setImage(new Image("F:/" + ((RadioButton) e.getSource()).getText() + ".gif"));
    }
}
