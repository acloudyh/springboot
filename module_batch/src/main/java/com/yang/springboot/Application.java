package com.yang.springboot;

import com.yang.springboot.communication.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Hello world!
 */
@SpringBootApplication
public class Application {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        KafkaSender kafkaSender = context.getBean(KafkaSender.class);

        for (int i = 0; i < 3; i++) {
            //调用消息发送类中的消息发送方法
            kafkaSender.send();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
