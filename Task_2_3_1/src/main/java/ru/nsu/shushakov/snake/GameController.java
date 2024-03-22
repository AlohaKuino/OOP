package ru.nsu.shushakov.snake;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.List;

public class GameController {
    @FXML
    public AnchorPane field;
    @FXML
    public Button restart;
    @FXML
    public Label score;
    @FXML
    public Label maxScore;
    @FXML
    private Canvas gameCanvas;

    private int scoreInt;
    private int maxScoreInt;
    private SnakeGame.Direction direction = SnakeGame.Direction.RIGHT;
    private SnakeGame.Direction prevDir = SnakeGame.Direction.RIGHT;

    public void setMaxScoreInt(int maxScoreInt) {
        this.maxScoreInt = maxScoreInt;
    }

    public void setScoreInt(int scoreInt) {
        this.scoreInt = scoreInt;
    }

    public int getMaxScoreInt() {
        return maxScoreInt;
    }

    public int getScoreInt() {
        return scoreInt;
    }

    public void updateScore(int currentScore) {
        setScoreInt(currentScore);
        score.setText(" " + getScoreInt());

    }
    public void updateMaxScore() {
        setMaxScoreInt(Math.max(getScoreInt(), getMaxScoreInt()));
        maxScore.setText(" " + getMaxScoreInt());
    }
    public void increaseScore(){
        scoreInt ++;
        updateScore(getScoreInt());
    }


    public void initialize() {
        Canvas canvas = new Canvas(400, 400);
        field.getChildren().add(canvas);

        setScoreInt(0);
        setMaxScoreInt(0);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        field.setStyle("-fx-border-color: black; -fx-border-right-width: 2px");

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void eventer(Scene scene){
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W && prevDir != SnakeGame.Direction.DOWN) {
                direction = SnakeGame.Direction.UP;
                prevDir = SnakeGame.Direction.UP;
            }
            if (key.getCode() == KeyCode.A && prevDir != SnakeGame.Direction.RIGHT) {
                direction = SnakeGame.Direction.LEFT;
                prevDir = SnakeGame.Direction.LEFT;
            }
            if (key.getCode() == KeyCode.S && prevDir != SnakeGame.Direction.UP) {
                direction = SnakeGame.Direction.DOWN;
                prevDir = SnakeGame.Direction.DOWN;
            }
            if (key.getCode() == KeyCode.D && prevDir != SnakeGame.Direction.LEFT) {
                direction = SnakeGame.Direction.RIGHT;
                prevDir = SnakeGame.Direction.RIGHT;
            }
        });
    }

    public SnakeGame.Direction getDirection() {
        return direction;
    }
    public void drawSnake(List<SnakeGame.Point> snake, SnakeGame.Point food) {
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        for (SnakeGame.Point c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * 10, c.y * 10, 10, 10);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * 10 + 1, c.y * 10 + 1, 10 - 2, 10 - 2);
        }
        gc.setFill(Color.RED);
        gc.fillRect(food.x * 10, food.y * 10, 10, 10);
    }
    public void restart(){

        SnakeGame.restart = true;

        direction = SnakeGame.Direction.RIGHT;
        prevDir = SnakeGame.Direction.LEFT;
    }
}
