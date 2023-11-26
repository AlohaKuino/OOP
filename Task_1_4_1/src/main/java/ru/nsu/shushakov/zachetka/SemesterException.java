package ru.nsu.shushakov.zachetka;

/**
 * exception class.
 */
public class SemesterException extends Exception {
    /**
     * @param message error text.
     *
     * if semester > 8.
     */
    public SemesterException(String message) {
        super(message);
    }
}
