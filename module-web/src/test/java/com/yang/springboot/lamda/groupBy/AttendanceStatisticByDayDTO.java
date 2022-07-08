package com.yang.springboot.lamda.groupBy;

import lombok.Data;

import java.util.Date;

/**
 * @author Yang Hao
 * @date 2022-03-22 22:04
 */
@Data
public class AttendanceStatisticByDayDTO {
    /**
     * 员工ID
     */

    private String userId;

    /**
     * 员工编码
     */

    private String userCode;

    /**
     * 员工名称
     */

    private String userName;

    /**
     * 统计日期
     */

    private Date statisticDate;

    /**
     * 应上时长
     */

    private Float workDueHour;

    /**
     * 实上时长
     */

    private Float workRealHour;

    /**
     * 统计次数
     */

    private Integer statisticTimes;

    /**
     * 考勤配置ID
     */

    private String attendanceTypeId;

    /**
     * 考勤配置名称
     */

    private String attendanceTypeName;
}
