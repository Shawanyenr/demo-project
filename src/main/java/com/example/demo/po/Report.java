package com.example.demo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Report {
    private Integer id;
    private Integer fromId;
    private Integer pid;
    private Integer duration;
    private Date time;
    private Date operateTime;
    private Integer archived;
    private Post post;
    private User fromUser;
}
