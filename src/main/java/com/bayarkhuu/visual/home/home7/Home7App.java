package com.bayarkhuu.visual.home.home7;

import javafx.application.Application;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Home7App extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        ObservableList<Person> list = FXCollections.observableArrayList();
        list.addAll(
                new Person("Kathy", "Smith", Color.rgb(151, 1, 146), Sport.Snowboarding, 5, false),
                new Person("John", "Doe", Color.rgb(51, 51, 153), Sport.Rowing, 3, true),
                new Person("Sue", "Black", Color.rgb(102, 102, 153), Sport.Knitting, 2, false),
                new Person("Jane", "White", Color.rgb(153, 153, 153), Sport.SpeedReading, 20, true),
                new Person("Jack", "Brown", Color.rgb(204, 204, 204), Sport.Pool, 10, false));

        Scene scene = new Scene(getTable(list), 750, 260);
        primaryStage.setTitle("Table Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView<Person> getTable(ObservableList<Person> list) {
        TableView<Person> table = new TableView<>(list);
        table.setEditable(true);
        table.getColumns().add(getColumn("First Name", Person::getFirstName));
        table.getColumns().add(getColumn("Last Name", Person::getLastName));
        table.getColumns().add(getColorColumn(table));
        table.getColumns().add(getColumn("Sport", Person::getSport));
        table.getColumns().add(getColumn("# of Years", Person::getYear));
        table.getColumns().add(getColumn("Vegetarian", Person::isVegetarian));
        table.getColumns().stream().filter(f -> f.getText().equals("Sport")).forEach(this::addTooltipToColumnCells);
        return table;
    }

    private TableColumn<Person, Color> getColorColumn(TableView<Person> tableView) {
        TableColumn<Person, Color> column = new TableColumn<>("Favorite Color");

        column.setCellFactory(data -> {
            TableCell<Person, Color> cell = new TableCell<>() {
                @Override
                protected void updateItem(Color color, boolean empty) {
                    if (color == null || empty) return;
                    super.updateItem(color, false);

                    Rectangle rectangle = new Rectangle();
                    rectangle.setX(20);
                    rectangle.setY(50);
                    rectangle.setWidth(100);
                    rectangle.setHeight(20);
                    rectangle.setFill(color);

                    rectangle.setOnMouseClicked(e -> {
                        if (e.getClickCount() == 2) {
                            ColorPicker colorPicker = new ColorPicker();
                            colorPicker.setValue(color);
                            colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                                TablePosition<Person, Color> pos = new TablePosition<>(tableView, 2, column);
                                column.getOnEditCommit().handle(new TableColumn.CellEditEvent<>(tableView, pos, null, newValue));
                            });
                            new PickColorDialog(primaryStage, colorPicker);
                        }
                    });

                    setGraphic(rectangle);
                }
            };
            cell.setAlignment(Pos.BASELINE_CENTER);
            return cell;

        });


        column.setOnEditCommit(event -> {
            TablePosition<Person, Color> pos = event.getTablePosition();
            int row = pos.getRow();
            Person model = event.getTableView().getItems().get(row);
            model.setColor(event.getNewValue());
        });

        column.setCellValueFactory(new PropertyValueFactory<>("color"));

        return column;
    }

    private TableColumn<Person, String> getColumn(String caption, Function<Person, ?> value) {
        TableColumn<Person, String> column = new TableColumn<>(caption);
        column.setCellValueFactory(data -> {
            Person model = data.getValue();
            if (caption.equals("Sport")) {
                Tooltip.install(column.getStyleableNode(), new Tooltip("The person's favorite sport to participate in"));
            }
            return new ObservableValueBase<>() {
                @Override
                public String getValue() {
                    return String.valueOf(value.apply(model));
                }
            };
        });
        column.setStyle("-fx-alignment: BASELINE-CENTER;");
        column.setPrefWidth(150);

        if (caption.equals("Sport")) {
            ObservableList<String> sports = FXCollections.observableArrayList(Arrays.stream(Sport.values()).map(Sport::getName).collect((Collectors.toList())));
            column.setCellFactory(ComboBoxTableCell.forTableColumn(sports));
            column.setOnEditCommit(event -> {
                TablePosition<Person, String> pos = event.getTablePosition();
                Sport sport = Sport.valueOf(event.getNewValue());
                int row = pos.getRow();
                Person model = event.getTableView().getItems().get(row);
                model.setSport(sport);
            });
        }

        return column;
    }

    private <T> void addTooltipToColumnCells(TableColumn<Person, T> column) {
        Callback<TableColumn<Person, T>, TableCell<Person, T>> existingCellFactory = column.getCellFactory();
        column.setCellFactory(c -> {
            TableCell<Person, T> cell = existingCellFactory.call(c);
            cell.setTooltip(new Tooltip("This person's sport to participate in is: " + c.getText()));
            return cell;
        });
    }
}
