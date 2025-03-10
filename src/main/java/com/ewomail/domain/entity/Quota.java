package com.ewomail.domain.entity;

import lombok.Data;

@Data
public class Quota {
    private String email;
    private Long bytes;
    private Integer messages;
} 