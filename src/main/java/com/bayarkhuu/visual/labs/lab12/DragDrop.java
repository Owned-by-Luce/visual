package com.bayarkhuu.visual.labs.lab12;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class DragDrop {
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfNewName;
    @FXML
    private TextArea tfLog;

    @FXML
    private void initialize() {
        tfCity.setOnDragDetected(e -> {
            addLog("tfCity: " + e.getEventType().getName());
            Dragboard db = tfCity.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(tfCity.getSelectedText());
            db.setContent(content);
            e.consume();
        });

        tfNewName.setOnDragOver(e -> {
            addLog("tfNewName: " + e.getEventType().getName());
            if (e.getGestureSource() != tfNewName && e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            e.consume();
        });

        tfNewName.setOnDragDropped(e -> {
            addLog("tfNewName: " + e.getEventType().getName());
            Dragboard db = e.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                tfNewName.setText(db.getString());
                tfCity.clear();
                success = true;
            }
            e.setDropCompleted(success);
            e.consume();
        });
    }

    private void addLog(String log) {
        tfLog.setText(tfLog.getText() + "\n" + log);
        tfLog.setScrollTop(10000);
    }
}
