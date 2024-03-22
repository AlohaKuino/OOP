package ru.nsu.shushakov.snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class SnakeGame extends Application {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    public static GameController gameController;
    public static boolean gameOver;
    private static List<Point> snake;
    private static int speed;
    private static Point food;
    public static boolean restart;
    private static Random random;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private static Canvas canvas;

    static Direction prevDir = Direction.RIGHT;

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public SnakeGame() {
        gameOver = false;
        food  = new Point(0, 10);
        speed = 10;
        restart  = false;
        snake = new ArrayList<>();
        snake.add(new Point(0, 0));
        random = new Random();
        canvas = new Canvas(WIDTH * 10, HEIGHT * 10);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        generateFood();

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/game.fxml"));

        Parent gameRoot = gameLoader.load();
        Scene gameScene = new Scene(gameRoot);
        primaryStage.setScene(gameScene);
        primaryStage.setTitle("Snake Game - Game");
        gameController = gameLoader.getController();

        gameController.updateScore(0);
        gameController.updateMaxScore();

        gameController.initialize();
        primaryStage.show();

        new AnimationTimer() {
            long lastTick = 0;
            public void handle(long now) {
                restart();
                if (lastTick == 0) {
                    lastTick = now;
                    try {
                        tick(gc, gameController.getDirection());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }

                if (now - lastTick > 1000000000 / speed) {
                    lastTick = now;
                    gameController.eventer(gameScene);
                    try {
                        tick(gc, gameController.getDirection());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    gameController.drawSnake(snake, food);

                }
            }
        }.start();
    }

    public static void tick(GraphicsContext gc, Direction direction) throws InterruptedException {

        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction) {
            case UP:
                if (snake.get(0).y == 0) {
                    snake.get(0).y = HEIGHT - 1;
                } else {
                    snake.get(0).y--;
                }
                prevDir = Direction.UP;
                break;
            case DOWN:
                if (snake.get(0).y == HEIGHT - 1) {
                    snake.get(0).y = 0;
                } else {
                    snake.get(0).y++;
                }
                prevDir = Direction.DOWN;
                break;
            case LEFT:
                if (snake.get(0).x == 0) {
                    snake.get(0).x = WIDTH - 1;
                } else {
                    snake.get(0).x--;
                }
                prevDir = Direction.LEFT;
                break;
            case RIGHT:
                if (snake.get(0).x == WIDTH - 1) {
                    snake.get(0).x = 0;
                } else {
                    snake.get(0).x++;
                }
                prevDir = Direction.RIGHT;
                break;
        }
        for(int i = 1; i  < snake.size(); i ++){
            if(snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y){
                System.out.println("game over");
                restart = true;
                restart();
                break;
            }
        }
        if(snake.get(0).x == food.x && snake.get(0).y == food.y){
            speed ++;
            snake.add(new Point(food.x, food.y));
            gameController.increaseScore();
            generateFood();
        }
    }
    public static void restart(){
        if(restart) {
            snake = new ArrayList<>();
            snake.add(new Point(0, 0));
            random = new Random();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            restart = false;
            speed = 10;
            gameController.updateMaxScore();
            gameController.updateScore(0);
            gameOver = false;
        }
    }
    public static void generateFood() {
        food.x = random.nextInt(WIDTH);
        food.y = random.nextInt(HEIGHT);
        while (snake.contains(food)) {
            food.x = random.nextInt(WIDTH);
            food.y = random.nextInt(HEIGHT);
        }
    }
}