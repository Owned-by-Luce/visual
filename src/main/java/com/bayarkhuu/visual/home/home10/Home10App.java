package com.bayarkhuu.visual.home.home10;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Home10App extends Application {
    private final List<Task<Province>> tasks = new ArrayList<>();
    private final List<Province> result = new ArrayList<>();
    private final DecimalFormat formatter = new DecimalFormat("#.00");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final Timer timer = new Timer();
        final VBox root = new VBox();

        Supplier<Label> labelSupplier = () -> {
            Label label = new Label();
            label.setFont(new Font("Arial Bold", 18));
            label.setPrefWidth(650);
            label.setWrapText(true);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setAlignment(Pos.TOP_CENTER);
            return label;
        };

        final Label header = labelSupplier.get();
        header.textProperty().bind(timer.messageProperty());

        final Label percent = labelSupplier.get();

        timer.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                t -> {
                    tasks.forEach(task -> task.cancel(true));
                    header.textProperty().unbind();
                    header.setText("ДҮН: Нийт: " + result.stream().mapToInt(Province::getPopulation).sum() + " сонгогчдоос " + result.stream().mapToInt(Province::getCurrent).sum() + " хүн санал өгсөн.");
                    header.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");

                    double d = 100 / result.stream().mapToDouble(Province::getPopulation).sum() * result.stream().mapToInt(Province::getCurrent).sum();
                    percent.setText("Энэ нь нийт сонгогдын " + formatter.format(d) + "% байна.");
                    percent.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");
                });

        new Thread(timer).start();

        final List<Province> provinces = getRandomProvinces();

        root.getChildren().addAll(header, percent);
        root.getChildren().addAll(provinces.stream().map(this::row).toList());
        root.setPadding(new Insets(10));
        root.setSpacing(5);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Сонгууль");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * 1 ширхэг аймгийг илэрхийлэх Node
     *
     * @param model аймаг
     * @return Node
     */
    private HBox row(Province model) {
        final Election task = new Election(model);
        tasks.add(task);

        final HBox box = new HBox();
        box.setSpacing(15);

        final Label name = new Label(model.getName());
        name.setPrefWidth(100);
        name.setFont(new Font("Arial Bold", 15));

        final ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(150);
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(task.progressProperty());

        final Label current = new Label(model.getCurrent() + "/" + model.getPopulation());
        current.setPrefWidth(70);
        current.setFont(new Font("Arial Bold", 15));
        current.textProperty().unbind();
        current.textProperty().bind(task.messageProperty());

        final Label result = new Label();
        result.setPrefWidth(200);
        result.setFont(new Font("Arial Bold", 15));

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, t -> {
            result.setText(model.getCurrent() + "/" + model.getPopulation());
            this.result.add(task.getValue());
        });
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_CANCELLED, t -> {
            result.setText(model.getCurrent() + "/" + model.getPopulation());
            this.result.add(model);
        });

        new Thread(task).start();

        box.getChildren().addAll(name, progressBar, current, result);
        return box;
    }

    /**
     * Санамсаргүйгээр аймаг үүсгэх
     */
    private List<Province> getRandomProvinces() {
        Random r = new Random();
        //Аймгийн тоо
        int numberOfDraws = 22;
        //Нийт сонгогчдын тоо
        int targetSum = 1200000;

        List<Integer> load = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < numberOfDraws; i++) {
            int next = r.nextInt(targetSum) + 1;
            load.add(next);
            sum += next;
        }

        double scale = 1d * targetSum / sum;
        sum = 0;
        for (int i = 0; i < numberOfDraws; i++) {
            load.set(i, (int) (load.get(i) * scale));
            sum += load.get(i);
        }

        while (sum++ < targetSum) {
            int i = r.nextInt(numberOfDraws);
            load.set(i, load.get(i) + 1);
        }

        List<String> names = Arrays.asList("Улаанбаатар", "Архангай", "Баян-Өлгий", "Баянхонгор",
                "Булган", "Говь-Алтай", "Говьсүмбэр", "Дархан-Уул", "Дорноговь", "Дорнод",
                "Дундговь", "Завхан", "Орхон", "Өвөрхангай", "Өмнөговь", "Сүхбаатар", "Сэлэнгэ",
                "Төв", "Увс", "Ховд", "Хөвсгөл", "Хэнтий"
        );


        List<Province> provinces = load.stream().map(e -> new Province(names.get(load.indexOf(e)), e, 0, r.nextInt(900 - 850) + 850)).toList();
        provinces.forEach(province -> {
            if (province.getPopulation() > 50000) {
                provinces.stream()
                        .filter(f -> !f.getName().equals(province.getName()))
                        .filter(f -> f.getPopulation() + province.getPopulation() < 50000)
                        .findFirst().ifPresent(el -> {
                            province.setPopulation(province.getPopulation() - 50000);
                            el.setPopulation(province.getPopulation());
                        });
            }
        });

        return provinces;
    }
}
