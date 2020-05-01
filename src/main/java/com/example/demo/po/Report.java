package com.example.demo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Report {
    private Integer id;
    private Integer fromId;
    private Integer ownerId;
    private Integer pid;
    private Integer operation;
    private Date time;
    private Date operateTime;
    private Post post;
    private User fromUser;
    private User ownerUser;
}
