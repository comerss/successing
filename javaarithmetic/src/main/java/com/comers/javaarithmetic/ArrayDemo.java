package com.comers.javaarithmetic;

/**
 * Created by Comers on 2017/9/29.
 */

public class ArrayDemo {
    public static void main(String args[]) {
        System.out.println("我是一个java main函数");
        System.out.println("找到了--》"+ find(356));
    }

    //二分法  --->前提  ： 数组是有序的
    public static int find(int value) {
        int[] ints = new int[]{1, 2, 4, 5, 7, 8, 9, 34, 66, 123, 156, 356, 456, 563, 578, 588, 656, 2442};
        int start = 0;
        int end = ints.length - 1;
        while (end >= start) {
            int index = (start + end) / 2;
            if (ints[index] == value) {
                return index;
            } else if (value > ints[index]) {
                start = index + 1;
            } else {
                end = index - 1;
            }
        }
        return 0;
    }
}
