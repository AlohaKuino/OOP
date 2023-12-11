package ru.nsu.shushakov.calculator;

import java.util.Scanner;

public class Help {
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

    public static boolean isNumber(String maybeNumber) {
        try {
            Double.parseDouble(maybeNumber);
            return true;
        } catch (NumberFormatException ohNoException) {
            return false;
        }
    }
}
