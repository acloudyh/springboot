package com.springboot.demo.communication.jt808.server;

import com.springboot.demo.common.TPMSConstants;
import com.springboot.demo.communication.jt808.service.TerminalMsgProcessService;
import com.springboot.demo.communication.jt808.service.codec.MsgDecoder;
import com.springboot.demo.communication.jt808.vo.PackageData;
import com.springboot.demo.communication.jt808.vo.Session;
import com.springboot.demo.communication.jt808.vo.req.LocationInfoUploadMsg;
import com.springboot.demo.communication.jt808.vo.req.TerminalAuthenticationMsg;
import com.springboot.demo.communication.jt808.vo.req.TerminalRegisterMsg;
import com.springboot.demo.util.HexStringUtil;
import com.springboot.demo.util.JT808ProtocolUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yanghao
 * @date 2019-04-29 11:16
 */
@Slf4j
public class TCPServerHandler extends ChannelInboundHandlerAdapter { // (1)


    private final SessionManager sessionManager;
    private final MsgDecoder decoder;
    private TerminalMsgProcessService msgProcessService;
    private JT808ProtocolUtil jt808ProtocolUtil;

    public TCPServerHandler() {
        this.sessionManager = SessionManager.getInstance();
        this.decoder = new MsgDecoder();
        this.jt808ProtocolUtil = new JT808ProtocolUtil();
        this.msgProcessService = new TerminalMsgProcessService();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException { // (2)
        try {
            ByteBuf buf = (ByteBuf) msg;
            buf.retain();
            if (buf.readableBytes() <= 0) {
                ReferenceCountUtil.safeRelease(msg);
                return;
            }

            byte[] bs = new byte[buf.readableBytes()];
            buf.readBytes(bs);
            log.info("接收的字节流：{}", HexStringUtil.bytesToHexString(bs));
            bs = this.jt808ProtocolUtil.doEscape4Receive(bs, 0, bs.length);
            // 字节数据转换为针对于808消息结构的实体类
            PackageData pkg = this.decoder.bytes2PackageData(bs);
            // 引用channel,以便回送数据给硬件
            pkg.setChannel(ctx.channel());
            this.processPackageData(pkg);
        } catch (Exception e) {
            log.error("读取sock字节数据异常：", e);
        } finally {
            release(msg);
        }
    }

    /**
     * 处理业务逻辑
     *
     * @param packageData
     */
    private void processPackageData(PackageData packageData) {
        final PackageData.MsgHeader header = packageData.getMsgHeader();

        // 1. 终端心跳-消息体为空 ==> 平台通用应答
        if (TPMSConstants.msg_id_terminal_heart_beat == header.getMsgId()) {
            log.info(">>>>>[终端心跳],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            try {
                this.msgProcessService.processTerminalHeartBeatMsg(packageData);
                log.info("<<<<<[终端心跳],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            } catch (Exception e) {
                log.error("<<<<<[终端心跳]处理错误,phone={},flowid={},err={}", header.getTerminalPhone(), header.getFlowId(),
                        e.getMessage());
                e.printStackTrace();
            }
        }

        // 5. 终端鉴权 ==> 平台通用应答
        else if (TPMSConstants.msg_id_terminal_authentication == header.getMsgId()) {
            log.info(">>>>>[终端鉴权],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            try {
                TerminalAuthenticationMsg authenticationMsg = new TerminalAuthenticationMsg(packageData);
                this.msgProcessService.processAuthMsg(authenticationMsg);
                log.info("<<<<<[终端鉴权],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            } catch (Exception e) {
                log.error("<<<<<[终端鉴权]处理错误,phone={},flowid={},err={}", header.getTerminalPhone(), header.getFlowId(),
                        e.getMessage());
                e.printStackTrace();
            }
        }
        // 6. 终端注册 ==> 终端注册应答
        else if (TPMSConstants.msg_id_terminal_register == header.getMsgId()) {
            log.info(">>>>>[终端注册],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            try {
                TerminalRegisterMsg msg = this.decoder.toTerminalRegisterMsg(packageData);
                this.msgProcessService.processRegisterMsg(msg);
                log.info("<<<<<[终端注册],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            } catch (Exception e) {
                log.error("<<<<<[终端注册]处理错误,phone={},flowid={},err={}", header.getTerminalPhone(), header.getFlowId(),
                        e.getMessage());
                e.printStackTrace();
            }
        }
        // 7. 终端注销(终端注销数据消息体为空) ==> 平台通用应答
        else if (TPMSConstants.msg_id_terminal_log_out == header.getMsgId()) {
            log.info(">>>>>[终端注销],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            try {
                this.msgProcessService.processTerminalLogoutMsg(packageData);
                log.info("<<<<<[终端注销],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            } catch (Exception e) {
                log.error("<<<<<[终端注销]处理错误,phone={},flowid={},err={}", header.getTerminalPhone(), header.getFlowId(),
                        e.getMessage());
                e.printStackTrace();
            }
        }
        // 3. 位置信息汇报 ==> 平台通用应答
        else if (TPMSConstants.msg_id_terminal_location_info_upload == header.getMsgId()) {
            log.info(">>>>>[位置信息],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            try {
                LocationInfoUploadMsg locationInfoUploadMsg = this.decoder.toLocationInfoUploadMsg(packageData);
                System.out.println(locationInfoUploadMsg);
                this.msgProcessService.processLocationInfoUploadMsg(locationInfoUploadMsg);
                log.info("<<<<<[位置信息],phone={},flowid={}", header.getTerminalPhone(), header.getFlowId());
            } catch (Exception e) {
                log.error("<<<<<[位置信息]处理错误,phone={},flowid={},err={}", header.getTerminalPhone(), header.getFlowId(),
                        e.getMessage());
                e.printStackTrace();
            }
        }
        // 其他情况
        else {
            log.error(">>>>>>[未知消息类型],phone={},msgId={},package={}", header.getTerminalPhone(), header.getMsgId(),
                    packageData);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        log.error("发生异常:{}", cause.getMessage());
        cause.printStackTrace();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Session session = Session.buildSession(ctx.channel());
        sessionManager.put(session.getId(), session);
        log.info("终端连接:{}", session);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final String sessionId = ctx.channel().id().asLongText();
        Session session = sessionManager.findBySessionId(sessionId);
        this.sessionManager.removeBySessionId(sessionId);
        log.info("终端断开连接:{}", session);
        ctx.channel().close();
        // ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                Session session = this.sessionManager.removeBySessionId(Session.buildId(ctx.channel()));
                log.info("服务器主动断开连接:{}", session);
                ctx.close();
            }
        }
    }

    private void release(Object msg) {
        try {
            ReferenceCountUtil.release(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
