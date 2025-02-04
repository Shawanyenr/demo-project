package com.example.demo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer id;
    private String sender;
    private String receiver;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date time;
    private Integer checked;
    private Integer unreadNumById;
}
