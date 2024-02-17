package ru.nsu.shushakov.threads;

import java.util.List;

public class TheSmartestIDKWay {

    private boolean nonPrimeFlag;
    private final List<Integer> mainArray;

    public TheSmartestIDKWay(List<Integer> mainArray){
        this.mainArray = mainArray;
    }

    private void nonPrimeChecker(int number){
        for (int j = 2; j < Math.sqrt(number) + 1; j++) {
            if (number % j == 0) {
                nonPrimeFlag = true;
                break;
            }
        }
    }

    public boolean magicParallelStreams(){
        mainArray.parallelStream().forEach(this::nonPrimeChecker);
        return nonPrimeFlag;
    }
}
