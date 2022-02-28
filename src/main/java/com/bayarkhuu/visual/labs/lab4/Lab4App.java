package com.bayarkhuu.visual.labs.lab4;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Lab4App extends Application {
    @FXML
    private TextField aInput;
    @FXML
    private TextField bInput;
    @FXML
    private TextField cInput;
    @FXML
    private TextField x1;
    @FXML
    private TextField x2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Lab4App.class.getResource("Lab4Design.fxml"));

        Scene scene = new Scene(loader.load(), 315, 255);
        primaryStage.setTitle("Quad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    protected void solve() {
        double a, b, c;
        try {
            a = Double.parseDouble(aInput.getText().trim());
            b = Double.parseDouble(bInput.getText().trim());
            c = Double.parseDouble(cInput.getText().trim());
        } catch (NumberFormatException | NullPointerException ex) {
            ex.printStackTrace();
            return;
        }

        double[] quad = quad(a, b, c);

        if (quad == null) {
            x1.setText("Шийдгүй.");
            return;
        }
        x1.setText(String.valueOf(quad[0]));
        x2.setText(String.valueOf(quad[1]));
    }

    private double[] quad(double a, double b, double c) {
        double d = b * b - 4 * a * c;
        if (d > 0) {
            double x1 = (-b + Math.pow(d, 0.5)) / (2.0 * a);
            double x2 = (-b - Math.pow(d, 0.5)) / (2.0 * a);
            return new double[]{x1, x2};
        } else if (d == 0) {
            double x1 = -b / (2 * a);
            return new double[]{x1, x1};
        } else {
            return null;
        }
    }
}
