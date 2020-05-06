package com.example.demo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Notification {
    private Integer id;
    private Integer uid;
    private Date time;
    private Integer isread;
    private String content;
    private Integer pid;
    private Integer fromid;
    private User user;
}
