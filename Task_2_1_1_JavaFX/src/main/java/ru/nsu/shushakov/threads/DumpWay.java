package ru.nsu.shushakov.threads;

import java.util.ArrayList;

public class DumpWay {
    static private ArrayList<Integer> mainArray;

    public DumpWay(ArrayList<Integer> arrayIn){
        mainArray = arrayIn;
    }
    public boolean dumpWayAlgorithm() {
        for (Integer number : mainArray) {
            if(number == 2){
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