package com.ewomail.domain.entity;

import lombok.Data;

@Data
public class AdminMenu {
    private Integer menuId;
    private String mark;
    private String lang;
    private String url;
    private Integer topId;
    private Integer edit;
    private Integer del;
    private Integer editId;
    private Integer sort;
} 