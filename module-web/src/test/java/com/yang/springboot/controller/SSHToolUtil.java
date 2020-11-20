package com.yang.springboot.controller;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @author Yang Hao
 * @date 2020/7/2
 */
@Slf4j
public class SSHToolUtil {

    private static String DEFAULT_CHARSET = "UTF-8";
    private static Connection conn;

    /**
     * 远程登录服务器 用户名和密码
     *
     * @return
     */
    public static Connection login(RemoteConnectInfo connectInfo) {
        conn = new Connection(connectInfo.getIp());

        try {
            // 连接
            conn.connect();
            // 认证
            conn.authenticateWithPassword(connectInfo.getUsername(), connectInfo.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 秘钥方式 远程登录服务器
     *
     * @param keyFile     秘钥文件(PEM，不能丢失"-----BEGIN DSA PRIVATE KEY-----" or "-----BEGIN RSA PRIVATE KEY-----"标签)
     * @param keyfilePass 如果秘钥文件加密 需要用该参数解密，如果没有加密可以为null
     * @return
     */
    public static Connection loginByFileKey(RemoteConnectInfo connectInfo, File keyFile, String keyfilePass) {
        boolean flag = false;

        try {
            conn = new Connection(connectInfo.getIp());
            conn.connect();
            // 登录认证
            flag = conn.authenticateWithPublicKey(connectInfo.getUsername(), keyFile, keyfilePass);
            if (flag) {
                log.info("认证成功！");
            } else {
                log.info("认证失败！");
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 执行Shell脚本或命令
     *
     * @param cmd 命令行序列
     * @return 脚本输出结果
     */
    public static String execute(String cmd, Connection connection) {
        String result = "";
        try {
            Session session = connection.openSession();// 打开一个会话
            session.execCommand(cmd);// 执行命令
            result = processStdout(session.getStdout(), DEFAULT_CHARSET);
            // 如果为得到标准输出为空，说明脚本执行出错了
            if (StringUtils.isBlank(result)) {
                result = processStdout(session.getStderr(), DEFAULT_CHARSET);
            }
            connection.close();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 执行shell 查看结果 -1失败 0 成功
     *
     * @param cmd
     * @param connection
     * @return
     */
    public static int executeWithResult(String cmd, Connection connection) {
        int status = -1;
        try {
            Session session = connection.openSession();// 打开一个会话
            session.execCommand(cmd);// 执行命令
            session.waitForCondition(ChannelCondition.EXIT_STATUS, 1000 * 10);
            status = session.getExitStatus();
            connection.close();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


    /**
     * 解析脚本执行的返回结果
     *
     * @param in      输入流对象
     * @param charset 编码
     * @return String 以纯文本的格式返回
     * @throws
     */
    public static String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws IOException {

//        //参考链接: https://juejin.im/post/5d843679f265da03f04d0687
//
//        SSHTool tool = new SSHTool("ip", "root", "password", StandardCharsets.UTF_8);
//        String cmd = "service gmp-server-1.0 restart";
//        cmd = "sshpass -p oracle scp /opt/logback.xml root@ip:/tmp";
//        StringBuilder exec = tool.exec(cmd);
//        System.out.println(exec);


    }
}


