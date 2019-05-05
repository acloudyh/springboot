package com.springboot.demo.communication.jt808.vo.req;


import com.springboot.demo.common.TPMSConstants;
import com.springboot.demo.communication.jt808.vo.PackageData;

import java.util.Arrays;

/**
 * 终端鉴权消息
 *
 * @author yanghao
 * @date 2019-04-29 11:46
 */
public class TerminalAuthenticationMsg extends PackageData {
    private String authCode;

    public TerminalAuthenticationMsg() {
    }

    public TerminalAuthenticationMsg(PackageData packageData) {
        this();
        this.channel = packageData.getChannel();
        this.checkSum = packageData.getCheckSum();
        this.msgBodyBytes = packageData.getMsgBodyBytes();
        this.msgHeader = packageData.getMsgHeader();
        this.authCode = new String(packageData.getMsgBodyBytes(), TPMSConstants.string_charset);
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "TerminalAuthenticationMsg [authCode=" + authCode + ", msgHeader=" + msgHeader + ", msgBodyBytes="
                + Arrays.toString(msgBodyBytes) + ", checkSum=" + checkSum + ", channel=" + channel + "]";
    }

}
