package com.yang.springboot.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanghao
 * @date 2019-05-15 16:09
 */
@RestController
@Slf4j
@RequestMapping(value = "/web/oauth2")
@Api(tags = "测试用", description = "测试用")
public class OAuthController {

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        return "order id : " + id;
    }

}
