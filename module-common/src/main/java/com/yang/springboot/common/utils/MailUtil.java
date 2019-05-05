package com.yang.springboot.common.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import java.io.InputStream;


/**
 * @author yanghao
 * @date 2019-04-19 11:51
 */
@Slf4j
public class MailUtil {


    /**
     * 发送普通邮件
     *
     * @param mailParam
     */
    public static void sendTextEmail(MailParam mailParam, JavaMailSender mailSender) {
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setTo(mailParam.getTo());
            helper.setFrom(mailParam.getFrom());
            helper.setSubject(mailParam.getSubject());
            helper.setText(mailParam.getContent());
            mailSender.send(msg);
        } catch (Exception e) {
            log.error("发送邮件失败error:{},content:{}", e, mailParam.getContent());
        }

    }

    /**
     * 发送thymeleaf 模板邮件
     *
     * @param mailParam
     */
    public static void sendHtmlEmail(MailParam mailParam, JavaMailSender mailSender) {
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setTo(mailParam.getTo());
            helper.setFrom(mailParam.getFrom());
            helper.setSubject(mailParam.getSubject());
            helper.setText(mailParam.getContent(), true);
            mailSender.send(msg);
        } catch (Exception e) {
            log.error("发送邮件失败error:{},content:{}", e, mailParam.getContent());
        }

    }

    /**
     * 发送带附件的邮件。（excel）
     *
     * @param mailParam
     */
    public static void sendExcelMail(MailParam mailParam, JavaMailSender mailSender) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailParam.getFrom());
            helper.setTo(mailParam.getTo());
            helper.setSubject(mailParam.getSubject());
            helper.setText(mailParam.getContent(), true);
            String fileName = mailParam.getSubject() + ".xlsx";
            //添加多个附件可以使用多条x
            //helper.addAttachment(fileName,file);
            DataSource source = new ByteArrayDataSource(mailParam.getInputStream(), "application/msexcel");
            helper.addAttachment(MimeUtility.encodeText(fileName), source);
            mailSender.send(message);
            log.info("带附件的邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送带附件的邮件失败");
        }
    }

    @Data
    public static class MailParam {
        /**
         * 发送带附件的邮件
         * to       接受者
         * subject  主题
         * content  内容
         * filePath 文件路径
         */
        private String from;
        private String to;
        private String subject;
        private String content;
        private String filePath;
        private InputStream inputStream;

        public MailParam(String from, String to, String subject, String content, InputStream inputStream) {
            this.from = from;
            this.to = to;
            this.subject = subject;
            this.content = content;
            this.inputStream = inputStream;
        }
    }

}
