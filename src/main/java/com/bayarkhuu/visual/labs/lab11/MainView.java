package com.bayarkhuu.visual.labs.lab11;

import javafx.fxml.FXML;
import javafx.scene.Parent;

public class MainView {
    @FXML
    private Parent embeddedRedView; //embeddedElement
    @FXML
    private RedView embeddedRedViewController; // $embeddedElement+Controller
    public void initialize() {
        System.out.println(embeddedRedViewController);
        System.out.println(embeddedRedView);
        embeddedRedViewController.foo("It works"); //Console print "It works"
    }
}
