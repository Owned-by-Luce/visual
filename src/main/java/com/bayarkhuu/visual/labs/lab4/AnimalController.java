package com.bayarkhuu.visual.labs.lab4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AnimalController {
    @FXML
    private ImageView imageView;

    @FXML
    protected void change(ActionEvent e) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("F:/" + ((Labeled) e.getSource()).getText() + ".gif");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        Image image = new Image(inputStream);
        imageView.setImage(image);
    }
}
