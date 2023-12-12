package ru.nsu.shushakov.calculator;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * class consist of helping methods that i use in calculator.
 */
public class Help {
    private static Scanner input;

    /**
     * get input string from input stream.
     *
     * @return next line of input.
     */
    protected static String getInputString() {
        return input.nextLine();
    }

    /**
     * func that separates number from operation.
     *
     * @param maybeNumber string that can be double.
     * @return true if it is a number.
     */
    protected static boolean isNumber(String maybeNumber) {
        try {
            Double.parseDouble(maybeNumber);
            return true;
        } catch (NumberFormatException ohNoException) {
            return false;
        }
    }

    /**
     * main func that starts calculations.
     *
     * @param args just because main needs it.
     */
    public static void main(String[] args) {
        input = new Scanner(System.in);
        while (true) {
            try {
                Calculator.parseInputString(Help.getInputString());
            } catch (EndException e) {
                System.out.println("Stop");
                break;
            } catch (WrongFormatException | NoSuchElementException | EmptyStackException e) {
                System.out.println("Wrong Format");
            }
        }
    }

    /**
     * enum to get operation type.
     *
     * @param operation read input string.
     * @return enum based on string.
     */
    protected static OperationType typeOfOperation(String operation) {
        return switch (operation) {
            case "/" -> OperationType.DIVISION;
            case "*" -> OperationType.MULTIPLICATION;
            case "+" -> OperationType.PLUS;
            case "-" -> OperationType.MINUS;
            case "sin" -> OperationType.SIN;
            case "cos" -> OperationType.COS;
            case "pow" -> OperationType.POW;
            case "log" -> OperationType.LOG;
            case "sqrt" -> OperationType.SQRT;
            case "stop" -> OperationType.STOP;
            default -> OperationType.ERROR;
        };
    }
}
