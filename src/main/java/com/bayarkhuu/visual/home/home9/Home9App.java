package com.bayarkhuu.visual.home.home9;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@SuppressWarnings("Duplicates")
public class Home9App extends Application {
    private final HBox row1 = new HBox();
    private final HBox row2 = new HBox();
    private final List<String> order = List.of("1", "2", "3", "4", "5");
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.primaryStage = primaryStage;

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        row1.setId("row1");
        row1.setSpacing(10);
        row1.getChildren().addAll(
                getCard("4"),
                getCard("3"),
                getCard("2"),
                getCard("1"),
                getCard("5")
        );

        row2.setId("row2");
        row2.setSpacing(10);
        row2.getChildren().addAll(
                getCard(null),
                getCard(null),
                getCard(null),
                getCard(null),
                getCard(null)
        );

        root.getChildren().addAll(row1, row2);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Drag Drop");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Карт үүсгэх
     * @param id Зургийн id буюу нэр
     */
    private VBox getCard(String id) throws FileNotFoundException {
        VBox box = new VBox();
        box.setPrefWidth(102);
        box.setPrefHeight(300);
        box.setStyle("-fx-border-color: black; -fx-cursor: hand");

        if (id != null) {
            File file = new File("src/main/resources/static/horse/" + id + ".png");
            box.getChildren().add(getImage(new Image(new FileInputStream(file)), id));
        }

        box.setOnDragOver(e -> {
            if (e.getGestureSource() != box && e.getDragboard().hasImage()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        box.setOnDragDropped(e -> {
            if (box.getChildren().isEmpty()) {
                Dragboard db = e.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    ImageView imageView = getImage(db.getImage(), db.getString());
                    box.getChildren().add(imageView);
                    success = true;
                }
                e.setDropCompleted(success);
            } else e.setDropCompleted(false);
            e.consume();
        });

        box.setOnDragDetected(e -> {
            if (!box.getChildren().isEmpty()) {
                ImageView imageView = (ImageView) box.getChildren().get(0);

                Dragboard db = box.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putImage(imageView.getImage());
                content.putString(imageView.getId());
                db.setContent(content);
            }
            e.consume();
        });

        box.setOnDragDone(e -> {
            if (e.isAccepted()) {
                box.getChildren().clear();
                success();
            }
            e.consume();
        });

        return box;
    }

    private ImageView getImage(Image image, String id) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(300);
        imageView.setId(id);
        return imageView;
    }

    /**
     * Зөв эвлүүлсэн эсэхийг шалгаад Alert харуулах метод
     */
    private void success() {
        //Зургуудад 1, 2, 3, 4, 5 гэх ID-г өгсөн байгаа. Ингэсэнээр зургууд зөв байрлалд
        //орсон эсэхийг амархан шалгаж болох юм
        List<String> ids = row2.getChildren().stream().map(e -> ((VBox) e))
                .filter(f -> !f.getChildren().isEmpty())
                .map(e -> e.getChildren().get(0))
                .map(Node::getId).toList();

        if (ids.size() == 5) {
            //Классын эхэнд order гэх 1, 2, 3, 4, 5 гэх элементтэй хүснэгт зарласан.
            //ids нь order хүснэгттэй ижилхэн байвал зөв эвлүүлсэн гэж үзсэн.
            if (ids.equals(order)) {
                showAlert(Alert.AlertType.INFORMATION, "Зөв", "Дүрсийг зөв эвлүүллээ");
            } else {
                showAlert(Alert.AlertType.ERROR, "Буруу", "Дүрсийг буруу эвлүүллээ");
            }
        }
    }

    /**
     * Alert харуулах метод
     * @param alertType Alert-ны төрөл
     * @param header Alert-ны гарчиг
     * @param content Alert-ны харуулах текст
     */
    private void showAlert(Alert.AlertType alertType, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(primaryStage);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setTitle(header);
        alert.show();
    }
}
