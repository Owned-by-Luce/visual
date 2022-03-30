package com.bayarkhuu.visual.home.home7;

import javafx.application.Application;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.function.Function;

public class Home7App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ObservableList<Person> list = FXCollections.observableArrayList();
        list.addAll(new Person("Kathy", "Smith", new Color(151, 1, 146), Sport.Snowboarding, 5, false), new Person("John", "Doe", new Color(51, 51, 153), Sport.Rowing, 3, true), new Person("Sue", "Black", new Color(102, 102, 153), Sport.Knitting, 2, false), new Person("Jane", "White", new Color(153, 153, 153), Sport.SpeedReading, 20, true), new Person("Jack", "Brown", new Color(204, 204, 204), Sport.Pool, 10, false));

        Scene scene = new Scene(getTable(list), 750, 260);
        primaryStage.setTitle("Table Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView<Person> getTable(ObservableList<Person> list) {
        TableView<Person> table = new TableView<>(list);
        table.getColumns().add(getColumn("First Name", Person::getFirstName));
        table.getColumns().add(getColumn("Last Name", Person::getLastName));
        table.getColumns().add(getColumn("Sport", Person::getSport));
        table.getColumns().add(getColumn("# of Years", Person::getYear));
        table.getColumns().add(getColumn("Vegetarian", Person::isVegetarian));
        table.getColumns().stream().filter(f -> f.getText().equals("Sport")).forEach(this::addTooltipToColumnCells);
        return table;
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

        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(e -> {
            Person person = e.getRowValue();
            System.out.println(person.getFirstName());
        });

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
