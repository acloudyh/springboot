package com.yang.springboot.study;

import java.util.Objects;
import java.util.TreeSet;

/**
 * @author Yang Hao
 * @date 2020/7/29
 */
public class StringTest {
    public static void main(String[] args) {
        String aa = new String("yang");
        String bb = new String("yang");
        String cc = "yang";

        System.out.println(aa.equals(bb));
        System.out.println(aa.equals(cc));

        Student s1 = new Student("yang-student");
        Student s2 = new Student("yang-student");


        System.out.println(s1.equals(s2));


        TreeSet<Integer> treeSetInteger = new TreeSet<Integer>();
        treeSetInteger.add(23);
        treeSetInteger.add(1);
        treeSetInteger.add(4);
        System.out.println(treeSetInteger.toString());

    }


    public static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student student = (Student) o;
            return name.equals(student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
