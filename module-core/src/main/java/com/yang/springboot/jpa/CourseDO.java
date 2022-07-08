package com.yang.springboot.jpa;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Yang Hao
 * @date 2021-03-12 20:10
 */
@Data
@Entity
@Table(name = "yh_course")
public class CourseDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) AUTO_INCREMENT NOT NULL COMMENT '人员ID'")
    private Long id;

    @Column(name = "course_name", columnDefinition = "varchar(32) COMMENT '课程名'")
    private String courseName;

}
