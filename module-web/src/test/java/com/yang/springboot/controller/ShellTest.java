package com.yang.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Yang Hao
 * @date 2020/7/2
 */
@SpringBootTest
@Slf4j
public class ShellTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_shell() {
        String bashCommand = "ll";  //①
        Runtime runtime = Runtime.getRuntime();
        try {
            Process pro = runtime.exec(bashCommand);  //②
            int status = pro.waitFor();  //③
            if (status != 0) {  //④
                log.error("error");
                return;
            }
            log.info("success");
        } catch (Exception e) {
            log.error("报错了");
        }

    }


    @Test
    public void test_shell_server() throws IOException {
        SSHTool tool = new SSHTool("ip", "username", "password", StandardCharsets.UTF_8);
        String cmd = "service server-1.0 restart";
        StringBuilder exec = tool.exec(cmd);
        System.out.println(exec);

    }
}
