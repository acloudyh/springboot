package com.yang.springboot.jpa;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yanghao
 * @date 2019-04-18 16:30
 */
@Data
@Entity
@Table(name = "yh_waybill")
@EntityListeners(AuditingEntityListener.class)
public class WaybillDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) AUTO_INCREMENT NOT NULL COMMENT '主键'")
    private Integer id;

    @Column(name = "bill_code", columnDefinition = "varchar(32) COMMENT '运单号'", nullable = false)
    private String billCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", columnDefinition = "datetime COMMENT '创建时间'")
    @CreatedDate
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'")
    @LastModifiedDate
    private Date updateTime;

    @Column(name = "carrier_name", columnDefinition = "varchar(32) COMMENT '承运人姓名'")
    private String carrierName;

    @Column(name = "carrier_email", columnDefinition = "varchar(128) COMMENT '承运人邮箱'")
    private String carrierEmail;

}
