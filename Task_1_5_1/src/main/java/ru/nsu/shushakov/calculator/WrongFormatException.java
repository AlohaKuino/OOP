package ru.nsu.shushakov.calculator;

/**
 * exception to show if input is wrong .
 */
public class WrongFormatException extends RuntimeException {
    /**
     * SUPER MESSAGE.
     */
    public WrongFormatException(String msg) {
        super(msg);
    }
}

