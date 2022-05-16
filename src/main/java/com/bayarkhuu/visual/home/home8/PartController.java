package com.bayarkhuu.visual.home.home8;

import com.bayarkhuu.visual.home.home8.model.Part;
import com.bayarkhuu.visual.home.home8.model.Receipt;
import com.bayarkhuu.visual.utils.Repository;
import javafx.application.Platform;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class PartController {
    private final Repository<Part> repository = new Repository<>(Part.class);
    private final Repository<Receipt> receiptRepository = new Repository<>(Receipt.class);
    private final DecimalFormat formetter = new DecimalFormat("0.00");

    @FXML
    private TreeView<String> tvAutoParts;
    @FXML
    private TableView<Part> lvAutoParts;
    @FXML
    private TableView<Part> tvSelectedParts;
    @FXML
    private VBox orderBox;
    @FXML
    private TextField txtPartNumber;
    @FXML
    private TextField txtPartName;
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TextField txtSubTotal;
    @FXML
    private TextField txtTaxRate;
    @FXML
    private TextField txtTaxAmount;
    @FXML
    private TextField txtPartsTotal;
    @FXML
    private TextField txtOrderTotal;
    @FXML
    private TextField txtSave;
    @FXML
    private TextField txtOpen;

    @FXML
    private void initialize() {
        orderBox.setDisable(true);
        txtSave.setDisable(true);

        TreeItem<String> root = new TreeItem<>("College Park Auto-Parts");
        List<Part> allParts = repository.findAllByCriteria(null);

        root.getChildren().addAll(allParts.stream().filter(distinctByKey(Part::getYear)).map(part -> {
            TreeItem<String> yearItem = new TreeItem<>(part.getYear());

            yearItem.getChildren().addAll(repository.findAllByCriteria(e -> e.getYear().equals(part.getYear())).stream().map(el -> {
                TreeItem<String> makeItem = new TreeItem<>(el.getMake().getName());

                makeItem.getChildren().addAll(repository.findAllByCriteria(e -> e.getMake().getName().equals(el.getMake().getName())).stream().map(model -> {
                    TreeItem<String> modelItem = new TreeItem<>(model.getModel().getName());

                    modelItem.getChildren().addAll(repository.findAllByCriteria(e -> e.getModel() != null && e.getModel().getName().equals(model.getModel().getName())).stream()
                            .map(category -> new TreeItem<>(category.getCategory().getName())).toList());

                    return modelItem;
                }).toList());

                return makeItem;
            }).toList());

            return yearItem;
        }).toList());

        tvAutoParts.setRoot(root);

        lvAutoParts.getColumns().clear();
        lvAutoParts.getColumns().addAll(
                addColumn("Part #", Part::getPartNumber, 80),
                addColumn("Part Name", Part::getPartName, 260),
                addColumn("Unit Price", Part::getPrice, 75),
                addColumn("Year", Part::getYear, 65)
        );
        tvSelectedParts.getColumns().clear();
        tvSelectedParts.getColumns().addAll(
                addColumn("Part #", Part::getPartNumber, 80),
                addColumn("Part Name", Part::getPartName, 260),
                addColumn("Unit Price", Part::getPrice, 75),
                addColumn("Qty", Part::getQuantity, 65),
                addColumn("Sub-Total", e -> e.getPrice() * e.getQuantity(), 85)
        );

        lvAutoParts.setItems(FXCollections.observableArrayList(allParts));

        lvAutoParts.setRowFactory(tv -> {
            TableRow<Part> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Part model = row.getItem();
                    txtPartNumber.setUserData(model);
                    txtPartNumber.setText(model.getPartNumber());
                    txtPartName.setText(model.getPartName());
                    txtUnitPrice.setText(String.valueOf(model.getPrice()));
                    txtQuantity.setText("1");
                    txtSubTotal.setText(String.valueOf(model.getPrice() * 1));
                }
            });
            return row;
        });

        txtUnitPrice.setOnAction(e -> {
            try {
                double unitPrice = Double.parseDouble(txtUnitPrice.getText());
                int quantity = Integer.parseInt(txtQuantity.getText());
                txtSubTotal.setText(String.valueOf(unitPrice * quantity));
            } catch (Exception ex) {
                txtSubTotal.setText("0");
            }
        });
    }

    @FXML
    private void newAutoPart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PartEditor.fxml"));
        new Modal("Part Editor", loader.load(), 454, 238);
    }

    @FXML
    private void add() {
        ObservableList<Part> items = tvSelectedParts.getItems();
        Part part = (Part) txtPartNumber.getUserData();
        if (part != null) {
            part.setPartNumber(txtPartNumber.getText());
            part.setPartName(txtPartName.getText());
            part.setPrice(Double.parseDouble(txtUnitPrice.getText()));
            part.setQuantity(Integer.parseInt(txtQuantity.getText()));
            items.add(part);
        }

        System.out.println(items.stream().mapToDouble(e -> e.getPrice() * e.getQuantity()).sum());

        txtTaxAmount.setText(formetter.format(items.stream().mapToDouble(e -> e.getPrice() * e.getQuantity()).sum()));
        double v = Double.parseDouble(txtTaxRate.getText()) / 100 * Double.parseDouble(txtTaxAmount.getText());
        txtPartsTotal.setText(formetter.format(v));

        txtOrderTotal.setText(formetter.format(Double.parseDouble(txtTaxAmount.getText()) + Double.parseDouble(txtPartsTotal.getText())));
    }

    @FXML
    private void close() {
        Platform.exit();
    }

    @FXML
    private void enable() {
        orderBox.setDisable(false);

        List<Receipt> allReceipt = receiptRepository.findAllByCriteria(null);
        if (allReceipt.isEmpty()) txtSave.setText("1");
        else txtSave.setText(String.valueOf(allReceipt.size() + 1));
    }

    @FXML
    private void save() {
        ObservableList<Part> items = tvSelectedParts.getItems();
        Receipt receipt = new Receipt(items.stream().toList());
        Integer id = receiptRepository.save(receipt);
        if (id != null) {
            tvSelectedParts.getItems().clear();
            txtPartNumber.clear();
            txtPartName.clear();
            txtUnitPrice.clear();
            txtQuantity.clear();
            txtSubTotal.clear();
            txtTaxAmount.clear();
            txtPartsTotal.clear();
            txtOrderTotal.clear();
            txtTaxRate.setText("7.75");
            txtSave.clear();
        }
    }

    @FXML
    private void open() {
        Receipt receipt = receiptRepository.findById(Integer.valueOf(txtOpen.getText()));
        if (receipt != null)
            tvSelectedParts.setItems(FXCollections.observableArrayList(receipt.getParts()));
    }

    public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    private TableColumn<Part, String> addColumn(String caption, Function<Part, ?> value, double width) {
        TableColumn<Part, String> column = new TableColumn<>(caption);
        column.setCellValueFactory(data -> {
            Part model = data.getValue();
            return new ObservableValueBase<>() {
                @Override
                public String getValue() {
                    return String.valueOf(value.apply(model));
                }
            };
        });
        column.setStyle("-fx-alignment: BASELINE-CENTER;");
        column.setPrefWidth(width);
        return column;
    }
}
