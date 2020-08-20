package com.yang.springboot.牛客网;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写
 *
 * @author Yang Hao
 * @date 2020/8/19
 */
public class Test04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toLowerCase();
        String str2 = sc.nextLine().toLowerCase();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == str2.charAt(0)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
