package com.bayarkhuu.visual.labs.lab6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class SwapController {
    private final ObservableList<String> left = FXCollections.observableArrayList();
    private final ObservableList<String> right = FXCollections.observableArrayList();

    @FXML
    private ListView<String> leftView;
    @FXML
    private ListView<String> rightView;

    @FXML
    private void initialize() {
        left.addAll("1", "2", "3", "4", "5");
        right.addAll("a", "b", "c", "d", "e");

        leftView.setItems(left);
        rightView.setItems(right);
    }

    @FXML
    private void toRight() {
       String selectedItems = leftView.getSelectionModel().getSelectedItem();
        leftView.getSelectionModel().clearSelection();
        left.remove(selectedItems);
        right.add(selectedItems);
    }

    @FXML
    private void toLeft() {
        String selectedItems = rightView.getSelectionModel().getSelectedItem();
        rightView.getSelectionModel().clearSelection();
        right.remove(selectedItems);
        left.add(selectedItems);
    }
}
