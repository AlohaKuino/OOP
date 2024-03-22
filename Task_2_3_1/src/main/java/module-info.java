module ru.nsu.shushakov.snake {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.shushakov.snake to javafx.fxml;
    exports ru.nsu.shushakov.snake;
}