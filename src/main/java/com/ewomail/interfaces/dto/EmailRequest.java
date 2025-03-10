package com.ewomail.interfaces.dto;

import lombok.Data;

@Data
public class EmailRequest {
    private String from;
    private String to;
    private String subject;
    private String content;
    private String password;
    private String description;
}
