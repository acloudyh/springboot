package com.yang.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yanghao
 * @date 2019-04-18 16:05
 */
@Data
@Entity
@Table(name = "yh_user")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) AUTO_INCREMENT NOT NULL COMMENT '主键'")
    private Long id;

    @Column(name = "bill_code", columnDefinition = "varchar(32) COMMENT '运单号'", nullable = false)
    private String billCode;

    @Column(name = "username", columnDefinition = "varchar(32) COMMENT '业务员'", nullable = false)
    private String username;

    @Column(name = "tenant_id", columnDefinition = "varchar(32) COMMENT '租户id'", nullable = false)
    private String tenantId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", columnDefinition = "varchar(32) COMMENT '创建时间'")
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", columnDefinition = "varchar(32) COMMENT '更新时间'")
    private Date updateTime;

    @Column(name = "created_by", columnDefinition = "varchar(32) COMMENT '创建人'")
    private String createdBy;
}
