package ru.nsu.shushakov.snake;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button startGameButton;

    @FXML
    private SplitMenuButton selectGameLevelButton;

    @FXML
    private MenuItem action1MenuItem;

    @FXML
    private MenuItem action2MenuItem;

    @FXML
    void onStartGameButtonClick(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.start(stage);
        startGameButton.getScene().getWindow().hide();
    }

    @FXML
    void onAction1MenuItemClick(ActionEvent event) {
        System.out.println("Action 1 selected");
    }

    @FXML
    void onAction2MenuItemClick(ActionEvent event) {
        System.out.println("Action 2 selected");
    }
}
