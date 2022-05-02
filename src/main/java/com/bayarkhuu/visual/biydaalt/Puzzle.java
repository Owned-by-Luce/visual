package com.bayarkhuu.visual.biydaalt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

@SuppressWarnings("Duplicates")
public class Puzzle extends Application {
    private final List<String> order = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
    private final GridPane root = new GridPane();
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.primaryStage = primaryStage;
        shuffle();

        root.setVgap(1);
        root.setHgap(1);
        root.setPadding(new Insets(8));

        VBox vBox = new VBox(root);
        vBox.setSpacing(20);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(8));
        hBox.setSpacing(240);

        ImageView image = getImage(new Image(new FileInputStream("src/main/resources/static/puzzle/orig.png")), null);
        image.setStyle("-fx-border-radius: 8px; -fx-background-radius: 8px;");

        ImageView closeImage = getImage(new Image(new FileInputStream("src/main/resources/static/puzzle/close.png")), null);
        closeImage.setFitWidth(25);
        closeImage.setFitHeight(25);
        closeImage.setStyle("-fx-border-radius: 50px; -fx-background-radius: 50px;");

        Button btnClose = new Button();
        btnClose.setPadding(new Insets(0));
        btnClose.setGraphic(closeImage);
        btnClose.setOnAction(e -> Platform.exit());
        btnClose.setPrefWidth(70);
        btnClose.setPrefHeight(70);
        btnClose.setStyle("-fx-cursor: hand; -fx-border-radius: 50px; -fx-background-radius: 50px; -fx-background-color: rgb(158, 158, 158)");
        btnClose.setTranslateY(13);

        hBox.getChildren().addAll(image, btnClose);

        vBox.getChildren().add(hBox);

        Scene scene = new Scene(vBox);
        primaryStage.setTitle("Puzzle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * VBox дотор зургийг хийж авах
     *
     * @param id зургийн нэр буюу ID. Хоосон нүд үүсгэх бол null утга өгөх
     * @return VBox
     * @throws FileNotFoundException Файлууд 1, 2, 3, ... 15 гэх нэртэй байна.
     */
    private VBox getCard(String id) throws FileNotFoundException {
        VBox box = new VBox();
        box.setPrefWidth(100);
        box.setPrefHeight(100);
        box.setStyle("-fx-border-color: black; -fx-cursor: hand");

        if (id != null) {
            File file = new File("src/main/resources/static/puzzle/" + id + ".png");
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
                if (db.hasImage() && isNext(box)) {
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
                box.getChildren().clear();
            }
            e.consume();
        });

        box.setOnDragDone(e -> {
            if (e.isAccepted()) {
                box.getChildren().clear();
                success();
            } else {
                Dragboard db = e.getDragboard();
                ImageView imageView = getImage(db.getImage(), db.getString());
                box.getChildren().add(imageView);
            }
            e.consume();
        });

        return box;
    }

    /**
     * Чирж тавьж буй зургийн 4 талд хоосон нүд байгаа эсэхийг шалгах
     * <p>
     * Аль нэг талд нь хоосон нүд байх үед л зургийг хөдөлгөх боломжтой.
     *
     * @param box Чирж буй зураг
     * @return Зургийг чирж тавих боломжтой бол true, боломжгүй бол false
     */
    private boolean isNext(VBox box) {
        Integer columnIndex = GridPane.getColumnIndex(box);
        Integer rowIndex = GridPane.getRowIndex(box);

        //4 талын нүдын node-үүдрийг index-ээр нь олох.
        VBox right = getCardFromRoot(columnIndex + 1, rowIndex);
        VBox bottom = getCardFromRoot(columnIndex, rowIndex + 1);
        VBox left = getCardFromRoot(columnIndex - 1, rowIndex);
        VBox top = getCardFromRoot(columnIndex, rowIndex - 1);

        //Аль нэг тал нь хоосон эсэхийг шалгаж байна.
        return Stream.of(top, right, bottom, left).filter(Objects::nonNull)
                .anyMatch(a -> a.getChildren().isEmpty());
    }

    /**
     * GridPane-аас мөр, баганы index-ээр node авах
     *
     * @param col Баганы index
     * @param row Мөрийн index
     * @return index-ээр олдсон node. Олдоогүй бол null
     */
    private VBox getCardFromRoot(int col, int row) {
        for (Node node : root.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (VBox) node;
            }
        }
        return null;
    }

    /**
     * Image-ээс ImageView үүсгэж авах
     *
     * @param image Image
     * @param id    зургийн id (уг id-аар зөв дараалалд орсон эсэхийг шалгана)
     * @return imageView
     */
    private ImageView getImage(Image image, String id) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setId(id);
        return imageView;
    }

    /**
     * Зөв эвлүүлсэн эсэхийг шалгах
     */
    private void success() {
        //Эвлүүлсэн зургуудын ID-г авах
        List<String> ids = root.getChildren()
                .stream()
                .map(e -> ((VBox) e))
                .filter(f -> !f.getChildren().isEmpty())
                .map(e -> e.getChildren().get(0).getId()).toList();

        //Зөв дараалалтай хүснэгттэй адилхан эсэхийг шалгах
        if (ids.equals(order)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(primaryStage);
            alert.setHeaderText("Зөв");
            alert.setContentText("Дүрсийг зөв эвлүүллээ");
            alert.setTitle("Зөв");
            alert.show();
        }
    }

    /**
     * Зургуудыг санамсаргүй байдлаар харуулах
     *
     * @throws FileNotFoundException Файлууд 1, 2, 3, ... 15 гэх нэртэй байна.
     */
    private void shuffle() throws FileNotFoundException {
        int row = 0;
        int i = 0;

        //Зөв дараалалтай хүснэгтийг холих
        List<String> shuffle = new ArrayList<>(order);
        Collections.shuffle(shuffle);

        //Хольсон хүснэгтээр зургуудыг үүсгэх
        for (String id : shuffle) {
            root.addRow(row, getCard(String.valueOf(id)));
            //Дараагийн мөрөнд шилжих
            if (i == 3 || i == 7 || i == 11) row++;
            i++;
        }

        //Хоосон нүд нэмэх
        root.addRow(row, getCard(null));
    }
}
