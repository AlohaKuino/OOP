package ru.nsu.shushakov.calculator;

import java.util.Scanner;

/**
 * helping class.
 */
public class Help {
    /**
     * gets a string from console.
     *
     * @throws EndException if stop is printed than stop.
     */
    public static void getInputString() throws EndException {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                String inputString = input.nextLine();
                Calculator.parseInputString(inputString);
            } catch (EndException e) {
                break;
            }
        }
    }

    /**
     * checks if string is a number.
     *
     * @param maybeNumber string from console.
     * @return true if it's a number false otherwise.*
     */
    public static boolean isNumber(String maybeNumber) {
        try {
            Double.parseDouble(maybeNumber);
            return true;
        } catch (NumberFormatException ohNoException) {
            return false;
        }
    }
}
