package com.smtteam.smt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by mengf on 2017/11/22 0022.
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送纯文本的简单邮件 
     * @param tos
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String[] tos, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(tos);
        message.setSubject(subject);
        message.setText(content);

        try {
            sender.send(message);
//            log.info("简单邮件已经发送。");
        } catch (Exception e) {
//            log.error("发送简单邮件时发生异常！", e);
        }
    }

    /**
     * 发送html格式的邮件 
     * @param tos
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String[] tos, String subject, String content){
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message  
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(tos);
            helper.setSubject(subject);
            helper.setText(content, true);

            sender.send(message);
        } catch (MessagingException e) {
//            log.error("发送html邮件时发生异常！", e);
        }
    }

    /**
     * 发送带附件的邮件 
     * @param tos
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String[] tos, String subject, String content, String filePath){
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message  
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(tos);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            sender.send(message);
//            log.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
//            log.error("发送带附件的邮件时发生异常！", e);
        }
    }

    /**
     * 发送嵌入静态资源（一般是图片）的邮件 
     * @param tos
     * @param subject
     * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" > 
     * @param rscPath 静态资源路径和文件名 
     * @param rscId 静态资源id 
     */
    public void sendInlineResourceMail(String[] tos, String subject, String content, String rscPath, String rscId){
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message  
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(tos);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            sender.send(message);
//            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
//            log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }
}
