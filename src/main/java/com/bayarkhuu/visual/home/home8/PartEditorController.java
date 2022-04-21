package com.bayarkhuu.visual.home.home8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PartEditorController {

    @FXML
    private void newMake() {
        Modal modal = new Modal("Make Editor", new Editor("Make"), 225, 82);
        modal.show();
    }

    @FXML
    private void newModel() {
        Modal modal = new Modal("Model Editor", new Editor("Model"), 235, 82);
        modal.show();
    }

    @FXML
    private void newCategory() {
        Modal modal = new Modal("Item Category", new Editor("Category"), 245, 82);
        modal.show();
    }

    @FXML
    private void close(ActionEvent e) {
        ((Button) e.getTarget()).getScene().getWindow().hide();
    }
}
