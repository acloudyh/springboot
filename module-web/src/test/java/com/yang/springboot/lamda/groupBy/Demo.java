package com.yang.springboot.lamda.groupBy;


import java.math.BigDecimal;
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
        list.add(new Student("张三", "16", 80));
        list.add(new Student("张三", "16", 80));
        list.add(new Student("张三", "16", 80));
        list.add(new Student("李四", "18", 80));
        List<Student> studentList = new ArrayList<>();
        list.parallelStream().collect(Collectors.groupingBy(o -> (o.getName() + o.getAge()), Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a, b) -> new Student(a.getName(), a.getAge(), a.getScore() + b.getScore())).ifPresent(studentList::add);
                }
        );
        System.out.println(studentList);




        List<Invoice> invoices = new ArrayList<>();
        List<Invoice> invoicetList = new ArrayList<>();
        invoices.add(new Invoice(117L, 1881L, "8887777", BigDecimal.valueOf(50L), 214L, "事件", "US", 1L));
        invoices.add(new Invoice(118L, 1881L, "8887777", BigDecimal.valueOf(30L), 214L, "事件", "US", 71L));
        invoices.add(new Invoice(119L, 1881L, "8887777", BigDecimal.valueOf(50L), 214L, "事件", "CN", 1L));
        invoices.add(new Invoice(120L, 1881L, "8887777", BigDecimal.valueOf(20L), 214L, "事件", "CN", 1L));
        invoices.add(new Invoice(121L, 1881L, "8887777", BigDecimal.valueOf(10L), 214L, "事件", "CN", 1L));
        invoices.add(new Invoice(122L, 1881L, "8887777", BigDecimal.valueOf(40L), 214L, "事件", "US", 71L));
        System.out.println(invoices);
        invoices.parallelStream().collect(Collectors.groupingBy(o -> (o.getServicePackageId() + o.getGlobalSimId() + o.getCountryCode() +o.getServiceCategoryId()), Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream()
                            .reduce((a, b) ->{
                                return new Invoice(a.getId(), a.getGlobalSimId(), a.getGIccid(), a.getActualUsage().add(b.getActualUsage()), a.getServicePackageId(), a.getServicePackageName(), a.getCountryCode(), a.getServiceCategoryId());
                            })
                            .ifPresent(invoicetList::add);
                }
        );

        System.out.println("\n" + "---" + invoicetList);
    }
}



   

    
