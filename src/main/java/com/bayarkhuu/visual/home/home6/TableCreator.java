package com.bayarkhuu.visual.home.home6;

import javafx.beans.value.ObservableValueBase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.function.Function;

/**
 * TableCreator
 *
 * @author Баярхүү.Лув 2022.03.30 09:50
 */
public class TableCreator<T> extends TableView<T> {

    public void addColumn(String caption, Function<T, ?> value, double width) {
        getColumns().add(getColumn(caption, value, width));
    }

    private TableColumn<T, String> getColumn(String caption, Function<T, ?> value, double width) {
        TableColumn<T, String> column = new TableColumn<>(caption);
        column.setCellValueFactory(data -> {
            T model = data.getValue();
            return new ObservableValueBase<>() {
                @Override
                public String getValue() {
                    return (String) value.apply(model);
                }
            };
        });
        column.setStyle("-fx-alignment: BASELINE-CENTER;");
        column.setPrefWidth(width);
        return column;
    }
}
