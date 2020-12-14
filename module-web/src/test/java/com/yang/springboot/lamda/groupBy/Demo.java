package com.yang.springboot.lamda.groupBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yang Hao
 * @date 2020-11-19 19:41
 */
public class Demo {

    public static void main(String[] args) {

        /**
         * lambda表达式: 根据姓名和年龄分组之后再求和总分
         *
         * [                                                           [
         *      StudentDemo(name=张三, age=20, score=12),                 StudentDemo(name=张三, age=20, score=92),
         *      StudentDemo(name=张三, age=20, score=80),    =========>   StudentDemo(name=王五, age=20, score=33),
         *      StudentDemo(name=王五, age=20, score=33),    =========>   StudentDemo(name=李四, age=29, score=90),
         *      StudentDemo(name=李四, age=18, score=50),                 StudentDemo(name=李四, age=18, score=50)
         *      StudentDemo(name=李四, age=29, score=90)                ]
         * ]
         */

        List<StudentDemo> list = new ArrayList<>();
        list.add(new StudentDemo("张三", "20", 12));
        list.add(new StudentDemo("张三", "20", 80));
        list.add(new StudentDemo("王五", "20", 33));
        list.add(new StudentDemo("李四", "18", 50));
        list.add(new StudentDemo("李四", "29", 90));
        System.out.println("分组求和之前 " + list);

        List<StudentDemo> studentDemoList = new ArrayList<>();
        list.parallelStream()
                .collect(Collectors.groupingBy(o -> (o.getName() + o.getAge()), Collectors.toList()))
                .forEach((id, transfer) -> transfer.stream().reduce((a, b) -> new StudentDemo(a.getName(), a.getAge(), a.getScore() + b.getScore())).ifPresent(studentDemoList::add));
        System.out.println("分组求和之后 " + studentDemoList);
    }
}



   

    
