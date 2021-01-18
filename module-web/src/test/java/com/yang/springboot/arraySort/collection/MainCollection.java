package com.yang.springboot.arraySort.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Yang Hao
 * @date 2021-01-18 17:00
 */
public class MainCollection {
    public static void main(String[] args) {
        List<Person> pList = new ArrayList<>();
        Person p1 = new Person(10, "a 张三");
        Person p2 = new Person(18, "c 李四");
        Person p3 = new Person(20, "b 王五");
        Person p4 = new Person(15, "d 陈六");
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);
        pList.add(p4);

        printInfo(pList, "排序前依次输出：");

        // 按照年龄升序排序（默认）
        Collections.sort(pList);
        printInfo(pList, "按年龄升序排序后依次输出：");

        // 按照年龄倒叙排序
        Collections.reverse(pList);
        printInfo(pList, "按年龄倒叙排序后依次输出：");

        Collections.sort(pList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        printInfo(pList, "按照姓名升序排序后依次输出：");



    }





    private static void printInfo(List<Person> pList, String s) {
        System.out.println(s);
        for (Person p : pList) {
            System.out.println(p.getAge() + " -- " + p.getName());
        }
        System.out.println("-----------------------------------------------------------\n");
    }
}
