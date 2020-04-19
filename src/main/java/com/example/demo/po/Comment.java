package com.example.demo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;
    private Integer pid;
    private User user;
    private Integer parentId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date time;
}
