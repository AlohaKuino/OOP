package ru.nsu.shushakov.threads;

import java.lang.Thread;
import java.util.List;

public class SmarterWay {

    private final List<Integer> mainArray;
    private volatile boolean nonPrimeFlag;

    public SmarterWay(List<Integer> mainArray){
        this.mainArray = mainArray;
    }

     class checkInThread implements Runnable {
        private final List<Integer> subListToCheck;

        public checkInThread(List<Integer> subListToCheck) {
            this.subListToCheck = subListToCheck;
        }

        @Override
        public void run() {
            for (Integer number : subListToCheck) {
                if(number == 2){
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

    public boolean threaderAndCutter(int numberOfThreads) throws IllegalArgumentException {
        String ANSI_GREEN = "\u001b[32m";
        String ANSI_RESET = "\u001b[0m";
        if(numberOfThreads > this.mainArray.size() || numberOfThreads <= 0){
            throw new IllegalArgumentException("\u001b[32mthink better, pal\u001b[0m");
        }

        Thread[] threads = new Thread[numberOfThreads];

        if(this.mainArray.size() % numberOfThreads == 0) {

            int step = this.mainArray.size() / numberOfThreads;

            for (int i = 0; i < numberOfThreads; i++) {
                threads[i] = new Thread(new checkInThread(this.mainArray.subList(i * step, (i + 1) * step)));
                threads[i].start();
            }
        }

        else{

            int step = (this.mainArray.size() - this.mainArray.size() % numberOfThreads) / (numberOfThreads - 1);

            for (int i = 0; i < numberOfThreads; i++) {
                if(i == numberOfThreads - 1){
                    threads[i] = new Thread(new checkInThread(this.mainArray.subList(i * step, this.mainArray.size())));
                    threads[i].start();
                    break;
                }
                threads[i] = new Thread(new checkInThread(this.mainArray.subList(i * step, (i + 1) * step)));
                threads[i].start();
            }
        }

        try {
            for (int threadIndex = 0; threadIndex < numberOfThreads; ++threadIndex) {
                threads[threadIndex].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("\u001b[32mdon't dare you\u001b[0m");
        }
        return nonPrimeFlag;
    }
}



