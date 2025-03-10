package com.ewomail.application.service;

import com.ewomail.interfaces.dto.Email;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface EmailApplication {

    void sendEmail(String from, String to, String subject, String content, String password, String description) throws Exception;

    List<Email> receiveEmails(String emailAccount, String password) throws MessagingException, IOException;
}
