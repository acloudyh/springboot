package com.yang.springboot.arraySort;

/**
 * @author Yang Hao
 * @date 2021-01-18 16:42
 */
public class SelectionSort {
    public static int[] selectionSort(int[] array) {

        if (0 == array.length) {
            System.out.println("空集合-----");
            return array;
        }
//             int[] array = {23, 123, 8, 91, 2, 24, 88};
        for (int i = 0; i < array.length - 1; i++) {
            //假设第一个元素是最小的
            int minIndex = i;
            for (int j = i; j < array.length; j++) {

                //二次循环, 如果当前的值小于minIndex 就替换
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            //将最小数和无序区的第一个数交换
            int tmp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = tmp;
        }
        return array;
    }
}
