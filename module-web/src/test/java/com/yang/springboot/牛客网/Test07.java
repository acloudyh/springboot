package com.yang.springboot.牛客网;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
 *
 * @author Yang Hao
 * @date 2020/8/20
 */
public class Test07 {


    public static void main(String[] args) {
//        //进制转换
//        conversion();
        Scanner sc = new Scanner(System.in);
        System.out.println(Integer.toHexString(10));
        System.out.println(Integer.parseInt("10", 10));
        while (sc.hasNext()) {
            String str = sc.nextLine().substring(2);
            int s = Integer.parseInt(str, 16);
            System.out.println(s);
        }
    }


    /**
     * int n=100
     * //十进制转成十六进制：
     * Integer.toHexString(n);
     * //十进制转成八进制
     * Integer.toOctalString(n);
     * //十进制转成二进制
     * Integer.toBinaryString(n);
     *
     * //十六进制转成十进制
     * Integer.valueOf("FFFF",16).toString();
     * //十六进制转成二进制
     * Integer.toBinaryString(Integer.valueOf("FFFF",16));
     * //十六进制转成八进制
     * Integer.toOctalString(Integer.valueOf("FFFF",16));
     *
     * //八进制转成十进制
     * Integer.valueOf("144",8).toString();
     * //八进制转成二进制
     * Integer.toBinaryString(Integer.valueOf("144",8));
     * //八进制转成十六进制
     * Integer.toHexString(Integer.valueOf("144",8));
     *
     * //二进制转十进制
     * Integer.valueOf("1100100",2).toString();
     * //二进制转八进制
     * Integer.toOctalString(Integer.parseInt("1100100", 2));
     * //二进制转十六进制
     * Integer.toHexString(Integer.parseInt("1100100", 2));
     */
    public static void conversion() {
        //十进制   十六进制    八进制     二进制
        //100       64        144     1100100

        //十进制转换十六进制 八进制 二进制
        String conversion_10_to_16 = Integer.toHexString(100);
        String conversion_10_to_8 = Integer.toOctalString(100);
        String conversion_10_to_2 = Integer.toBinaryString(100);
        System.out.println("十进制[100]转换十六进制为:[" + conversion_10_to_16 + "]");
        System.out.println("十进制[100]转换八进制为:[" + conversion_10_to_8 + "]");
        System.out.println("十进制[100]转换二进制为:[" + conversion_10_to_2 + "]"+"\n");

        //二进制转换十进制 八进制 十六进制
        int conversion_2_to_10 = Integer.parseInt("1100100", 2);
//        conversion_2_to_10 = Integer.valueOf("1100100",2); //这个也行
        //后续转换都可以基于10进制转换
        System.out.println("二进制[1100100]转换十进制为:[" + conversion_2_to_10 + "]"+"\n");

        //八进制 转换十进制 十六进制 二进制
        int conversion_8_to_10 = Integer.valueOf("144",8);
//        conversion_8_to_10 = Integer.parseInt("144",8);//这也行
        System.out.println("八进制[144]转换十进制为:[" + conversion_8_to_10 + "]"+"\n");
        //后续也是基于10进制转换

        //十六进制转换十进制 二进制 八进制
        int conversion_16_to_10 = Integer.parseInt("64",16);
//        conversion_16_to_10 = Integer.valueOf("64",16);
        System.out.println("十六进制[64]转换十进制为:[" + conversion_16_to_10 + "]"+"\n");
    }
}
