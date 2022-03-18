package com.bayarkhuu.visual.home.home5;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * FontController
 *
 * @author Баярхүү.Лув, 2022.03.18 14:48
 */
public class FontController {
    @FXML
    private ListView<String> font;
    @FXML
    private ListView<String> style;
    @FXML
    private ListView<String> size;
    @FXML
    private CheckBox underline;
    @FXML
    private ComboBox<String> color;
    @FXML
    private Label sample;
    @FXML
    private Label colorSample;

    @FXML
    private void initialize() {
        ObservableList<String> fontNames = FXCollections.observableArrayList(Font.getFamilies());
        font.setItems(fontNames);
        style.getItems().addAll("Regular", "Italic", "Bold", "Bold Italic");
        size.getItems().addAll("8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72");
        color.setItems(FXCollections.observableArrayList("BLACK", "RED", "BLUE", "GREEN", "YELLOW", "ORANGE", "PINK"));

        font.getSelectionModel().selectFirst();
        style.getSelectionModel().selectFirst();
        size.getSelectionModel().selectFirst();


        font.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> setSample());
        style.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> setSample());
        size.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> setSample());

        color.setOnAction(e -> {
            colorSample.setStyle("-fx-border-color:  transparent transparent " + color.getValue() + " transparent;" +
                    "-fx-border-width: 3");
            setSample();
        });
        color.setValue("BLACK");
        underline.setOnAction(e -> setSample());
    }

    private void setSample() {
        FontWeight fontWeight = FontWeight.NORMAL;
        if (style.getSelectionModel().getSelectedItem().contains("Bold")) {
            fontWeight = FontWeight.BOLD;
        }

        FontPosture fontPosture = FontPosture.REGULAR;
        if (style.getSelectionModel().getSelectedItem().contains("Italic")) {
            fontPosture = FontPosture.ITALIC;
        }

        sample.setFont(Font.font(
                font.getSelectionModel().getSelectedItem(),
                fontWeight,
                fontPosture,
                Double.parseDouble(size.getSelectionModel().getSelectedItem())));
        sample.setTextFill(Paint.valueOf(color.getValue()));
        sample.setUnderline(underline.isSelected());
    }

    @FXML
    private void ok() {
        Platform.exit();
    }
}
