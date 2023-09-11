package org.example;

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
    void sort() {
        assertArrayEquals(new int[] {1,2,3,4}, HeapSort.sort(new int[] {3, 1, 2, 4}));
    }

    @Test
    void sort2() {
        assertArrayEquals(new int[] {-10, -2, 2, 5, 7, 100}, HeapSort.sort(new int[] {-10, 7, 100, 2, -2, 5}));
    }

    @AfterAll
    static void end(){
        System.out.println("hehehehihihihahaha");
    }
}