package com.bayarkhuu.visual.home.home4;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class InterestController {
    @FXML
    private TextField tfPrincipal;
    @FXML
    private TextField tfRate;
    @FXML
    private DatePicker tfStart;
    @FXML
    private DatePicker tfEnd;
    @FXML
    private TextField tfPeriods;
    @FXML
    private TextField tfEarned;
    @FXML
    private TextField tfFuture;

    @FXML
    private void initialize() {
        tfStart.setValue(LocalDate.now());
        tfEnd.setValue(LocalDate.now());

        DoubleProperty bindPeriods = new SimpleDoubleProperty();
        DoubleProperty bindPrincipal = new SimpleDoubleProperty();
        DoubleProperty bindRate = new SimpleDoubleProperty();
        ObjectProperty<LocalDate> bindStart = new SimpleObjectProperty<>();
        ObjectProperty<LocalDate> bindEnd = new SimpleObjectProperty<>();
        DoubleProperty earned = new SimpleDoubleProperty();
        DoubleProperty future = new SimpleDoubleProperty();

        bindPeriods.bind(new SimpleIntegerProperty((int) ChronoUnit.DAYS.between(tfStart.getValue(), tfEnd.getValue())));
        earned.bind(bindPrincipal.multiply(bindRate).multiply(bindPeriods));
        future.bind(bindPrincipal.add(earned));

        StringConverter<? extends Number> converter = new DoubleStringConverter();

        Bindings.bindBidirectional(tfPeriods.textProperty(), bindPeriods, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(tfPrincipal.textProperty(), bindPrincipal, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(tfRate.textProperty(), bindRate, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(tfEarned.textProperty(), earned, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(tfFuture.textProperty(), future, (StringConverter<Number>) converter);
        Bindings.bindBidirectional(tfStart.valueProperty(), bindStart);
        Bindings.bindBidirectional(tfEnd.valueProperty(), bindEnd);

        tfPeriods.textProperty().bind(bindPeriods.asString());
        tfEarned.textProperty().bind(earned.asString());
        tfFuture.textProperty().bind(future.asString());
    }
}
