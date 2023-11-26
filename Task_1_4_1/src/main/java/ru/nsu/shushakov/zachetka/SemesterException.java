package ru.nsu.shushakov.zachetka;

/**
 * exception class.
 */
public class SemesterException extends Exception {
    /**
     * @param message error text.
     *                <p>
     *                if semester > 8.
     */
    public SemesterException(String message) {
        super(message);
    }
}
