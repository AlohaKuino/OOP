package ru.nsu.shushakov.heapsort;
public class HeapSort {
    /**
    * <p>@param arr array of ints
    * </p>
    *
    * @return - arr.
    *
    * <p>doing a heap.
    * taking elements by one.
    * moving current root in the end.
    * calling heapify on smaller heap.
    * </p>
    */
    public static int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    /**
    *
    * <p>@param arr array of ints
    * </p>
    *
    * <p>@param n count
    * </p>
    *
    * <p>@param i root
    * </p>
    *
    * <p>doing binary tree from subtree with root i.
    * the largest is a root.
    * </p>
    */

    static void heapify(int[] arr, int n, int i) {
        int root = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[root]) {
            root = left;
        }
        if (right < n && arr[right] > arr[root]) {
            root = right;
        }
        if (root != i) {
            int swap = arr[i];
            arr[i] = arr[root];
            arr[root] = swap;
            heapify(arr, n, root);
        }
    }

    /**
     * 
    * <p>@param args some magic
    * </p>
    */
    public static void main(String[] args) {
    }
}