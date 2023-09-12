package ru.nsu.shushakov.heapsort;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {
    @AfterEach
    void tearDown() {
        System.out.println("nice");
    }

    @Test
    void easySort() {
        assertArrayEquals(new int[] {1, 2, 3, 4}, HeapSort.sort(new int[] {3, 1, 2, 4}));
    }

    @Test
    void hardSort() {
        assertArrayEquals(new int[] {-10, -2, 2, 5, 7, 100}, HeapSort.sort(new int[] {-10, 7, 100, 2, -2, 5}));
    }
    void emptySort() {
        assertArrayEquals(new int[] {}, HeapSort.sort(new int[] {}));
    }
    void zeroSort() {
        assertArrayEquals(new int[] {0, 0, 0}, HeapSort.sort(new int[] {0, 0, 0}));
    }
    @AfterAll
    static void end(){
        System.out.println("hehehehihihihahaha");
    }
}