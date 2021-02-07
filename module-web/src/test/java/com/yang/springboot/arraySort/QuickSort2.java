package com.yang.springboot.arraySort;

/**
 * @author Yang Hao
 * @date 2021-01-25 17:32
 */
public class QuickSort2 {

    /**
     * 快排 参数说明
     *
     * @param array
     * @param l     左侧元素
     * @param r     右侧元素
     * @return
     */
    public static int[] quickSort(int[] array, int l, int r) {

        if (l < r) {
            int left = l;
            int right = r;
            //随机选择一个作为pivot(我选了第一个)
            int pivot = array[left];
            while (left < right) {
                //从右向左找到比pivot 小的元素
                while (left < right && array[right] >= pivot) {
                    right--;
                }
                array[left] = array[right];
                //从左往右找到比pivot 大的元素
                while (left < right && array[left] <= pivot)
                    left++;
                array[right] = array[left];
            }

        }
        return array;

    }


}
