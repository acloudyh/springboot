package com.yang.springboot.arraySort;

/**
 * @author Yang Hao
 * @date 2021-01-18 14:39
 */
public class InsertSort {

    public static int[] insertSort(int[] array) {

        if (0 == array.length) {
            System.out.println("空集合-----");
            return array;
        }

        //从第二个元素开始遍历
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i;
            while (j > 0 && tmp < array[j - 1]) {
                //将当前元素移动到合适的位置
                array[j] = array[j - 1];
                j--;
            }
            array[j] = tmp;

        }
        return array;
    }
}
