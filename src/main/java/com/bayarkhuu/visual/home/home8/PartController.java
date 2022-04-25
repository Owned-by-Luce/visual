package com.bayarkhuu.visual.home.home8;

import com.bayarkhuu.visual.home.home8.model.Part;
import com.bayarkhuu.visual.labs.lab9.Repository;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.IOException;

public class PartController {
    private final Repository<Part> repository = new Repository<>(Part.class);
    @FXML
    private TreeView<Part> tvAutoParts;

    @FXML
    private void initialize() {
        TreeItem<Part> root = new TreeItem<>(new Part("College Park Auto-Parts"));

        root.getChildren().addAll(repository.findAllByCriteria(null).stream().map(e -> {
            TreeItem<Part> treeItem = new TreeItem<>(e);
            return treeItem;
        }).toList());

        tvAutoParts.setRoot(root);
    }

    @FXML
    private void newAutoPart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PartEditor.fxml"));
        new Modal("", loader.load(), 425, 238);
    }

    @FXML
    private void close() {
        Platform.exit();
    }
}
