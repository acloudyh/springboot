package com.yang.springboot.controller;

import com.yang.springboot.communication.SyncKafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @date 2019-04-18 16:50
 */
@RestController
@Slf4j
@RequestMapping(value = "/web/neo/test/kafka")
public class TestKafkaController {

    @Autowired
    private SyncKafkaSender syncKafkaSender;
    @RequestMapping(method = RequestMethod.GET)
    public void testKafka() {
        syncKafkaSender.send();
    }

}
