package com.yang.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yanghao
 * @date 2019-04-19 15:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

    private String from;

    private String to;

    private int sendPoolSize;

    private int sendTimeout;
}
