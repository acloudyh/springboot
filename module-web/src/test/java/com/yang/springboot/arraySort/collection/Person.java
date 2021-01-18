package com.yang.springboot.arraySort.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Yang Hao
 * @date 2021-01-18 16:58
 */
@Data
@AllArgsConstructor
public class Person implements Comparable<Person> {

    private int age;

    private String name;

    @Override
    public int compareTo(Person o) {
        if (this.age > o.age) {
            return 1;
        } else if (this.age < o.age) {
            return -1;
        } else {
            return 0;
        }
    }
}
