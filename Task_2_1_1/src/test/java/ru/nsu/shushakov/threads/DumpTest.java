package ru.nsu.shushakov.threads;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class DumpTest {

    ArrayList<Integer> experimental = new ArrayList<>();
    FullOf99991 v = new FullOf99991(experimental);

    @Test
    void firstTest() throws IOException {
        long startTime = System.currentTimeMillis();

        DumpWay a = new DumpWay(experimental);
        System.out.println(a.dumpWayAlgorithm());

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime) + " milliseconds");

    }

    @Test
    void secondTest() throws IllegalArgumentException, IOException {
        long startTime = System.currentTimeMillis();

        SmarterWay a = new SmarterWay(experimental);
        boolean b = a.threaderAndCutter(100000);
        System.out.println(b);

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime) + " milliseconds");

    }

    @Test
    void thirdTest() throws IOException {

        long startTime = System.currentTimeMillis();


        TheSmartestIDKWay a = new TheSmartestIDKWay(experimental);
        boolean b = a.magicParallelStreams();
        System.out.println(b);

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime) + " milliseconds");

    }
//    @Test
//    void hehe(){
//        System.out.println(Math.round(8./3));
//        System.out.println(Math.ceil(8./3));
//        System.out.println(Math.floor(8./3));
//    }
}
