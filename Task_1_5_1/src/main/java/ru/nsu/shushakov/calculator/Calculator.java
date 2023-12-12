package ru.nsu.shushakov.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * class that makes calculations.
 */
public class Calculator {
    /**
     * calculate and parse method.
     *
     * @param inputString string with input information.
     * @throws EndException if stop is written.
     * @throws WrongFormatException if format is wrong.
     * @throws NoSuchElementException if i actually don't remember why, but i need it.
     * @throws EmptyStackException if stack is empty, for example if we put just sin in input.
     */
    protected static void parseInputString(String inputString) throws EndException,
            WrongFormatException, NoSuchElementException, EmptyStackException {
        String[] tmpString = inputString.split(" ");
        Deque<Double> operands = new ArrayDeque<>();
        for (int i = tmpString.length - 1; i >= 0; i--) {
            if (Help.isNumber(tmpString[i])) {
                operands.push(Double.parseDouble(tmpString[i]));
            } else {
                double firstOperand;
                double secondOperand;
                switch (Help.typeOfOperation(tmpString[i])) {
                    case SIN -> {
                        firstOperand = operands.pop();
                        operands.push(Math.sin(firstOperand));
                    }
                    case COS -> {
                        firstOperand = operands.pop();
                        operands.push(Math.cos(firstOperand));
                    }
                    case LOG -> {
                        firstOperand = operands.pop();
                        operands.push(Math.log(firstOperand));
                    }
                    case SQRT -> {
                        firstOperand = operands.pop();
                        operands.push(Math.sqrt(firstOperand));
                    }
                    case POW -> {
                        firstOperand = operands.pop();
                        secondOperand = operands.pop();
                        operands.push(Math.pow(firstOperand, secondOperand));
                    }
                    case PLUS -> {
                        firstOperand = operands.pop();
                        secondOperand = operands.pop();
                        operands.push(firstOperand + secondOperand);
                    }
                    case MINUS -> {
                        firstOperand = operands.pop();
                        secondOperand = operands.pop();
                        operands.push(firstOperand - secondOperand);
                    }
                    case MULTIPLICATION -> {
                        firstOperand = operands.pop();
                        secondOperand = operands.pop();
                        operands.push(firstOperand * secondOperand);
                    }
                    case DIVISION -> {
                        firstOperand = operands.pop();
                        secondOperand = operands.pop();
                        operands.push(firstOperand / secondOperand);
                    }
                    case STOP -> throw new EndException("Stop");
                    default -> throw new WrongFormatException("Wrong Format");
                }
            }
        }
        if (operands.peek().isInfinite()) {
            System.out.println("It's infinity");
        } else if (operands.peek().isNaN()) {
            System.out.println("It's nan");
        } else if (operands.size() == 1) {
            System.out.println(operands.pop());
        } else {
            throw new WrongFormatException("Wrong Format");
        }
        operands.clear();
    }
}
