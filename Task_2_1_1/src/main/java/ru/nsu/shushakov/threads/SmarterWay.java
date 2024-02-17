package ru.nsu.shushakov.threads;

import java.lang.Thread;
import java.util.List;

/**
 * same algorithm but using threads.
 */
public class SmarterWay {

    private final List<Integer> mainArray;
    private volatile boolean nonPrimeFlag;

    /**
     * simple constructor.
     *
     * @param mainArray array where we need to find a non-prime number.
     */
    public SmarterWay(List<Integer> mainArray) {
        this.mainArray = mainArray;
    }

    /**
     * gives us a step for every thread.
     *
     * @param numberOfThreads number of threads.
     * @return step for thread.
     * @throws IllegalArgumentException wrong number of threads.
     */
    private int stepper(int numberOfThreads) throws IllegalArgumentException {
        if (numberOfThreads > this.mainArray.size() || numberOfThreads <= 0) {
            throw new IllegalArgumentException("think better, pal");
        }
        if (this.mainArray.size() % numberOfThreads == 0) {
            return this.mainArray.size() / numberOfThreads;
        } else {
            return (int) (Math.ceil((double) this.mainArray.size() / numberOfThreads));
        }
    }

    /**
     * starts all threads.
     *
     * @param arrayOfThreads array of threads.
     */
    private void leeeetsGoo(Thread[] arrayOfThreads) {
        for (Thread arrayOfThread : arrayOfThreads) {
            arrayOfThread.start();
        }
    }

    /**
     * cuts array in some pieces (numberOfThreads) and gives every thread some numbers.
     *
     * @param numberOfThreads number threads you want to make.
     * @return non-prime flag.
     */
    public boolean threaderAndCutter(int numberOfThreads) {

        Thread[] threads = new Thread[numberOfThreads];

        int step = stepper(numberOfThreads);
        if (numberOfThreads == 1) {
            threads[numberOfThreads - 1] = new Thread(new checkInThread(this.mainArray
                    .subList(0, this.mainArray.size())));
        } else {
            for (int i = 0; i < numberOfThreads - 1; i++) {
                threads[i] = new Thread(new checkInThread(this.mainArray
                        .subList(i * step, (i + 1) * step)));
            }
            threads[numberOfThreads - 1] = new Thread(new checkInThread(this.mainArray
                    .subList((numberOfThreads - 2) * step, this.mainArray.size())));
        }
        leeeetsGoo(threads);

        try {
            for (int threadIndex = 0; threadIndex < numberOfThreads; ++threadIndex) {
                threads[threadIndex].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("don't dare you");
        }
        return nonPrimeFlag;
    }

    /**
     * something for every thread.
     */
    class checkInThread implements Runnable {
        private final List<Integer> subListToCheck;

        /**
         * simple constructor.
         *
         * @param subListToCheck what to check.
         */
        public checkInThread(List<Integer> subListToCheck) {
            this.subListToCheck = subListToCheck;
        }

        /**
         * what every thread do.
         */
        @Override
        public void run() {
            for (Integer number : subListToCheck) {
                if (number == 2) {
                    continue;
                }
                for (int j = 2; j < Math.sqrt(number) + 1; j++) {
                    if (number % j == 0) {
                        nonPrimeFlag = true;
                        break;
                    }
                }
            }
        }
    }
}



