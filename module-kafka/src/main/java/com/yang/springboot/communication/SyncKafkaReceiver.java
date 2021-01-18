package com.yang.springboot.communication;

import com.yang.springboot.communication.result.ResultDto;
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
public class SyncKafkaReceiver {

    @KafkaListener(topics = {"my-topic-sync"})
    // 可以监听多个topic
//    @KafkaListener(topics = {"springboot-demo-yang","springboot-demo-yang2"})
    public ResultDto<String> listen(ConsumerRecord<?, ?> record) throws Exception {
        Optional<?> msg = Optional.ofNullable(record.value());
        if (msg.isPresent()) {
            Object message = msg.get();
            log.info("========> 开始接受kafka record record:{}", record);
            log.info("========> 开始接受kafka message message:{}", message);
        }

        log.error("模拟错误返回---------");

        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new Exception("客家话估计过户脚后跟还感觉好吧是就看不见客户机和大街上的骄傲黑色的卡吉安市");
        }
        return null;
    }
}
