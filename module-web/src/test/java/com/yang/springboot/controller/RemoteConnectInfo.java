package com.yang.springboot.controller;

import lombok.Data;

/**
 * @author Yang Hao
 * @date 2020/7/7
 */
@Data
public class RemoteConnectInfo {

    private String ip;
    private String username;
    private String password;


    public RemoteConnectInfo(String ip, String username, String password) {
        this.ip = ip;
        this.username = username;
        this.password = password;
    }
}
