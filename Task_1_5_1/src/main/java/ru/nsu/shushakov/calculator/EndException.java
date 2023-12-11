package ru.nsu.shushakov.calculator;

/**
 * exception to stop endless calculations.
 */
public class EndException extends Exception {
    /**
     * SUPER MESSAGE.
     */
        public EndException() {
            super("\n\n\nStopped\n\n\n");
        }
}

