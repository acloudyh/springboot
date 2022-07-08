package com.yang.springboot.jpa;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Yang Hao
 * @date 2021-03-12 20:12
 */
@Data
@Entity
@Table(name = "yh_person_info")
public class PersonInfoDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) AUTO_INCREMENT NOT NULL COMMENT '主键'")
    private Long id;

    @Column(name = "month", columnDefinition = "bigint COMMENT '月份'")
    private Long month;

    @Column(name = "course_id", columnDefinition = "bigint COMMENT '课程ID'")
    private Long courseId;

    @Column(columnDefinition = "char(1) COMMENT '是否通过'")
    private Short passed;

}
