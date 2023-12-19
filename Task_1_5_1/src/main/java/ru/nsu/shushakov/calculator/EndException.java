package ru.nsu.shushakov.calculator;

/**
 * exception to stop endless calculations.
 */
public class EndException extends RuntimeException {
    /**
     * SUPER MESSAGE.
     */
    public EndException(String msg) {
        super(msg);
    }
}

