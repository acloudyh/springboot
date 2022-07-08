package com.yang.springboot.lamda.groupBy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Yang Hao
 * @date 2020-11-19 19:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDemo {
    private String name;
    private String age;
    private Integer score;
    private BigDecimal num;
}
