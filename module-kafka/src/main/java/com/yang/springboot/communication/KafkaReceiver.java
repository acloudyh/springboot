package com.yang.springboot.communication;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author yanghao
 * @date 2019-04-24 15:32
 */
@Slf4j
@Component
public class KafkaReceiver {

    @KafkaListener(topics = {"my-topic"})
    // 可以监听多个topic
//    @KafkaListener(topics = {"springboot-demo-yang","springboot-demo-yang2"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> msg = Optional.ofNullable(record.value());
        if (msg.isPresent()) {

            Object message = msg.get();
            log.info("========> 开始接受kafka record record:{}", record);
            log.info("========> 开始接受kafka message message:{}", message);
        }
    }
}
