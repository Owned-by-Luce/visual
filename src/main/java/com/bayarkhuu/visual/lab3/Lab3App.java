package com.bayarkhuu.visual.lab3;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Lab3App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setPadding(new Insets(15));
        root.setVgap(10);
        root.setHgap(10);

        TextField aInput = new TextField();
        TextField bInput = new TextField();
        TextField cInput = new TextField();
        TextField x1 = new TextField();
        TextField x2 = new TextField();
        x1.setPrefWidth(70);
        x2.setPrefWidth(70);

        root.add(new Label("a:"), 0, 0);
        root.add(aInput, 1, 0);

        root.add(new Label("b:"), 0, 1);
        root.add(bInput, 1, 1);

        root.add(new Label("c:"), 0, 2);
        root.add(cInput, 1, 2);

        Button btnSolve = new Button("Solve");
        btnSolve.setPrefWidth(80);
        btnSolve.setOnAction(e -> {
            double a, b, c;
            try {
                a = Double.parseDouble(aInput.getText().trim());
                b = Double.parseDouble(bInput.getText().trim());
                c = Double.parseDouble(cInput.getText().trim());
            } catch (NumberFormatException | NullPointerException ex) {
                System.out.println("Тоон утга оруулна уу.");
                return;
            }

            double[] quad = quad(a, b, c);

            if (quad == null) {
                System.out.println("Шийдгүй");
                return;
            }

            if (quad.length == 1) {
                x1.setText(String.valueOf(quad[0]));
            } else {
                x1.setText(String.valueOf(quad[0]));
                x2.setText(String.valueOf(quad[1]));
            }
        });
        root.add(btnSolve, 0, 3, 2, 1);
        GridPane.setHalignment(btnSolve, HPos.CENTER);

        HBox hBox = new HBox(new Label("x1:"), x1, new Label("x2:"), x2);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(5);

        root.add(hBox, 0, 4, 2, 1);

        Button btnClose = new Button("Close");
        btnClose.setPrefWidth(80);
        btnClose.setOnAction(e -> primaryStage.close());
        root.add(btnClose, 0, 5, 2, 1);
        GridPane.setHalignment(btnClose, HPos.CENTER);

        Scene scene = new Scene(root, 215, 230);
        primaryStage.setTitle("Quad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double[] quad(double a, double b, double c) {
        double d = b * b - 4 * a * c;
        if (d > 0) {
            double x1 = (-b + Math.pow(d, 0.5)) / (2.0 * a);
            double x2 = (-b - Math.pow(d, 0.5)) / (2.0 * a);
            return new double[]{x1, x2};
        } else if (d == 0) {
            double x1 = -b / (2 * a);
            return new double[]{x1};
        } else {
            return null;
        }
    }
}
