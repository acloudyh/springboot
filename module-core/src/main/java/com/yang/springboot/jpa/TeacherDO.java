package com.yang.springboot.jpa;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author Yang Hao
 * @date 2020/9/1
 */
@Data
@Entity
@Table(name = "yh_teacher")
@EntityListeners(AuditingEntityListener.class)
public class TeacherDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) AUTO_INCREMENT NOT NULL COMMENT '主键'")
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(32) COMMENT '姓名'")
    private String name;

    @Column(name = "address", columnDefinition = "varchar(32) COMMENT '地址'")
    private String address;

    public TeacherDO(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
