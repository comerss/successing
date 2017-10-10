package com.comers.javaarithmetic;

import java.util.Arrays;

/**
 * Created by Comers on 2017/9/30.
 */

public class SelectSort {
    public static void main(String[] args) {
        sort();
    }

    public static void sort() {
        int array[] = new int[]{1, 4, 46, 53, 2425, 42, 34, 5, 234, 46, 3, 456, 456, 4, 656, 24, 626, 26256, 56, 256, 256, 657, 534};
        int min = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    min = j;
                }
            }
            swip(array, min, i);
        }
        System.out.println(Arrays.toString(array));
    }

    private static void swip(int[] array, int min, int i) {
        int temp = array[i];
        array[i] = array[min];
        array[min] = temp;
    }
}
