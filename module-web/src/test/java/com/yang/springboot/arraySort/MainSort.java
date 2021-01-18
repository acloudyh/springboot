package com.yang.springboot.arraySort;

import java.util.Arrays;

/**
 * @author Yang Hao
 * @date 2021-01-18 15:02
 */
public class MainSort {
    public static void main(String[] args) {
        int[] array = {23, 123, 8, 91, 2, 24, 88, 234, 22, 0, 4, 1321, 342, 13};
        System.out.println("排序前:          " + Arrays.toString(array));
        System.out.println("排序后, 插入排序: " + Arrays.toString(InsertSort.insertSort(array)));

    }
}
