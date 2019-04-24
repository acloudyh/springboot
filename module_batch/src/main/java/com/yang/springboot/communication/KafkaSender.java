package com.yang.springboot.communication;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author yanghao
 * @date 2019-04-24 11:12
 */
@Component
@Slf4j
public class KafkaSender {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send() {
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setId(System.currentTimeMillis());
        kafkaMessage.setMsg(UUID.randomUUID().toString());
        kafkaMessage.setSendTime(new Date());
        String gson = new Gson().toJson(kafkaMessage);
        log.info("========> 开始发送消息，kafkaMessage:{}", gson);

        kafkaTemplate.send("springboot-demo-yang", gson);
    }
}
