package com.yang.springboot.lamda.groupBy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        list.add(new StudentDemo("张三", "20", 12, new BigDecimal(1)));
        list.add(new StudentDemo("张三", "20", 80, new BigDecimal(2)));
        list.add(new StudentDemo("王五", "20", 33, new BigDecimal(3)));
        list.add(new StudentDemo("李四", "18", 50, new BigDecimal(4)));
        list.add(new StudentDemo("李四", "29", 90, new BigDecimal(5)));
        System.out.println("分组求和之前 " + list);

        List<StudentDemo> studentDemoList = new ArrayList<>();
//        list.parallelStream()
//                .collect(Collectors.groupingBy(o -> (o.getName() + o.getAge()), Collectors.toList()))
//                .forEach((id, transfer) -> transfer.stream().reduce((a, b) -> new StudentDemo(a.getName(), a.getAge(), a.getScore() + b.getScore())).ifPresent(studentDemoList::add));
//        System.out.println("分组求和之后 " + studentDemoList);
//        list.parallelStream()
//                .collect(Collectors.groupingBy(StudentDemo::getName, Collectors.toList()))
//                .forEach((id, transfer) -> transfer.stream().reduce((a, b) -> new StudentDemo(a.getName(), a.getAge(), a.getScore() + b.getScore())).ifPresent(studentDemoList::add));
//        System.out.println("分组求和之后 " + studentDemoList);
//
//        Map<String, List<StudentDemo>> collect = list.stream().collect(Collectors.groupingBy(StudentDemo::getName));
//        System.out.println(collect);
//        System.out.println(collect.get("李四"));

        Map<String, List<StudentDemo>> collect = list.stream()
                .collect(Collectors.groupingBy(StudentDemo::getName, Collectors.toList()));
        collect.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });

    }

//        list.stream()
//                .collect(Collectors.groupingBy(StudentDemo::getName,
//                        Collectors.collectingAndThen(Collectors.toList(), x -> {
//                            BigDecimal personIn = x.stream().map(StudentDemo::getNum).reduce(BigDecimal.ZERO, BigDecimal::add);
//                            System.out.println("2222--:{}" + personIn);
//                            return null;
//                        })));
//    }


//    private static AttendanceStatisticByDayDTO fetchGroupKey(AttendanceStatisticByDayDTO attendanceStatisticByDayDTO) {
//
//        final AttendanceStatisticByDayDTO statisticByDay = new AttendanceStatisticByDayDTO();
//
//        statisticByDay.setUserId(attendanceStatisticByDayDTO.getUserId());
//
//        statisticByDay.setUserCode(attendanceStatisticByDayDTO.getUserCode());
//
//        statisticByDay.setUserName(attendanceStatisticByDayDTO.getUserName());
//
//        statisticByDay.setStatisticDate(attendanceStatisticByDayDTO.getStatisticDate());
//
//        statisticByDay.setAttendanceTypeId(attendanceStatisticByDayDTO.getAttendanceTypeId());
//
//        statisticByDay.setAttendanceTypeName(attendanceStatisticByDayDTO.getAttendanceTypeName());
//
//        return statisticByDay;
//
//    }
//
//    public static void main(final String[] args) {
//
//        final List<AttendanceStatisticByDayDTO> attendanceStatisticByDayDTOS = new ArrayList<>();
//
//        for (int i = 0; i < 100; i++) {
//            final AttendanceStatisticByDayDTO attendanceStatisticByDayDTO = new AttendanceStatisticByDayDTO();
//
//            attendanceStatisticByDayDTO.setUserId("" + (i % 5));
//
//            attendanceStatisticByDayDTO.setUserCode("" + (i % 5));
//
//            attendanceStatisticByDayDTO.setUserName("" + (i % 5));
//
//            attendanceStatisticByDayDTO.setWorkDueHour((float) (i % 5));
//
//            attendanceStatisticByDayDTO.setWorkRealHour((float) (i % 5));
//
//            attendanceStatisticByDayDTO.setStatisticTimes(i);
//
//            attendanceStatisticByDayDTO.setAttendanceTypeId("" + (i % 5));
//
//            attendanceStatisticByDayDTO.setAttendanceTypeName("" + (i % 5));
//
//            attendanceStatisticByDayDTOS.add(attendanceStatisticByDayDTO);
//
//        }
//
//        final Map groupByMap = attendanceStatisticByDayDTOS.stream()
//
//                .collect(Collectors.groupingBy(n -> fetchGroupKey(n), Collectors.collectingAndThen(Collectors.toList(), m -> {
//
//                    final long totalCount = m.stream().count();
//
//                    final Double dueHourSum = m.stream().mapToDouble(AttendanceStatisticByDayDTO::getWorkDueHour).sum();
//
//                    final Double realHourSum = m.stream().mapToDouble(AttendanceStatisticByDayDTO::getWorkDueHour).sum();
//
//                    final ComputeGroupByDTO computeGroupBy = new ComputeGroupByDTO();
//
//                    computeGroupBy.setTotalCount(totalCount);
//
//                    computeGroupBy.setDueHourSum(dueHourSum);
//
//                    computeGroupBy.setRealHourSum(realHourSum);
//
//                    return computeGroupBy;
//
//                })));
//
//        System.out.println("groupByMap=" + groupByMap.size());
//
//        groupByMap.forEach((k, v) -> {
//
//            System.out.println(JSON.toJSONString(k));
//
//            System.out.println(JSON.toJSONString(v));
//
//        });
//
}

