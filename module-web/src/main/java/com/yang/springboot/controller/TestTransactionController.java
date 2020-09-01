package com.yang.springboot.controller;

import com.yang.springboot.service.TestTransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Yang Hao
 * @date 2020/9/1
 */
@RestController
@Slf4j
@RequestMapping(value = "/web/neo/transaction")
@Api(tags = "spring事务")
public class TestTransactionController {

    @Resource
    private TestTransactionService testTransactionService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "测试事务嵌套", notes = "测试事务嵌套")
    public void testTransaction() {
        testTransactionService.serviceA();
//        testTransactionService.serviceB();
    }
}
