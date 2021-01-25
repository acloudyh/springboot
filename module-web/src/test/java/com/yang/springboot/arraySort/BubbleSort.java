package com.yang.springboot.arraySort;

/**
 * @author Yang Hao
 * @date 2021-01-18 18:39
 */
public class BubbleSort {

    public static int[] bubbleSort(int[] array) {

        if (0 == array.length) {
            System.out.println("空集合-----");
            return array;
        }

        for (int i = 0; i < array.length - 1; i++) {
            //先默认数组是有序的，只要发生一次交换，就必须进行下一轮比较
            //如果在内层循环中，都没有执行一次交换操作，说明此时数组已经是升序数组
            boolean sorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }

        }
        return array;
    }
}
