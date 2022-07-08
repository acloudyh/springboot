package com.yang.springboot.lamda.groupBy;

import lombok.Data;

/**
 * @author Yang Hao
 * @date 2022-03-22 22:03
 */
@Data
public class ComputeGroupByDTO {
    /**
     * 总条数
     */

    private long totalCount;

    /**
     * 总的工作小时数
     */

    private Double dueHourSum;

    /**
     * 真实的小时数
     */

    private Double realHourSum;
}
