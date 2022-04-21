package com.bayarkhuu.visual.home.home8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PartEditorController {

    @FXML
    private void newMake() {
        new Modal("Make Editor", new Editor("Make"), 225, 82);
    }

    @FXML
    private void newModel() {
        new Modal("Model Editor", new Editor("Model"), 235, 82);
    }

    @FXML
    private void newCategory() {
        new Modal("Item Category", new Editor("Category"), 245, 82);
    }

    @FXML
    private void close(ActionEvent e) {
        ((Button) e.getTarget()).getScene().getWindow().hide();
    }
}
