package com.bayarkhuu.visual.home.home2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Гэрийн даалгавар
 * @author Баярхүү.Лув
 */
public class Home2App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setPadding(new Insets(15));
        root.setSpacing(10);
        root.setStyle("-fx-background-color: #FFE0C1;");

        StackPane stackPane = new StackPane();

        Label caption = getLabel("Textbook Sale Information");
        caption.setTranslateX(-105);
        caption.setTranslateY(-120);

        stackPane.getChildren().add(caption);
        stackPane.setStyle("-fx-border-color: white; -fx-border-width: 2");
        stackPane.setPadding(new Insets(0, 0, 20, 10));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(getLabel("Book Title:"), 0, 0);
        grid.add(getLabel("ISBN (Identifier):"), 0, 1);
        grid.add(getLabel("Price:"), 0, 2);
        grid.add(getLabel("Quantity"), 0, 3);
        grid.add(getLabel("Subtotal"), 0, 4);
        grid.add(getLabel("Sales Tax"), 0, 5);
        grid.add(getLabel("Total Due"), 0, 6);

        TextField title = getTextField();
        title.setMaxWidth(265);
        title.setPrefWidth(265);
        TextField identifier = getTextField();
        identifier.setMaxWidth(200);
        identifier.setPrefWidth(200);
        TextField price = getTextField();
        TextField quantity = getTextField();
        TextField subtotal = getTextField();
        TextField tax = getTextField();
        TextField total = getTextField();

        grid.add(title, 1, 0);
        grid.add(identifier, 1, 1);
        grid.add(price, 1, 2);
        grid.add(quantity, 1, 3);
        grid.add(subtotal, 1, 4);
        grid.add(tax, 1, 5);
        grid.add(total, 1, 6);

        grid.setTranslateY(10);
        stackPane.getChildren().add(grid);

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.TOP_CENTER);

        Button btnCompute = getButton("Compute");
        Button btnReset = getButton("Reset");
        Button btnTotals = getButton("Totals");
        Button btnExit = getButton("Exit");
        buttons.getChildren().addAll(btnCompute, btnReset, btnTotals, btnExit);
        root.getChildren().addAll(stackPane, buttons);

        btnCompute.setOnAction(e -> {
            double doublePrice = Double.parseDouble(price.getText().isEmpty() ? "0" : price.getText());
            double doubleQuantity = Double.parseDouble(quantity.getText().isEmpty() ? "0" : quantity.getText());
            subtotal.setText(String.valueOf(doublePrice * doubleQuantity));
        });

        btnReset.setOnAction(e -> {
            title.clear();
            identifier.clear();
            price.clear();
            quantity.clear();
            subtotal.clear();
            tax.clear();
            total.clear();
        });

        btnTotals.setOnAction(e -> {
            double doubleSubtotal = Double.parseDouble(subtotal.getText());
            double doubleTax = doubleSubtotal * 0.06;
            tax.setText(String.valueOf(doubleTax));
            total.setText(String.valueOf(doubleSubtotal + doubleTax));
        });

        btnExit.setOnAction(e -> primaryStage.close());

        Scene scene = new Scene(root, 450, 320);
        primaryStage.setTitle("Book Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label getLabel(String caption) {
        Label label = new Label(caption);
        label.setStyle("-fx-font-weight: bold; -fx-background-color: #FFE0C1;");
        label.setFont(new Font(15));
        return label;
    }

    private TextField getTextField() {
        TextField textField = new TextField();
        textField.setMaxWidth(100);
        return textField;
    }

    private Button getButton(String caption) {
        Button btn = new Button(caption);
        btn.setStyle("-fx-background-color: #FCC281; -fx-font-size: 15; -fx-font-weight: bold; -fx-border-color: white; -fx-border-radius: 8; -fx-background-radius: 8");
        return btn;
    }
}
