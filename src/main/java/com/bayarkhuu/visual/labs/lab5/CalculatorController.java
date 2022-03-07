package com.bayarkhuu.visual.labs.lab5;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

public class CalculatorController {
    @FXML
    private TextField markedPrice;
    @FXML
    private TextField taxRate;
    @FXML
    private TextField taxAmount;
    @FXML
    private TextField netPrice;

    @FXML
    private void initialize() {
        DoubleProperty bindMarkedPrice = new SimpleDoubleProperty();
        DoubleProperty bindTaxRate = new SimpleDoubleProperty();
        DoubleProperty bindTaxAmount = new SimpleDoubleProperty();
        DoubleProperty total = new SimpleDoubleProperty();

        total.bind(bindMarkedPrice.divide(100).multiply(bindTaxRate).add(bindMarkedPrice));
        bindTaxAmount.bind(total.subtract(bindMarkedPrice));

        StringConverter<? extends Number> converter = new DoubleStringConverter();

        Bindings.bindBidirectional(markedPrice.textProperty(), bindMarkedPrice, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(taxRate.textProperty(), bindTaxRate, (StringConverter<Number>) converter);

        netPrice.textProperty().bind(total.asString());
        taxAmount.textProperty().bind(bindTaxAmount.asString());
    }

    @FXML
    private void exit() {
        Platform.exit();
    }
}
