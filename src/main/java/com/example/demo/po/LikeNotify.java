package com.example.demo.po;

import lombok.Data;

import java.util.Date;

@Data
public class LikeNotify {
    private Integer id;
    private Date time;
    private Integer uid;
    private Integer isread;
    private Integer pid;
    private Integer fromid;
    private User user;
}
