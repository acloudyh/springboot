package com.yang.springboot.study;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yang Hao
 * @date 2020/7/23
 */
public class Test1 {
    public static void main(String[] args) {

        List list3 = new ArrayList();
        LinkedList<Object> objects = new LinkedList<>();
        Vector vector = new Vector();
        List synchronizedList = Collections.synchronizedList(new LinkedList());
        Map hashMap = new HashMap();

        HashSet hashSet = new HashSet<Object>();
        TreeSet treeSet = new TreeSet<Object>();
        Map hashtable = new Hashtable();
        Map concurrentHashMap = new ConcurrentHashMap();
        StringBuilder sb = new StringBuilder();
        StringBuffer sbf = new StringBuffer();

//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            int a = sc.nextInt();
//            String str = Integer.toBinaryString(a);
//            System.out.println(str);
//            int count = 0;
//            for (int i = 0; i < str.length(); i++) {
//                if (str.charAt(i)=='1') {
//                    count++;
//                }
//            }
//            System.out.println(count);
//        }


        int num = 2147483647;
        num += 2;
        System.err.println(num);


//
//        int[][] arr = {{1, 2, 3}, {5, 6, 7}};
//        System.out.println(arr);
//        for (int i = 0; i < arr.length; i++) {
//
//            for (int j = 0; j < arr[i].length; j++) {
//
//                System.out.print(arr[i][j] + " ");
//
//            }
//            //输出一列后就回车空格
//            System.out.println();
//
//        }

    }


    public static int f(int value) {
        try {
            return value * value;
        } finally {
            if (value == 2) {
                return 0;
            }
        }

    }


    class Student {
        private String name;
        private String description;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student student = (Student) o;
            return name.equals(student.name) &&
                    description.equals(student.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, description);
        }
    }


}
