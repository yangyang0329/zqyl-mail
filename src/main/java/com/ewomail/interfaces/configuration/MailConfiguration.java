package com.ewomail.interfaces.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MailConfiguration {

    // SMTP配置
    @Value("${mail.smtp.host}")
    private String smtpHost;

    @Value("${mail.smtp.port}")
    private Integer smtpPort;

    @Value("${mail.smtp.username}")
    private String smtpUsername;

    @Value("${mail.smtp.password}")
    private String smtpPassword;

    @Value("${mail.smtp.auth}")
    private Boolean smtpAuth;

    @Value("${mail.smtp.starttls}")
    private Boolean smtpStarttls;

    // IMAP配置
    @Value("${mail.imap.host}")
    private String imapHost;

    @Value("${mail.imap.port}")
    private Integer imapPort;

    @Value("${mail.imap.username}")
    private String imapUsername;

    @Value("${mail.imap.password}")
    private String imapPassword;

    @Value("${mail.imap.ssl}")
    private Boolean imapSsl;

    // Getters
    // ...
}
