package com.ewomail.localtest;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import java.util.Properties;

public class EmailLocalTest {

    public static void main(String[] args) {
//        sendEmail();
//        receiveEmail();
        receiveEmailUnRead();
    }

    public static void receiveEmail() {
        // 邮件账号和密码
        String emailAccount = "test@cnyunlian.com";
        String password = "test1234";

        // IMAP服务器配置
        String imapHost = "imap.cnyunlian.com";
        int imapPort = 993;

        // 设置邮件属性
        Properties properties = new Properties();
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.imap.ssl.trust", "*");
        properties.put("mail.imap.host", imapHost);
        properties.put("mail.imap.port", imapPort);

        // 创建会话
        Session session = Session.getInstance(properties);

        try {
            // 获取Store对象
            Store store = session.getStore("imap");
            store.connect(imapHost, emailAccount, password);

            // 打开收件箱
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // 获取邮件消息数量
            int messageCount = inbox.getMessageCount();
            System.out.println("邮件总数: " + messageCount);

            // 遍历邮件
            for (int i = 1; i <= messageCount; i++) {
                Message message = inbox.getMessage(i);
                System.out.println("主题: " + message.getSubject());
                System.out.println("发件人: " + message.getFrom()[0]);
                // 可以根据需要获取更多邮件信息，如内容等
            }

            // 关闭文件夹和Store连接
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiveEmailUnRead() {
        // 邮件账号和密码
        String emailAccount = "test@cnyunlian.com";
        String password = "test1234";

        // IMAP服务器配置
        String imapHost = "imap.cnyunlian.com";
        int imapPort = 993;

        // 设置邮件属性
        Properties properties = new Properties();
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.imap.ssl.trust", "*");
        properties.put("mail.imap.host", imapHost);
        properties.put("mail.imap.port", imapPort);

        // 创建会话
        Session session = Session.getInstance(properties);

        try {
            // 获取Store对象
            Store store = session.getStore("imap");
            store.connect(imapHost, emailAccount, password);

            // 打开收件箱
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            // 获取未读邮件列表
            Message[] unreadMessages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            for (Message message : unreadMessages) {
                System.out.println("未读邮件主题: " + message.getSubject());
            }
            // 关闭文件夹和Store连接
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendEmail() {
        // 邮件账号和密码
        String fromEmail = "test1@cnyunlian.com";
        String password = "test1234";

        // 收件人邮箱
        String toEmail = "test@cnyunlian.com";

        // SMTP服务器配置
        String smtpHost = "smtp.cnyunlian.com";
        int smtpPort = 25;

        // 设置邮件属性
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        // 创建会话
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // 创建邮件消息
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Test Email");
            message.setText("This is a test email sent using JavaMail.");

            // 发送邮件
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
