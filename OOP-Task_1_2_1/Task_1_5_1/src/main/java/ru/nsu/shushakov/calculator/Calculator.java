package ru.nsu.shushakov.calculator;

import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Calculator {
    private String inputString;

    public Calculator() throws EndException {
        Help.getInputString();
    }

    public static void parseInputString(String inputString) throws EndException {
        String[] tmpString = inputString.split(" ");
        Deque<Double> operands = new ArrayDeque<>();
        for (int i = tmpString.length - 1; i >= 0; i--) {
            if (Help.isNumber(tmpString[i])) {
                operands.push(Double.parseDouble(tmpString[i]));
            } else {
                try {
                    double firstOperand = operands.pop();
                    double secondOperand;
                    switch (tmpString[i]) {
                        case "sin" -> operands.push(Math.sin(firstOperand));
                        case "cos" -> operands.push(Math.cos(firstOperand));
                        case "log" -> {
                            if (firstOperand < 0) {
                                System.out.println("Logarithm argument must be greater than zero");
                                operands.clear();
                            } else {
                                operands.push(Math.log(firstOperand));
                            }
                        }
                        case "sqrt" -> {
                            if (firstOperand < 0) {
                                System.out.println("Sqrt argument must be greater than zero");
                                operands.clear();
                            } else {
                                operands.push(Math.sqrt(firstOperand));
                            }
                        }
                        case "pow" -> {
                            secondOperand = operands.pop();
                            if ((int) secondOperand - secondOperand != 0 && firstOperand < 0) {
                                System.out.println("It's not a number");
                                operands.clear();
                            } else if (firstOperand == 0 && secondOperand < 0) {
                                System.out.println("It's infinity");
                                operands.clear();
                            } else {
                                operands.push(Math.pow(firstOperand, secondOperand));
                            }

                        }
                        case "+" -> {
                            secondOperand = operands.pop();
                            operands.push(firstOperand + secondOperand);
                        }
                        case "-" -> {
                            secondOperand = operands.pop();
                            operands.push(firstOperand - secondOperand);
                        }
                        case "*" -> {
                            secondOperand = operands.pop();
                            operands.push(firstOperand * secondOperand);
                        }
                        case "/" -> {
                            secondOperand = operands.pop();
                            if (secondOperand == 0) {
                                System.out.println("Division second argument must not be a zero");
                                operands.clear();
                            } else {
                                operands.push(firstOperand / secondOperand);
                            }
                        }
                        default -> System.out.println("Wrong Format");
                    }
                } catch (EmptyStackException | NoSuchElementException e) {
                    if (tmpString[i].equals("stop")) {
                        throw new EndException();
                    }
                    operands.clear();
                }
            }

        }
        if (operands.size() == 1) {
            System.out.println(operands.pop());
        } else {
            System.out.println("Wrong Format");
        }
        operands.clear();
    }
}