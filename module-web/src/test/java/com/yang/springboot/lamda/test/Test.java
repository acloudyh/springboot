package com.yang.springboot.lamda.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yang Hao
 * @date 2020-11-20 11:53
 */
public class Test {

    public static void main(String[] args) {
        String name ="string-field";
        Test test = new Test();
        List<String> names = new ArrayList<String>();
        names.add("A");
        names.add("D");
        names.add("C");
        names.add("B");
        System.out.println(names+"\n");
        test.sortJava8(names);
        System.out.println(names);
    }


    private void sortJava8(List<String> names){
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }
}
