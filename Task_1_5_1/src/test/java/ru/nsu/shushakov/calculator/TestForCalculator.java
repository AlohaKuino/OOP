package ru.nsu.shushakov.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

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
        assertEquals("0.0", Calculator.parseInputString("sin + - 1 2 1"));
    }
    
    @Test
    void helpTest() {
        assertEquals(0.0, Help.isNumber("0.0"));
    }

    @Test
    void letsCount() {
        assertEquals("0.8770383998668522", Calculator.parseInputString("sqrt sin cos 0.5"));
    }

    @Test
    void letsCountLongStuff() {
        assertEquals("0.9173172759781081", Calculator.parseInputString("sqrt sin cos log + - * / 1 1 1 1 1"));
    }

    @Test
    void wowSqrtWorks() {
        assertEquals("2.0", Calculator.parseInputString("sqrt 4"));
    }

    @Test
    void wowLogWorks() {
        assertEquals("0.1823215567939546", Calculator.parseInputString("log 1.2"));
    }

    @Test
    void wowCosWorks() {
        assertEquals("0.5403023058681398", Calculator.parseInputString("cos -1"));
    }

    @Test
    void wowSinWorks() {
        assertEquals("-0.8414709848078965", Calculator.parseInputString("sin -1"));
    }

    @Test
    void wowPlusWorks() {
        assertEquals("2.5", Calculator.parseInputString("+ 1.5 1"));
    }

    @Test
    void wowMinusWorks() {
        assertEquals("0.0", Calculator.parseInputString("- 1 1"));
    }

    @Test
    void wowMultWorks() {
        assertEquals("123.0", Calculator.parseInputString("* 123 1"));
    }

    @Test
    void wowDivWorks() {
        assertEquals("61.0", Calculator.parseInputString("/ 122 2"));
    }

    @Test
    void wowPowWorks() {
        assertEquals("164399.1533798152", Calculator.parseInputString("pow 122 2.5"));
    }

    @Test
    void wowWorks() {
        assertEquals("-0.7617387990249458", Calculator.parseInputString("sin pow 122 2"));
    }

    @Test
    public void wrongFormat() throws  WrongFormatException {
        Exception exception = assertThrows(WrongFormatException.class, () -> {
            Calculator.parseInputString("son 2");
        });
    }

    @Test
    void alotOfArguments() {
        Exception exception = assertThrows(WrongFormatException.class, () -> {
            Calculator.parseInputString("sin 1 2");
        });
    }

    @Test
    void fewArguments() throws NoSuchElementException {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            Calculator.parseInputString("pow 0");
        });
    }

    @Test
    void fewArguments2() throws NoSuchElementException {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            Calculator.parseInputString("sin");
        });
    }

    @Test
    void powErrorInf() {
        assertEquals("It's infinity", Calculator.parseInputString("pow 0 -1"));
    }

    @Test
    void powErrorNan() {
        assertEquals("It's nan", Calculator.parseInputString("pow -1 2.5"));
    }
}
