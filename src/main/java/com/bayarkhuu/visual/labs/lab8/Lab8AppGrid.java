package com.bayarkhuu.visual.labs.lab8;

import javafx.application.Application;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.function.Function;

public class Lab8AppGrid extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        orders.addAll(
                new Order(1L, false, "FedEx", 7, new Driver("Ellen Jowels", "d1", 4)),
                new Order(2L, true, "ParcelForce", 7.7, new Driver("Eric Northope", "d2", 2)),
                new Order(3L, false, "FedEx", 15, new Driver("Stella Gerrard", "d3", 3)),
                new Order(4L, true, "FedEx", 3.3, new Driver("Stanley Horbright", "d4", 3))
        );

        TableView<Order> table = new TableView<>(orders);
        table.getColumns().addAll(
                getColumn("orderId", e -> String.valueOf(e.getId())),
                getColumnVip(),
                getColumn("Courier", Order::getCourier),
                getColumn("Item Cost", e -> new DecimalFormat("$0.00").format(e.getPrice())),
                getColumnDriver()

        );
        table.setFixedCellSize(50);

        Scene scene = new Scene(table, 540, 320);
        primaryStage.setTitle("ListView");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableColumn<Order, String> getColumn(String caption, Function<Order, String> value) {
        TableColumn<Order, String> column = new TableColumn<>(caption);

        column.setCellValueFactory(data -> {
            Order order = data.getValue();
            return new ObservableValueBase<>() {
                @Override
                public String getValue() {
                    return value.apply(order);
                }
            };
        });

        column.setStyle("-fx-alignment: BASELINE-CENTER;");

        return column;
    }

    private TableColumn<Order, Boolean> getColumnVip() {
        TableColumn<Order, Boolean> column = new TableColumn<>("Priority Shipping");

        column.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Order, Boolean> call(TableColumn<Order, Boolean> orderOrderTableColumn) {
                TableCell<Order, Boolean> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(Boolean vip, boolean empty) {
                        if (vip == null || empty) return;
                        super.updateItem(vip, false);

                        if (vip) {
                            ImageView image = null;
                            try {
                                image = new ImageView(new Image(new FileInputStream("src/main/resources/static/icons/vip.png")));
                                image.setFitWidth(24);
                                image.setFitHeight(24);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            setGraphic(image);
                        } else setGraphic(null);
                    }
                };
                cell.setAlignment(Pos.BASELINE_CENTER);
                return cell;
            }
        });

        column.setCellValueFactory(new PropertyValueFactory<>("vip"));

        return column;
    }

    private TableColumn<Order, Driver> getColumnDriver() {
        TableColumn<Order, Driver> column = new TableColumn<>("Driver");

        column.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Order, Driver> call(TableColumn<Order, Driver> orderOrderTableColumn) {
                TableCell<Order, Driver> cell = new TableCell<>() {
                    @Override
                    protected void updateItem(Driver driver, boolean empty) {
                        if (driver == null || empty) return;
                        super.updateItem(driver, false);

                        HBox hBox = new HBox();
                        VBox vBox = new VBox();
                        HBox starLayout = new HBox();

                        hBox.setSpacing(10);

                        ImageView image = null;
                        try {
                            image = new ImageView(new Image(new FileInputStream("src/main/resources/static/icons/" + driver.getImage() + ".png")));
                            image.setFitWidth(40);
                            image.setFitHeight(40);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        //Жолоочийн онооны тоогоор шар од нэмэх
                        for (int i = 0; i < driver.getStar(); i++) {
                            starLayout.getChildren().add(getStar(false));
                        }

                        //Жолооч 5 од хүрэхгүй бол дутуу одны оронд шар өнгөгүй од нэмэх
                        int size = starLayout.getChildren().size();
                        for (int i = 0; i < 5 - size; i++) {
                            starLayout.getChildren().add(getStar(true));
                        }

                        vBox.getChildren().addAll(new Label(driver.getName()), starLayout);

                        hBox.getChildren().addAll(image, vBox);
                        setGraphic(hBox);
                    }
                };
                cell.setAlignment(Pos.BASELINE_CENTER);
                return cell;
            }
        });

        column.setCellValueFactory(new PropertyValueFactory<>("driver"));

        return column;
    }

    /**
     * Шар эсвэл хар од empty parameter-ээр ялгаж авах
     *
     * @param empty true бол хар од, false бол шар од
     * @return ImageView зураг олдохгүй бол null
     */
    private ImageView getStar(boolean empty) {
        try {
            ImageView imageView = new ImageView(new Image(new FileInputStream("src/main/resources/static/icons/" + (empty ? "star-empty" : "star") + ".png")));
            imageView.setFitWidth(24);
            imageView.setFitHeight(24);
            return imageView;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
