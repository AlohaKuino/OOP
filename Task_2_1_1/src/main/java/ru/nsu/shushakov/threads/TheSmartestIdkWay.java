package ru.nsu.shushakov.threads;

import java.util.List;

/**
 * the smartest way.
 */
public class TheSmartestIdkWay {

    private final List<Integer> mainArray;

    /**
     * simple way.
     *
     * @param mainArray where to find.
     */
    public TheSmartestIdkWay(List<Integer> mainArray) {
        this.mainArray = mainArray;
    }

    /**
     * prime checker for a number.
     *
     * @param number which to check.
     */
    private boolean nonPrimeChecker(int number) {
        for (int j = 2; j < Math.sqrt(number) + 1; j++) {
            if (number % j == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * cool thing using parallelStream.
     *
     * @return true if non-prime.
     */
    public boolean magicParallelStreams() {
       return mainArray.parallelStream().anyMatch(this::nonPrimeChecker);
    }
}
