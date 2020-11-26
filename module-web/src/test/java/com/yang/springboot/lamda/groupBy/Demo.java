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

        List<Student> list = new ArrayList<>();
        list.add(new Student("张三", "20", 80));
        list.add(new Student("张三", "20", 80));
        list.add(new Student("王五", "20", 80));
        list.add(new Student("李四", "18", 80));
        List<Student> studentList = new ArrayList<>();
        list.parallelStream().collect(Collectors.groupingBy(o -> (o.getName() + o.getAge()), Collectors.toList())).forEach(
                (id, transfer) -> transfer.stream().reduce((a, b) -> new Student(a.getName(), a.getAge(), a.getScore() + b.getScore())).ifPresent(studentList::add)
        );
        System.out.println(studentList);
    }
}



   

    
