package ru.nsu.shushakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleTest {
    @Test
    void checkPlus() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3, 4, 7});
        Assertions.assertArrayEquals(pol1.plus(pol2).polynomCof, new int[]{2, 4, 6, 4, 7});
    }

    @Test
    void checkPlusEqual() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3});
        Polinomial pol2 = new Polinomial(new int[]{4, 5, 6});
        Assertions.assertArrayEquals(pol1.plus(pol2).polynomCof, new int[]{5, 7, 9});
    }

    @Test
    void checkPlusEmpty() {
        Polinomial pol1 = new Polinomial(new int[0]);
        Polinomial pol2 = new Polinomial(new int[0]);
        Assertions.assertArrayEquals(pol1.plus(pol2).polynomCof, new int[0]);
    }

    @Test
    void checkPlusOneNotEmpty() {
        Polinomial pol1 = new Polinomial(new int[0]);
        Polinomial pol2 = new Polinomial(new int[]{1, 2});
        Assertions.assertArrayEquals(pol1.plus(pol2).polynomCof, new int[]{1, 2});
    }

    @Test
    void checkMinusAddMinus() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3, 4, 7});
        Assertions.assertArrayEquals(pol1.minus(pol2).polynomCof, new int[]{0, 0, 0, -4, -7});
    }

    @Test
    void checkMinusNorm() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3, 4, 7});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3});
        Assertions.assertArrayEquals(pol1.minus(pol2).polynomCof, new int[]{0, 0, 0, 4, 7});
    }

    @Test
    void checkMinusEmpty() {
        Polinomial pol1 = new Polinomial(new int[0]);
        Polinomial pol2 = new Polinomial(new int[0]);
        Assertions.assertArrayEquals(pol1.minus(pol2).polynomCof, new int[0]);
    }

    @Test
    void checkMinusFirstIsEmpty() {
        Polinomial pol1 = new Polinomial(new int[0]);
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3});
        Assertions.assertArrayEquals(pol1.minus(pol2).polynomCof, new int[]{-1, -2, -3});
    }

    @Test
    void checkMinusSecondIsEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{1, 2, 3});
        Polinomial pol2 = new Polinomial(new int[0]);
        Assertions.assertArrayEquals(pol1.minus(pol2).polynomCof, new int[]{1, 2, 3});
    }

    @Test
    void checkMultNorm() {
        Polinomial pol1 = new Polinomial(new int[]{5, 4, 3, 1});
        Polinomial pol2 = new Polinomial(new int[]{1, 3, 5});
        Assertions.assertArrayEquals(pol1.mult(pol2).polynomCof, new int[]{5, 19, 40, 30, 18, 5});
    }

    @Test
    void checkMultAboutMinus() {
        Polinomial pol1 = new Polinomial(new int[]{-5, 4, 3});
        Polinomial pol2 = new Polinomial(new int[]{1, 3});
        Assertions.assertArrayEquals(pol1.mult(pol2).polynomCof, new int[]{-5, -11, 15, 9});
    }

    @Test
    void checkMultAboutAllMinus() {
        Polinomial pol1 = new Polinomial(new int[]{-5, -4, -3});
        Polinomial pol2 = new Polinomial(new int[]{-1, -3});
        Assertions.assertArrayEquals(pol1.mult(pol2).polynomCof, new int[]{5, 19, 15, 9});
    }

    @Test
    void checkMultEmpty() {
        Polinomial pol1 = new Polinomial(new int[0]);
        Polinomial pol2 = new Polinomial(new int[0]);
        Assertions.assertArrayEquals(pol1.mult(pol2).polynomCof, new int[0]);
    }

    @Test
    void checkMultZero() {
        Polinomial pol1 = new Polinomial(new int[]{0});
        Polinomial pol2 = new Polinomial(new int[]{1, 2, 3});
        Assertions.assertArrayEquals(pol1.mult(pol2).polynomCof, new int[]{0, 0, 0});
    }

    @Test
    void checkSpot() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        Assertions.assertEquals(pol1.inSpot(1), 9);
    }

    @Test
    void checkSpotEmpty() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        Assertions.assertEquals(pol1.inSpot(), "ты дурак?");
    }

    @Test
    void checkDer() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        Assertions.assertArrayEquals(pol1.iDerivative(1).polynomCof, new int[]{3, 10});
    }

    @Test
    void checkDerForNthng() {
        Polinomial pol1 = new Polinomial(new int[]{1});
        Assertions.assertArrayEquals(pol1.iDerivative(1).polynomCof, new int[]{0});
    }

    @Test
    void checkDer2() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        Assertions.assertArrayEquals(pol1.iDerivative(2).polynomCof, new int[]{10});
    }

    @Test
    void checkDer3() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        Assertions.assertArrayEquals(pol1.iDerivative(3).polynomCof, new int[]{0});
    }

    @Test
    void checkDerNeg() {
        Polinomial pol1 = new Polinomial(new int[]{1, -2, 5});
        Assertions.assertArrayEquals(pol1.iDerivative(1).polynomCof, new int[]{-2, 10});
    }

    @Test
    void checkDerZero() {
        Polinomial pol1 = new Polinomial(new int[]{0, 0, 0});
        Assertions.assertArrayEquals(pol1.iDerivative(1).polynomCof, new int[]{0, 0});
    }

    @Test
    void checkDerEmpty() {
        Polinomial pol1 = new Polinomial(new int[0]);
        Assertions.assertArrayEquals(pol1.iDerivative(1).polynomCof, new int[0]);
    }

    @Test
    void checkStrSimple() {
        Polinomial pol1 = new Polinomial(new int[]{1});
        Assertions.assertEquals(pol1.polToStr(), "1");
        System.out.println(pol1.toString());
    }

    @Test
    void checkStrMedium() {
        Polinomial pol1 = new Polinomial(new int[]{1, 3, 5});
        Assertions.assertEquals(pol1.polToStr(), "5x^2 + 3x + 1");
        System.out.println(pol1.toString());
    }

    @Test
    void checkStrHard() {
        Polinomial pol1 = new Polinomial(new int[]{1, -3, 5, -10, 15});
        Assertions.assertEquals(pol1.polToStr(), "15x^4 - 10x^3 + 5x^2 - 3x + 1");
        System.out.println(pol1.toString());
    }

    @Test
    void checkStrMinusIsFirst() {
        Polinomial pol1 = new Polinomial(new int[]{1, -3, 5, -10, -15});
        Assertions.assertEquals(pol1.polToStr(), "-15x^4 - 10x^3 + 5x^2 - 3x + 1");
        System.out.println(pol1.toString());
    }

    @Test
    void checkStrZero() {
        Polinomial pol1 = new Polinomial(new int[]{0, 0, 0});
        Assertions.assertEquals(pol1.polToStr(), "0");
        System.out.println(pol1.toString());
    }

    @Test
    void checkStrEmpty() {
        Polinomial pol1 = new Polinomial(new int[0]);
        Assertions.assertEquals(pol1.polToStr(), "ты дурак?");
        System.out.println(pol1.toString());
    }

}
