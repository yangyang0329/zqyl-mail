package com.ewomail.application.service.impl;

import com.ewomail.application.service.EmailApplication;
import com.ewomail.interfaces.configuration.MailConfiguration;
import com.ewomail.interfaces.dto.Email;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Service
public class EmailApplicationImpl implements EmailApplication {
    @Resource
    private MailConfiguration mailConfig;

    @Override
    public void sendEmail(String from, String to, String subject, String content, String password, String description) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", mailConfig.getSmtpAuth());
        props.put("mail.smtp.host", mailConfig.getSmtpHost());
        props.put("mail.smtp.port", mailConfig.getSmtpPort());
//        props.setProperty("mail.debug", "true");
        // smtp登陆的账号、密码 ；需开启smtp登陆
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);
        message.setDescription(description);
        Transport.send(message);
    }

    @Override
    public List<Email> receiveEmails(String emailAccount, String password) throws MessagingException, IOException {
        // 设置邮件属性
        Properties properties = new Properties();
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.imap.ssl.trust", "*");
        properties.put("mail.imap.host", mailConfig.getImapHost());
        properties.put("mail.imap.port", mailConfig.getImapPort());

        // 创建会话
        Session session = Session.getInstance(properties);


        // 获取Store对象
        Store store = session.getStore("imap");
        store.connect(mailConfig.getImapHost(), emailAccount, password);

        // 打开收件箱
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        Message[] messages = inbox.getMessages();
        // 获取邮件消息数量
        int messageCount = inbox.getMessageCount();
        System.out.println("邮件总数: " + messageCount);

        List<Email> emailList = new ArrayList<>();
        for (Message msg : messages) {
            Email email = new Email();
            email.setSender(msg.getFrom()[0].toString());
            email.setRecipient(Arrays.toString(msg.getRecipients(Message.RecipientType.TO)));
            email.setSubject(msg.getSubject());
            email.setContent(msg.getContent().toString());
            email.setReceiveDate(msg.getReceivedDate());
            email.setSentDate(msg.getSentDate());
            email.setDescription(msg.getDescription());
            emailList.add(email);
        }
        // 关闭文件夹和Store连接
        inbox.close(false);
        store.close();
        return emailList;
    }
}