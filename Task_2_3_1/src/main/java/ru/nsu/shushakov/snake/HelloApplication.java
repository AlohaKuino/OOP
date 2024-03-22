package ru.nsu.shushakov.snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent menuRoot = menuLoader.load();
        Scene menuScene = new Scene(menuRoot);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Snake Game - Menu");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
