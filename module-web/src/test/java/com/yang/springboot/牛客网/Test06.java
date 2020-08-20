package com.yang.springboot.牛客网;

import java.util.Scanner;

/**
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 *
 * @author Yang Hao
 * @date 2020/8/19
 */
public class Test06 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

            String str = sc.nextLine();
            while (str.length() > 8) {
                String s = str.substring(0, 8);
                System.out.println(s);
                str = str.substring(8);
            }

            int addZero = 8 - str.length();
            for (int i = 0; i < addZero; i++) {
                str = str + "0";
            }
            System.out.println(str);

        }


//
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(sc.nextLine());
//            int addZero = 8 - sb.length() % 8;
//            if (addZero > 0 && addZero < 8) {
//                while (addZero > 0) {
//                    sb.append('0');
//                    addZero--;
//                }
//            }
//            String str2 = sb.toString();
//            while (str2.length() > 0) {
//                String substring = str2.substring(0, 8);
//                str2 = str2.substring(8);
//                System.out.println(substring);
//            }
//        }


    }
}
