package ru.nsu.shushakov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SampleTest {
    //тесты плюса
    @Test
    void checkPlus() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3, 4, 7});
        assertArrayEquals(new int[]{2, 4, 6, 4, 7}, pol1.plus(pol2).getPolynomCof());
    }

    @Test
    void checkPlusEqual() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3});
        Polinomial pol2 = new Polinomial(new int[]{4, 5, 6});
        assertArrayEquals(new int[]{5, 7, 9}, pol1.plus(pol2).getPolynomCof());
    }

    @Test
    void checkPlusEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{});
        Polinomial pol2 = new Polinomial(new int[]{});
        assertArrayEquals(new int[]{}, pol1.plus(pol2).getPolynomCof());
    }

    @Test
    void checkPlusOneNotEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{});
        Polinomial pol2 = new Polinomial(new int[]{1, 2});
        assertArrayEquals(new int[]{1, 2}, pol1.plus(pol2).getPolynomCof());
    }

    //тесты минуса
    @Test
    void checkMinusAddMinus() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3, 4, 7});
        assertArrayEquals(new int[]{0, 0, 0, -4, -7}, pol1.minus(pol2).getPolynomCof());
    }

    @Test
    void checkMinusNorm() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3, 4, 7});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3});
        assertArrayEquals(new int[]{0, 0, 0, 4, 7}, pol1.minus(pol2).getPolynomCof());
    }

    @Test
    void checkMinusEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{});
        Polinomial pol2 = new Polinomial(new int[]{});
        assertArrayEquals(new int[]{}, pol1.minus(pol2).getPolynomCof());
    }

    @Test
    void checkMinusFirstIsEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3});
        assertArrayEquals(new int[]{-1, -2, -3}, pol1.minus(pol2).getPolynomCof());
    }

    @Test
    void checkMinusSecondIsEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3});
        Polinomial pol2 = new Polinomial(new int[]{});
        assertArrayEquals(new int[]{1, 2, 3}, pol1.minus(pol2).getPolynomCof());
    }

    //тесты умножения
    @Test
    void checkMultNorm() {
        Polinomial pol1 = new Polinomial(new int[]{5, 4, 3, 1});
        Polinomial pol2 = new Polinomial(new int[]{1, 3, 5});
        assertArrayEquals(new int[]{5, 19, 40, 30, 18, 5}, pol1.mult(pol2).getPolynomCof());
    }

    @Test
    void checkMultAboutMinus() {
        Polinomial pol1 = new Polinomial(new int[]{-5, 4, 3});
        Polinomial pol2 = new Polinomial(new int[]{1, 3});
        assertArrayEquals(new int[]{-5, -11, 15, 9}, pol1.mult(pol2).getPolynomCof());
    }

    @Test
    void checkMultAboutAllMinus() {
        Polinomial pol1 = new Polinomial(new int[]{-5, -4, -3});
        Polinomial pol2 = new Polinomial(new int[]{-1, -3});
        assertArrayEquals(new int[]{5, 19, 15, 9}, pol1.mult(pol2).getPolynomCof());
    }

    @Test
    void checkMultEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{});
        Polinomial pol2 = new Polinomial(new int[]{});
        assertArrayEquals(new int[]{}, pol1.mult(pol2).getPolynomCof());
    }

    @Test
    void checkMultZero() {
        Polinomial pol1 = new Polinomial(new int[]{0});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3});
        assertArrayEquals(new int[]{0, 0, 0}, pol1.mult(pol2).getPolynomCof());
    }

    @Test
    void checkSpot() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        assertEquals(9, pol1.inSpot(1));
    }

    @Test
    void checkSpotEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        assertEquals(0, pol1.inSpot());
    }

    @Test
    void checkEq() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        Polinomial pol2 = new Polinomial(new int[]{1, 3, 5});
        assertEquals(pol1, pol2);
    }

    @Test
    void checkEqWrong() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 5});
        assertNotEquals(pol1, pol2);
    }

    @Test
    void checkEqEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        assertFalse(pol1.equals(null));
    }


    @Test
    void checkDer() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        assertArrayEquals(new int[]{3, 10}, pol1.ithDerivative(1).getPolynomCof());
    }

    @Test
    void checkDerForNthng() {
        Polinomial pol1 = new Polinomial(new int[]{1});
        assertArrayEquals(new int[]{0}, pol1.ithDerivative(1).getPolynomCof());
    }

    @Test
    void checkDer2() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        assertArrayEquals(new int[]{10}, pol1.ithDerivative(2).getPolynomCof());
    }

    @Test
    void checkDer3() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        assertArrayEquals(new int[]{0}, pol1.ithDerivative(3).getPolynomCof());
    }

    @Test
    void checkDerNeg() {
        Polinomial pol1 = new Polinomial(new int[]{1, -2, 5});
        assertArrayEquals(new int[]{-2, 10}, pol1.ithDerivative(1).getPolynomCof());
    }

    @Test
    void checkDerZero() {
        Polinomial pol1 = new Polinomial(new int[]{0, 0, 0});
        assertArrayEquals(new int[]{0, 0}, pol1.ithDerivative(1).getPolynomCof());
    }

    @Test
    void checkDerEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{});
        assertArrayEquals(new int[]{}, pol1.ithDerivative(1).getPolynomCof());
    }

    @Test
    void checkStrSimple() {
        Polinomial pol1 = new Polinomial(new int[]{1});
        assertEquals("1", pol1.toString());
    }

    @Test
    void checkStrMedium() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        assertEquals("5x^2 + 3x + 1", pol1.toString());
    }

    @Test
    void checkStrHard() {
        Polinomial pol1 = new Polinomial(new int[]{1, -3, 5, -10, 15});
        assertEquals("15x^4 - 10x^3 + 5x^2 - 3x + 1", pol1.toString());
    }

    @Test
    void checkStrMinusIsFirst() {
        Polinomial pol1 = new Polinomial(new int[]{1, -3, 5, -10, -15});
        assertEquals("-15x^4 - 10x^3 + 5x^2 - 3x + 1", pol1.toString());
    }

    @Test
    void checkStrZero() {
        Polinomial pol1 = new Polinomial(new int[]{0, 0, 0});
        assertEquals("0", pol1.toString());
    }

    @Test
    void checkStrEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{});
        assertEquals("...", pol1.toString());
    }
}
