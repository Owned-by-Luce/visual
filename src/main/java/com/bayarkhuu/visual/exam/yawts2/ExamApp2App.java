package com.bayarkhuu.visual.exam.yawts2;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ExamApp2App extends Application {
    //Төмс хураалтын үед тайлбайг 4 хуваах шаардлагатай учир 4 list үүсгэв
    private final List<Potato> field1 = new ArrayList<>();
    private final List<Potato> field2 = new ArrayList<>();
    private final List<Potato> field3 = new ArrayList<>();
    private final List<Potato> field4 = new ArrayList<>();

    private final Label result = getCaption("", false);
    private final Label resultCurrent = getCaption("", false);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final HBox root = new HBox();
        root.setSpacing(15);

        final VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setSpacing(10);

        createField();

        box.getChildren().addAll(
                getCaption("Талбай 1", true),
                getCombine(field1, 1),
                getCaption("Талбай 2", true),
                getCombine(field2, 2),
                getCaption("Талбай 3", true),
                getCombine(field3, 3),
                getCaption("Талбай 4", true),
                getCombine(field4, 4)
        );

        final VBox resultWrap = new VBox();
        resultWrap.setTranslateY(40);
        resultWrap.setPadding(new Insets(10));
        resultWrap.setStyle("-fx-border-radius: 10; -fx-border-color: black");
        resultWrap.setMaxHeight(50);

        result.setText("Одоогоор тодорхойгүй");
        resultCurrent.setPadding(new Insets(0, 0, 10, 0));
        resultWrap.getChildren().addAll(
                getCaption("Тухайн агшинд хураасан төмс:", true),
                resultCurrent,
                getCaption("Хамгийн их ургац хураасан талбай:", true),
                result);

        root.getChildren().addAll(box, resultWrap);

        Scene scene = new Scene(root, 585, 480);
        primaryStage.setTitle("Grading System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label getCaption(String caption, boolean bold) {
        Label label = new Label(caption);
        label.setFont(new Font("Arial" + (bold ? " Bold" : ""), 15));
        return label;
    }

    /**
     * NxN тариалангийн талбай үүсгэх
     * Үүссэн тайлбайг 4 тэнцүү хувааж тус тус Array list-д хийх
     */
    private void createField() {
        int N = 500;//Туршилт хийх зорилгоор 1,000,000 биш 500 болгов
        Random random = new Random();
        Potato[][] field = new Potato[N][N];

        //Санамсахгүй төмснүүд NxN байдалтай үүсгэх
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int size = random.nextInt(5) + 1;
                field[i][j] = new Potato(size);
            }
        }

        //2 хэмжээст хүснэгтийг 1 хэмжээст list болгох
        List<Potato> fieldAsArray = Arrays.stream(field).flatMap(Arrays::stream).collect(Collectors.toList());

        //Талбайг 4 тэнцүү хувааж буй хэсэг
        int i = N / 4;
        field1.addAll(fieldAsArray.subList(0, i - 1));
        field2.addAll(fieldAsArray.subList(i, i * 2 - 1));
        field3.addAll(fieldAsArray.subList(i * 2, i * 3 - 1));
        field4.addAll(fieldAsArray.subList(i * 3, i * 4 - 1));
    }

    private Node getCombine(List<Potato> field, int num) {
        Combine combine = new Combine(0, field);

        Harvest task = new Harvest(combine);

        final ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(150);
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(task.progressProperty());

        final Label current = new Label(combine.getHarvested() + "/" + combine.getField().size());
        current.setPrefWidth(70);
        current.setFont(new Font("Arial Bold", 15));
        current.textProperty().unbind();
        current.textProperty().bind(task.messageProperty());
        resultCurrent.textProperty().unbind();
        resultCurrent.textProperty().bind(task.titleProperty());

        final Label size = new Label();
        size.textProperty().unbind();
        size.textProperty().bind(task.titleProperty());

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, t -> {
            //Тариалсан бүх төмсийг хурааж авдаг гэсэн тул доорх код зөв ажиллана
            double a = field1.stream().filter(Potato::isHarvested).mapToDouble(Potato::getSize).sum();
            double b = field2.stream().filter(Potato::isHarvested).mapToDouble(Potato::getSize).sum();
            double c = field3.stream().filter(Potato::isHarvested).mapToDouble(Potato::getSize).sum();
            double d = field4.stream().filter(Potato::isHarvested).mapToDouble(Potato::getSize).sum();

            double max = Math.max(Math.max(Math.max(a, b), c), d);

            result.setText((max == a ? "Талбай 1" : max == b ? "Талбай 2" : max == c ? "Талбай 3" : "Талбай 4") +
                    " /" + max + "/"
            );
        });

        final HBox box = new HBox();
        box.setSpacing(10);
        box.getChildren().addAll(progressBar, current);

        final VBox root = new VBox();
        root.setMaxWidth(265);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(new Label("Комбайн " + num), box, size);
        root.setStyle("-fx-border-color: black; -fx-border-radius: 6px");

        new Thread(task).start();
        return root;
    }
}
