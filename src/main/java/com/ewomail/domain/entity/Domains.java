package com.ewomail.domain.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Domains {
    private Integer id;
    private String name;
    private Integer active;
    private Integer sNum;
    private Integer cNum;
    private Integer cIp;
    private Integer g;
    private Integer gBoundary;
    private Date ctime;
} 