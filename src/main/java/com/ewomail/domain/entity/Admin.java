package com.ewomail.domain.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Admin {
    private Integer aid;
    private String username;
    private String password;
    private String name;
    private Integer superAdmin;
    private Integer gid;
    private Integer active;
    private Integer isWebmail;
    private Date ctime;
} 