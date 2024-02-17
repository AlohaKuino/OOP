package ru.nsu.shushakov.threads;

import java.util.ArrayList;

public class FullOf99991 {

    private ArrayList<Integer> whereToput;

    public FullOf99991(ArrayList<Integer> input){
        this.whereToput = input;
        this.putter();
    }

    public void putter(){
        for(long i = 0; i < 10000000; i ++) {
            this.whereToput.add(99991);
        }
    }
}
