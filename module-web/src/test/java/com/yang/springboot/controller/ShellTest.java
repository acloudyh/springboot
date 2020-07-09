package com.yang.springboot.controller;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author Yang Hao
 * @date 2020/7/2
 */
@SpringBootTest
@Slf4j
public class ShellTest {
    private static String ip = "ip";
    private static String username = "root";
    private static String password = "password";

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
    public void test_shell_server_with_username_password() {
        RemoteConnectInfo connectInfo = new RemoteConnectInfo(ip, username, password);
        String cmd = "service server-1.0 restart";
        Connection connection = SSHToolUtil.login(connectInfo);
        String result = SSHToolUtil.execute(cmd, connection);
        System.out.println(result);

    }


    @Test
    public void test_shell_server_with_keyFile() {

        RemoteConnectInfo connectInfo = new RemoteConnectInfo(ip, username, password);
        File keyFile = new File("/Users/neo/Documents/certificate/devlab.pem");
        Connection connection = SSHToolUtil.loginByFileKey(connectInfo, keyFile, null);
        String cmd = "ls";
        String result = SSHToolUtil.execute(cmd, connection);
        System.out.println(result);
    }


    @Test
    public void test_shell_server_result() {
        RemoteConnectInfo connectInfo = new RemoteConnectInfo(ip, username, password);
        String cmd = "sshpass -p password scp /opt/server-1.0.0-SNAPSHOT.jar root@ip:/tmp/";
        Connection connection = SSHToolUtil.login(connectInfo);
        int status = SSHToolUtil.executeWithResult(cmd, connection);
        System.out.println(status);
    }


    @Test
    public void test_shell_server_upload_file() {
        RemoteConnectInfo connectInfo = new RemoteConnectInfo(ip, username, password);
        String cmd = "sshpass -p password scp /opt/server-1.0.0-SNAPSHOT.jar root@ip:/tmp/";
        Connection connection = SSHToolUtil.login(connectInfo);
        SCPClient scpClient = new SCPClient(connection);


    }


}
