package com.yang.springboot.牛客网;

import java.util.Scanner;

/**
 * 取近似值
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 *
 * @author Yang Hao
 * @date 2020/8/19
 */
public class Test01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            double d = sc.nextDouble();
            System.out.print(Math.round(d)+"\n");
        }

    }

}
