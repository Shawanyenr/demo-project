package com.example.demo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private Integer p_id;
    private Integer u_id;
    private String u_username;
    private String u_avatar;
    private String p_title;
    private String img_dir;
    private Integer p_like_count;
    private Integer p_like_flag;
    private Integer p_fav_count;
    private Integer p_fav_flag;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date upload_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date p_last_edit_time;
    private Integer publicity;
    private User user;

}
