package com.ewomail.domain.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Users {
    private Integer id;
    private Integer domainId;
    private String password;
    private String email;
    private String maildir;
    private String uname;
    private String tel;
    private Integer active;
    private Integer limits;
    private Integer limitg;
    private Date ctime;
} 