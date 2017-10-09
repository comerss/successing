package com.comers.javaarithmetic;

import java.util.Arrays;

/**
 * Created by Comers on 2017/9/30.
 */

public class BubbleSort {
    public static void main(String[] args) {
        sort();
    }

    public static void sort() {
        int array[] = new int[]{1, 4, 46, 53, 2425, 42, 34, 5, 234, 46, 3, 456, 456, 4, 656, 24, 626, 26256, 56, 256, 256, 657, 534};
        int temp=0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
