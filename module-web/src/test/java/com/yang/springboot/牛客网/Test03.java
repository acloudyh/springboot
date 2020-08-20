package com.yang.springboot.牛客网;

import java.util.Scanner;

/**
 * 计算字符串最后一个单词的长度，单词以空格隔开
 *
 * @author Yang Hao
 * @date 2020/8/19
 */
public class Test03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] arr = str.split("\\s+");
            int length = arr[arr.length - 1].length();
            System.out.println(length);
        }
    }
}
