package com.ewomail.interfaces.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Email {
    private Long id;
    private String sender;
    private String password;
    private String recipient;
    private String subject;
    private String content;
    private String description;
    private Date sentDate;
    private Date receiveDate;

}
