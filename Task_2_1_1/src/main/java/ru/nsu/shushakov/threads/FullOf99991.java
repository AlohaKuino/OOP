package ru.nsu.shushakov.threads;

import java.util.ArrayList;

/**
 * putting smth(its really putting some numbers in an arrayList).
 */
public class FullOf99991 {

    private ArrayList<Integer> whereToput;

    /**
     * simple constructor.
     *
     * @param input array.
     */
    public FullOf99991(ArrayList<Integer> input) {
        this.whereToput = input;
        this.putter();
    }

    /**
     * puts numbers to array.
     */
    public void putter() {
        for (long i = 0; i < 10000000; i++) {
            this.whereToput.add(99991);
        }
    }
}
