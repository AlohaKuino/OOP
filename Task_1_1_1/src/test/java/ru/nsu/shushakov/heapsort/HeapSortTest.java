package ru.nsu.shushakov.heapsort;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
class HeapSortTest {
    @Test
    void easySort() {
        assertArrayEquals(new int[] {1, 2, 3, 4}, HeapSort.sort(new int[] {3, 1, 2, 4}));
    }

    @Test
    void hardSort() {
        assertArrayEquals(new int[] {-10, -2, 2, 5, 7, 100}, HeapSort.sort(new int[] {-10, 7, 100, 2, -2, 5}));
    }
    @Test
    void emptySort() {
        assertArrayEquals(new int[] {}, HeapSort.sort(new int[] {}));
    }
    @Test
    void zeroSort() {
        assertArrayEquals(new int[] {0, 0, 0}, HeapSort.sort(new int[] {0, 0, 0}));
    }
    @Test
    void compareSort() {
        int[] arr = {23, 45, 6, -1, 3, 4, -10, 6, 14, 8, 1, 67, -190, 34, 23, 1, 0, -100};
        Arrays.sort(arr);
        int[] copyArr = {23, 45, 6, -1, 3, 4, -10, 6, 14, 8, 1, 67, -190, 34, 23, 1, 0, -100};
        assertArrayEquals (arr, HeapSort.sort(copyArr));
    }
    @Test
    void largeSort(){
        int length = 1000; // длина последовательности
        int min = -1000; // минимальное значение числа
        int max = 1000; // максимальное значение числа

        Random random = new Random();
        int[] arr = new int[length];
        int[] copyArr = new int[length];
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(max - min + 1) + min;
            arr[i] = number;
            copyArr[i] = number;
        }
        Arrays.sort(arr);
        assertArrayEquals (arr, HeapSort.sort(copyArr));
    }
}