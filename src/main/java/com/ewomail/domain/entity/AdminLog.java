package com.ewomail.domain.entity;

import lombok.Data;
import java.util.Date;

@Data
public class AdminLog {
    private Integer logId;
    private String username;
    private String ip;
    private String explain;
    private Date ctime;
} 