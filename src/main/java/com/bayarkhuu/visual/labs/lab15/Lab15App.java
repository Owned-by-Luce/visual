package com.bayarkhuu.visual.labs.lab15;

import com.bayarkhuu.visual.utils.Constant;
import com.bayarkhuu.visual.utils.JasperUtil;
import com.bayarkhuu.visual.utils.Repository;
import javafx.application.Application;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.function.Function;

public class Lab15App extends Application {
    private final Repository<DataBean> repository = new Repository<>(DataBean.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        final List<DataBean> list = repository.findAllByCriteria(null);

        Button btn = new Button("Хэвлэх");
        btn.setOnAction(e -> {
            Stage newStage = new Stage();
            newStage.setTitle("Хэвлэх");
            newStage.setScene(new Scene(showPrint(list)));
            newStage.initOwner(primaryStage);
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.show();
        });

        TableView<DataBean> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(list));
        table.getColumns().addAll(
                addColumn("ID", DataBean::getId, 80),
                addColumn("Нэр", DataBean::getName, 200),
                addColumn("Иргэншил", DataBean::getCountry, 200)
        );

        root.getChildren().addAll(btn, table);

        Scene scene = new Scene(root, 500, 180);
        primaryStage.setTitle("Lab 15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableColumn<DataBean, String> addColumn(String caption, Function<DataBean, ?> value, double width) {
        TableColumn<DataBean, String> column = new TableColumn<>(caption);
        column.setCellValueFactory(data -> {
            DataBean model = data.getValue();
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

    private Parent showPrint(List<DataBean> list) {
        WebView web = new WebView();
        WebEngine engine = web.getEngine();

        JasperUtil.create(Constant.PATH_STATIC + "/jaspers/jasper_report_template.jrxml", Constant.PATH_STATIC + "/jaspers/out/temp.html", list);

        //энэ кодыг нээвэл modal ашиглах шаардлаггүй
//        JasperViewer jviewer = new JasperViewer(jasperPrint, false);
//        jviewer.setVisible(true);

        File file = new File(Constant.PATH_STATIC + "/jaspers/out/temp.html");
        try {
            URL url = file.toURI().toURL();
            engine.load(url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return web;
    }
}
