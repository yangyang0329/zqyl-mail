package com.ewomail.interfaces.controller;

import com.ewomail.application.service.EmailApplication;
import com.ewomail.interfaces.dto.Email;
import com.ewomail.interfaces.dto.EmailRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Resource
    private EmailApplication emailApplication;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailApplication.sendEmail(
                    emailRequest.getFrom(),
                    emailRequest.getTo(),
                    emailRequest.getSubject(),
                    emailRequest.getContent(),
                    emailRequest.getPassword(),
                    emailRequest.getDescription()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("邮件发送成功");
    }

    @GetMapping("/receive")
    public ResponseEntity<?> receiveEmails(@RequestParam String emailAccount, @RequestParam String password) {
        try {
            List<Email> emails = emailApplication.receiveEmails(emailAccount, password);
            return ResponseEntity.ok(emails);
        } catch (MessagingException | IOException e) {
            return ResponseEntity.status(500).body("接收邮件失败: " + e.getMessage());
        }
    }
}

