package com.yang.springboot.arraySort;

import java.util.Arrays;

/**
 * @author Yang Hao
 * @date 2021-01-18 15:02
 */
public class MainSort {
    public static void main(String[] args) {
        int[] array = {23, 123, 8, 91, 2, 24, 88};

//        System.out.println("排序前:          " + Arrays.toString(array));
//        System.out.println("排序后, 插入排序: " + Arrays.toString(InsertionSort.insertionSort(array)));
//        System.out.println("--------------------------------------------------------------------------------\n");

//        System.out.println("排序前:          " + Arrays.toString(array));
//        System.out.println("排序后, 选择排序: " + Arrays.toString(SelectionSort.selectionSort(array)));
//        System.out.println("--------------------------------------------------------------------------------\n");

//        System.out.println("排序前:          " + Arrays.toString(array));
//        System.out.println("排序后, 冒泡排序: " + Arrays.toString(BubbleSort.bubbleSort(array)));
//        System.out.println("--------------------------------------------------------------------------------\n");

        System.out.println("排序前:          " + Arrays.toString(array));
        System.out.println("排序后, 快速排序: " + Arrays.toString(QuickSort2.quickSort(array, 0, array.length-1)));
        System.out.println("--------------------------------------------------------------------------------\n");


    }
}
