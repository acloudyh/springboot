package com.springboot.demo.communication.jt808.service;

import com.alibaba.fastjson.JSON;
import com.springboot.demo.communication.jt808.server.SessionManager;
import com.springboot.demo.communication.jt808.service.codec.MsgEncoder;
import com.springboot.demo.communication.jt808.vo.PackageData;
import com.springboot.demo.communication.jt808.vo.Session;
import com.springboot.demo.communication.jt808.vo.req.LocationInfoUploadMsg;
import com.springboot.demo.communication.jt808.vo.req.TerminalAuthenticationMsg;
import com.springboot.demo.communication.jt808.vo.req.TerminalRegisterMsg;
import com.springboot.demo.communication.jt808.vo.resp.ServerCommonRespMsgBody;
import com.springboot.demo.communication.jt808.vo.resp.TerminalRegisterMsgRespBody;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yanghao
 * @date 2019-04-29 11:22
 */
@Slf4j
public class TerminalMsgProcessService extends BaseMsgProcessService {


    private MsgEncoder msgEncoder;
    private SessionManager sessionManager;

    public TerminalMsgProcessService() {
        this.msgEncoder = new MsgEncoder();
        this.sessionManager = SessionManager.getInstance();
    }

    public void processRegisterMsg(TerminalRegisterMsg msg) throws Exception {
        log.info("终端注册:{}", JSON.toJSONString(msg, true));

        final String sessionId = Session.buildId(msg.getChannel());
        Session session = sessionManager.findBySessionId(sessionId);
        if (session == null) {
            session = Session.buildSession(msg.getChannel(), msg.getMsgHeader().getTerminalPhone());
        }
        session.setAuthenticated(true);
        session.setTerminalPhone(msg.getMsgHeader().getTerminalPhone());
        sessionManager.put(session.getId(), session);

        TerminalRegisterMsgRespBody respMsgBody = new TerminalRegisterMsgRespBody();
        respMsgBody.setReplyCode(TerminalRegisterMsgRespBody.success);
        respMsgBody.setReplyFlowId(msg.getMsgHeader().getFlowId());
        // TODO 鉴权码暂时写死
        respMsgBody.setReplyToken("123");
        int flowId = super.getFlowId(msg.getChannel());
        byte[] bs = this.msgEncoder.encode4TerminalRegisterResp(msg, respMsgBody, flowId);

        super.send2Client(msg.getChannel(), bs);
    }

    public void processAuthMsg(TerminalAuthenticationMsg msg) throws Exception {
        // TODO 暂时每次鉴权都成功

        log.info("终端鉴权:{}", JSON.toJSONString(msg, true));

        final String sessionId = Session.buildId(msg.getChannel());
        Session session = sessionManager.findBySessionId(sessionId);
        if (session == null) {
            session = Session.buildSession(msg.getChannel(), msg.getMsgHeader().getTerminalPhone());
        }
        session.setAuthenticated(true);
        session.setTerminalPhone(msg.getMsgHeader().getTerminalPhone());
        sessionManager.put(session.getId(), session);

        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody();
        respMsgBody.setReplyCode(ServerCommonRespMsgBody.success);
        respMsgBody.setReplyFlowId(msg.getMsgHeader().getFlowId());
        respMsgBody.setReplyId(msg.getMsgHeader().getMsgId());
        int flowId = super.getFlowId(msg.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(msg, respMsgBody, flowId);
        super.send2Client(msg.getChannel(), bs);
    }

    public void processTerminalHeartBeatMsg(PackageData req) throws Exception {
        log.debug("心跳信息:{}", JSON.toJSONString(req, true));
        final PackageData.MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId = super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId);
        super.send2Client(req.getChannel(), bs);
    }

    public void processTerminalLogoutMsg(PackageData req) throws Exception {
        log.info("终端注销:{}", JSON.toJSONString(req, true));
        final PackageData.MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId = super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId);
        super.send2Client(req.getChannel(), bs);
    }

    public void processLocationInfoUploadMsg(LocationInfoUploadMsg req) throws Exception {
        log.info("位置 信息:{}", JSON.toJSONString(req, true));
        //TODO
        //可以做些业务逻辑，位置存库
        final PackageData.MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId = super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId);
        super.send2Client(req.getChannel(), bs);
    }
}
