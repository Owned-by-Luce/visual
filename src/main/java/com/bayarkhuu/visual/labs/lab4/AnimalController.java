package com.bayarkhuu.visual.labs.lab4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AnimalController {
    @FXML
    private ImageView imageView;

    @FXML
    protected void change(ActionEvent e) throws FileNotFoundException {
        File file = new File("src/main/java/com/bayarkhuu/visual/labs/lab4/images/" + ((RadioButton) e.getSource()).getText() + ".gif");
        imageView.setImage(new Image(new FileInputStream(file)));
    }
}
