package ru.nsu.shushakov.zachetka;

/**
 * exception class.
 */
public class SemesterException extends Exception {
    /**
     * if semester greater 8.
     */
    public SemesterException() {
        super("\n\n are you this old? \n");
    }
}
