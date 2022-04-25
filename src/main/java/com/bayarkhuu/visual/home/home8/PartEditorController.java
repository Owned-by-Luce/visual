package com.bayarkhuu.visual.home.home8;

import com.bayarkhuu.visual.home.home8.model.Item;
import com.bayarkhuu.visual.home.home8.model.Part;
import com.bayarkhuu.visual.labs.lab9.Repository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class PartEditorController {
    private final Repository<Item> repository = new Repository<>(Item.class);
    private final Repository<Part> partRepository = new Repository<>(Part.class);
    @FXML
    private TextField txtItemNumber;
    @FXML
    private ComboBox<Item> cbMakes;
    @FXML
    private ComboBox<Item> cbModels;
    @FXML
    private ComboBox<Item> cbCategories;
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private TextField txtPartName;
    @FXML
    private TextField txtPartNumber;

    @FXML
    private void initialize() {
        cbMakes.setItems(FXCollections.observableArrayList(repository.findAllByCriteria(item -> item.getType().equals("Make"))));
        cbMakes.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });
        cbMakes.setButtonCell(cbMakes.getCellFactory().call(null));
        cbModels.setItems(FXCollections.observableArrayList(repository.findAllByCriteria(item -> item.getType().equals("Model"))));
        cbModels.setCellFactory(cbMakes.getCellFactory());
        cbModels.setButtonCell(cbMakes.getCellFactory().call(null));

        cbCategories.setItems(FXCollections.observableArrayList(repository.findAllByCriteria(item -> item.getType().equals("Category"))));
        cbCategories.setCellFactory(cbMakes.getCellFactory());
        cbCategories.setButtonCell(cbMakes.getCellFactory().call(null));
    }

    @FXML
    private void newMake() {
        new Modal("Make Editor", new Editor("Make", e -> cbMakes.getItems().add(e)), 225, 82);
    }

    @FXML
    private void newModel() {
        new Modal("Model Editor", new Editor("Model", e -> cbModels.getItems().add(e)), 235, 82);
    }

    @FXML
    private void newCategory() {
        new Modal("Item Category", new Editor("Category", e -> cbCategories.getItems().add(e)), 245, 82);
    }

    @FXML
    private void submit(ActionEvent e) {
        Integer id = partRepository.save(new Part(
                txtItemNumber.getText(),
                cbMakes.getValue(),
                cbModels.getValue(),
                cbCategories.getValue(),
                txtPartName.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                txtPartNumber.getText()
        ));
        if (id != null) {
            ((Button) e.getTarget()).getScene().getWindow().hide();
        }
    }

    @FXML
    private void close(ActionEvent e) {
        ((Button) e.getTarget()).getScene().getWindow().hide();
    }
}
