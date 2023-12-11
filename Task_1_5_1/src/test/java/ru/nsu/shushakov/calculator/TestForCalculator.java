package ru.nsu.shushakov.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test class.
 */
public class TestForCalculator {
    OutputStream output = new ByteArrayOutputStream();

    @AfterAll
    public static void tearDown() {
        System.setOut(System.out);
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    void verySimple() throws EndException {
        final ByteArrayInputStream input =
                new ByteArrayInputStream("sin + - 1 2 1\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("0.0", output.toString().trim());
    }

    @Test
    void letsCount() throws EndException {
        final ByteArrayInputStream input =
                new ByteArrayInputStream("sqrt sin cos 1\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("0.7172135376047702", output.toString().trim());
    }

    @Test
    void arithmeticTest() throws EndException {
        final ByteArrayInputStream input =
                new ByteArrayInputStream("sqrt sin cos log + - 1 1 1\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("0.9173172759781081", output.toString().trim());
    }

    @Test
    void logException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("log -1\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("Logarithm argument must be greater than zero\nWrong Format",
                output.toString().trim());
    }

    @Test
    void sqrtException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("sqrt -1\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("Sqrt argument must be greater than zero\nWrong Format",
                output.toString().trim());
    }

    @Test
    void powNanException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("pow -1 2.5\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("It's not a number\nWrong Format", output.toString().trim());
    }

    @Test
    void powInfException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("pow 0 -1\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("It's infinity\nWrong Format", output.toString().trim());
    }

    @Test
    void powFewArgumentsException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("pow 234\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("Wrong Format", output.toString().trim());
    }

    @Test
    void plusFewArgumentsException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("+ 234\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("Wrong Format", output.toString().trim());
    }

    @Test
    void minusFewArgumentsException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("- 234\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("Wrong Format", output.toString().trim());
    }

    @Test
    void multFewArgumentsException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("* 234\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("Wrong Format", output.toString().trim());
    }

    @Test
    void divisionFewArgumentsException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("/ 234\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("Wrong Format", output.toString().trim());
    }

    @Test
    void notAnumInArgsException() throws EndException {
        final ByteArrayInputStream input = new ByteArrayInputStream("/ a\nstop".getBytes());
        System.setIn(input);
        Help.getInputString();
        assertEquals("Wrong Format", output.toString().trim());
    }
}
