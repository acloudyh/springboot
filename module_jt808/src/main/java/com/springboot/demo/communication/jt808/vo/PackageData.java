package com.springboot.demo.communication.jt808.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.netty.channel.Channel;
import lombok.Data;

import java.util.Arrays;

/**
 * @author yanghao
 * @date 2019-04-28 18:17
 */
@Data
public class PackageData {
    /**
     * 16byte 消息头
     */
    protected MsgHeader msgHeader;

    // 消息体字节数组
    protected byte[] msgBodyBytes;

    /**
     * 校验码 1byte
     */
    protected int checkSum;

    //记录每个客户端的channel,以便下发信息给客户端
    @JSONField(serialize=false)
    protected Channel channel;

    @Override
    public String toString() {
        return "PackageData [msgHeader=" + msgHeader + ", msgBodyBytes=" + Arrays.toString(msgBodyBytes) + ", checkSum="
                + checkSum + ", address=" + channel + "]";
    }


    @Data
    public static class MsgHeader {
        // 消息ID
        protected int msgId;

        /////// ========消息体属性
        // byte[2-3]
        protected int msgBodyPropsField;
        // 消息体长度
        protected int msgBodyLength;
        // 数据加密方式
        protected int encryptionType;
        // 是否分包,true==>有消息包封装项
        protected boolean hasSubPackage;
        // 保留位[14-15]
        protected String reservedBit;
        /////// ========消息体属性

        // 终端手机号
        protected String terminalPhone;
        // 流水号
        protected int flowId;

        //////// =====消息包封装项
        // byte[12-15]
        protected int packageInfoField;
        // 消息包总数(word(16))
        protected long totalSubPackage;
        // 包序号(word(16))这次发送的这个消息包是分包中的第几个消息包, 从 1 开始
        protected long subPackageSeq;
        //////// =====消息包封装项

        @Override
        public String toString() {
            return "MsgHeader [msgId=" + msgId + ", msgBodyPropsField=" + msgBodyPropsField + ", msgBodyLength="
                    + msgBodyLength + ", encryptionType=" + encryptionType + ", hasSubPackage=" + hasSubPackage
                    + ", reservedBit=" + reservedBit + ", terminalPhone=" + terminalPhone + ", flowId=" + flowId
                    + ", packageInfoField=" + packageInfoField + ", totalSubPackage=" + totalSubPackage
                    + ", subPackageSeq=" + subPackageSeq + "]";
        }
    }


}
