package ru.nsu.shushakov.zachetka;

/**
 * exception class.
 */
public class SemesterException extends Exception {
    /**
     * if semester > 8.
     *
     * @param message error text.
     */
    public SemesterException(String message) {
        super(message);
    }
}
