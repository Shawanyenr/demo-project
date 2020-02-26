package com.example.demo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private Integer p_id;
    private String u_username;
    private String p_title;
    private String img_dir;
    private Date upload_time;
    private Date p_last_edit_time;
    private Integer u_id;
    private Integer p_like;

}
