package com.yang.springboot.牛客网;

import java.util.Scanner;

/**
 * 求int型正整数在内存中存储时1的个数
 * <p>
 * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
 *
 * @author Yang Hao
 * @date 2020/8/19
 */
public class Test02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt();
            String str = Integer.toBinaryString(a);
            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '1') {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

}
