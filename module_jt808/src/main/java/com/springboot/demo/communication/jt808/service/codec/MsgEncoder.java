package com.springboot.demo.communication.jt808.service.codec;


import com.springboot.demo.common.TPMSConstants;
import com.springboot.demo.communication.jt808.vo.PackageData;
import com.springboot.demo.communication.jt808.vo.Session;
import com.springboot.demo.communication.jt808.vo.req.TerminalRegisterMsg;
import com.springboot.demo.communication.jt808.vo.resp.ServerCommonRespMsgBody;
import com.springboot.demo.communication.jt808.vo.resp.TerminalRegisterMsgRespBody;
import com.springboot.demo.util.BitOperator;
import com.springboot.demo.util.JT808ProtocolUtil;

import java.util.Arrays;

/**
 * @author yanghao
 * @date 2019-04-29 11:46
 */
public class MsgEncoder {
    private BitOperator bitOperator;
    private JT808ProtocolUtil jt808ProtocolUtil;

    public MsgEncoder() {
        this.bitOperator = new BitOperator();
        this.jt808ProtocolUtil = new JT808ProtocolUtil();
    }

    public byte[] encode4TerminalRegisterResp(TerminalRegisterMsg req, TerminalRegisterMsgRespBody respMsgBody,
                                              int flowId) throws Exception {
        // 消息体字节数组
        byte[] msgBody = null;
        // 鉴权码(STRING) 只有在成功后才有该字段
        if (respMsgBody.getReplyCode() == TerminalRegisterMsgRespBody.success) {
            msgBody = this.bitOperator.concatAll(Arrays.asList(//
                    bitOperator.integerTo2Bytes(respMsgBody.getReplyFlowId()), // 流水号(2)
                    new byte[]{respMsgBody.getReplyCode()}, // 结果
                    respMsgBody.getReplyToken().getBytes(TPMSConstants.string_charset)// 鉴权码(STRING)
            ));
        } else {
            msgBody = this.bitOperator.concatAll(Arrays.asList(//
                    bitOperator.integerTo2Bytes(respMsgBody.getReplyFlowId()), // 流水号(2)
                    new byte[]{respMsgBody.getReplyCode()}// 错误代码
            ));
        }

        // 消息头
        int msgBodyProps = this.jt808ProtocolUtil.generateMsgBodyProps(msgBody.length, 0b000, false, 0);
        byte[] msgHeader = this.jt808ProtocolUtil.generateMsgHeader(req.getMsgHeader().getTerminalPhone(),
                TPMSConstants.cmd_terminal_register_resp, msgBody, msgBodyProps, flowId);
        byte[] headerAndBody = this.bitOperator.concatAll(msgHeader, msgBody);

        // 校验码
        int checkSum = this.bitOperator.getCheckSum4JT808(headerAndBody, 0, headerAndBody.length);
        // 连接并且转义
        return this.doEncode(headerAndBody, checkSum);
    }

    // public byte[] encode4ServerCommonRespMsg(TerminalAuthenticationMsg req,
    // ServerCommonRespMsgBody respMsgBody, int flowId) throws Exception {
    public byte[] encode4ServerCommonRespMsg(PackageData req, ServerCommonRespMsgBody respMsgBody, int flowId)
            throws Exception {
        byte[] msgBody = this.bitOperator.concatAll(Arrays.asList(//
                bitOperator.integerTo2Bytes(respMsgBody.getReplyFlowId()), // 应答流水号
                bitOperator.integerTo2Bytes(respMsgBody.getReplyId()), // 应答ID,对应的终端消息的ID
                new byte[]{respMsgBody.getReplyCode()}// 结果
        ));

        // 消息头
        int msgBodyProps = this.jt808ProtocolUtil.generateMsgBodyProps(msgBody.length, 0b000, false, 0);
        byte[] msgHeader = this.jt808ProtocolUtil.generateMsgHeader(req.getMsgHeader().getTerminalPhone(),
                TPMSConstants.cmd_common_resp, msgBody, msgBodyProps, flowId);
        byte[] headerAndBody = this.bitOperator.concatAll(msgHeader, msgBody);
        // 校验码
        int checkSum = this.bitOperator.getCheckSum4JT808(headerAndBody, 0, headerAndBody.length);
        // 连接并且转义
        return this.doEncode(headerAndBody, checkSum);
    }

    public byte[] encode4ParamSetting(byte[] msgBodyBytes, Session session) throws Exception {
        // 消息头
        int msgBodyProps = this.jt808ProtocolUtil.generateMsgBodyProps(msgBodyBytes.length, 0b000, false, 0);
        byte[] msgHeader = this.jt808ProtocolUtil.generateMsgHeader(session.getTerminalPhone(),
                TPMSConstants.cmd_terminal_param_settings, msgBodyBytes, msgBodyProps, session.currentFlowId());
        // 连接消息头和消息体
        byte[] headerAndBody = this.bitOperator.concatAll(msgHeader, msgBodyBytes);
        // 校验码
        int checkSum = this.bitOperator.getCheckSum4JT808(headerAndBody, 0, headerAndBody.length);
        // 连接并且转义
        return this.doEncode(headerAndBody, checkSum);
    }

    private byte[] doEncode(byte[] headerAndBody, int checkSum) throws Exception {
        byte[] noEscapedBytes = this.bitOperator.concatAll(Arrays.asList(//
                new byte[]{TPMSConstants.pkg_delimiter}, // 0x7e
                headerAndBody, // 消息头+ 消息体
                bitOperator.integerTo1Bytes(checkSum), // 校验码
                new byte[]{TPMSConstants.pkg_delimiter}// 0x7e
        ));
        // 转义
        return jt808ProtocolUtil.doEscape4Send(noEscapedBytes, 1, noEscapedBytes.length - 2);
    }
}
