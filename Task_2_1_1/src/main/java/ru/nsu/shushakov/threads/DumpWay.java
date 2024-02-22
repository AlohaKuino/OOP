package ru.nsu.shushakov.threads;

import java.util.ArrayList;

/**
 * the most simple way to find a non-prime number.
 */
public class DumpWay {
    private ArrayList<Integer> mainArray;

    /**
     * simple constructor.
     *
     * @param arrayIn array where we need to find a non-prime number.
     */
    public DumpWay(ArrayList<Integer> arrayIn) {
        mainArray = arrayIn;
    }

    /**
     * uncool algorithm.
     *
     * @return true if there is a non-prime number.
     */
    public boolean dumpWayAlgorithm() {

        for (Integer number : mainArray) {
            if (number == 2) {
                continue;
            }
            for (int j = 2; j < Math.sqrt(number) + 1; j++) {
                if (number % j == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
