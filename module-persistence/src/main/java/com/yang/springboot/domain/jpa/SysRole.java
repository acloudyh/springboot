package com.yang.springboot.domain.jpa;

import lombok.Data;

import javax.persistence.*;

/**
 * @author yanghao
 * @date 2019-05-15 11:10
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) AUTO_INCREMENT NOT NULL COMMENT '主键'")
    private Long id;

    @Column(name = "code", columnDefinition = "varchar(255) COMMENT '角色code'")
    private String code;

    @Column(name = "name", columnDefinition = "varchar(32) COMMENT '角色名称'")
    private String name;
}
