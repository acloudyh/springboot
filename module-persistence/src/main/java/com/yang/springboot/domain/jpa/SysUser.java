package com.yang.springboot.domain.jpa;

import lombok.Data;

import javax.persistence.*;

/**
 * @author yanghao
 * @date 2019-05-15 11:08
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) AUTO_INCREMENT NOT NULL COMMENT '主键'")
    private Long id;

    @Column(name = "username", columnDefinition = "varchar(32) COMMENT '用户名'")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(32) COMMENT '密码'")
    private String password;
}