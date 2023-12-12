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
    void verySimple() {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("sin + - 1 2 1\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("0.0\nStop", output.toString().trim());
    }

    @Test
    void letsCount() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("sqrt sin cos 0.5\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("0.8770383998668522\nStop", output.toString().trim());
    }

    @Test
    void letsCountLongStuff() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("sqrt sin cos log + - * / 1 1 1 1 1\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("0.9173172759781081\nStop", output.toString().trim());
    }

    @Test
    void wowSqrtWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("sqrt 4\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("2.0\nStop", output.toString().trim());
    }

    @Test
    void wowLogWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("log 1.2\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("0.1823215567939546\nStop", output.toString().trim());
    }

    @Test
    void wowCosWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("cos -1\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("0.5403023058681398\nStop", output.toString().trim());
    }

    @Test
    void wowSinWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("sin -1\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("-0.8414709848078965\nStop", output.toString().trim());
    }

    @Test
    void wowPlusWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("+ 1.5 1\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("2.5\nStop", output.toString().trim());
    }

    @Test
    void wowMinusWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("- 1 1\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("0.0\nStop", output.toString().trim());
    }

    @Test
    void wowMultWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("* 123 1\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("123.0\nStop", output.toString().trim());
    }

    @Test
    void wowDivWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("/ 122 2\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("61.0\nStop", output.toString().trim());
    }

    @Test
    void wowPowWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("pow 122 2.5\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("164399.1533798152\nStop", output.toString().trim());
    }

    @Test
    void wowWorks() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("sin pow 122 2\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("-0.7617387990249458\nStop", output.toString().trim());
    }

    @Test
    void wrongFormat() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("son 2\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("Wrong Format\nStop", output.toString().trim());
    }

    @Test
    void alotOfArguments() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("sin 1 2\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("Wrong Format\nStop", output.toString().trim());
    }

    @Test
    void fewArguments() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("pow 0\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("Wrong Format\nStop", output.toString().trim());
    }

    @Test
    void fewArguments2() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("sin\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("Wrong Format\nStop", output.toString().trim());
    }

    @Test
    void powErrorInf() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("pow 0 -1\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("It's infinity\nStop", output.toString().trim());
    }

    @Test
    void powErrorNan() throws EndException {
        final ByteArrayInputStream bais =
                new ByteArrayInputStream("pow -1 2.5\nstop".getBytes());
        System.setIn(bais);
        Help.main(null);
        assertEquals("It's nan\nStop", output.toString().trim());
    }
}
