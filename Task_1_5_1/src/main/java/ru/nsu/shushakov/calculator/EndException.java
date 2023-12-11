package ru.nsu.shushakov.calculator;

public class EndException extends Exception {
        public EndException() {
            super("\n\n\nStopped\n\n\n");
        }
}

