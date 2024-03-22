package ru.nsu.shushakov.threads;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * tests.
 */
public class DumpTest {

    ArrayList<Integer> experimental = new ArrayList<>();
    FullOf99991 put = new FullOf99991(experimental);

    @Test
    void firstTest() {
        long startTime = System.currentTimeMillis();

        DumpWay a = new DumpWay(experimental);
        System.out.println(a.dumpWayAlgorithm());

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime)
                + " milliseconds");
    }

    @Test
    void secondTest() throws IllegalArgumentException {
        long startTime = System.currentTimeMillis();
// 3681
        SmarterWay a = new SmarterWay(experimental);
        boolean b = a.threaderAndCutter(20);
        System.out.println(b);

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime)
                + " milliseconds");

    }

    @Test
    void thirdTest() {

        long startTime = System.currentTimeMillis();

        TheSmartestIdkWay a = new TheSmartestIdkWay(experimental);
        boolean b = a.magicParallelStreams();
        System.out.println(b);

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime)
                + " milliseconds");
    }
}
