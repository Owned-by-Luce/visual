package com.bayarkhuu.visual.labs.lab8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Lab8App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ListView<User> userView = new ListView<>();

        userView.getItems().addAll(
                new User("user1FN", "user1LN", "p1"),
                new User("user2FN", "user2LN", "p2"),
                new User("user3FN", "user3LN", "p3"),
                new User("user4FN", "user4LN", "p4"),
                new User("user5FN", "user5LN", "p5"),
                new User("user6FN", "user6LN", "p6")
        );

        userView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<User> call(ListView<User> userListView) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(User user, boolean empty) {
                        if (user == null || empty) return;

                        super.updateItem(user, false);
                        ImageView image = null;
                        try {
                            image = new ImageView(new Image(new FileInputStream("src/main/resources/static/portrait/" + user.getImage() + ".jpeg")));
                            image.setFitWidth(100);
                            image.setFitHeight(100);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        setGraphic(image);
                        setText(user.getFirstName() + " " + user.getLastName());
                    }
                };
            }
        });

        Scene scene = new Scene(userView, 320, 540);
        primaryStage.setTitle("ListView");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
