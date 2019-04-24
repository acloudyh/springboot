package com.yang.springboot.communication;

import lombok.Data;

import java.util.Date;

/**
 * @author yanghao
 * @date 2019-04-23 18:04
 */
@Data
public class KafkaMessage {

    private Long id;

    private String msg;

    private Date sendTime;

}
