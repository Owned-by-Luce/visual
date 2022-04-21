package com.bayarkhuu.visual.home.home8;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class PartController {

    @FXML
    private void newAutoPart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PartEditor.fxml"));
        Modal modal = new Modal("", loader.load(), 425, 238);
        modal.show();
    }

    @FXML
    private void close() {
        Platform.exit();
    }
}
