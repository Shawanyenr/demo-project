package com.example.demo.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Post implements Serializable {
    private Integer p_id;
    private String p_title;
    private String u_username;
    private String img_dir;
    private Date upload_time;
    private Integer p_like;
    private Date p_last_edit_time;
/*
    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getP_title() {
        return p_title;
    }

    public void setP_title(String p_title) {
        this.p_title = p_title;
    }

    public String getU_username() {
        return u_username;
    }

    public void setU_username(String u_username) {
        this.u_username = u_username;
    }

    public String getImg_dir() {
        return img_dir;
    }

    public void setImg_dir(String img_dir) {
        this.img_dir = img_dir;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public Integer getP_like() {
        return p_like;
    }

    public void setP_like(Integer p_like) {
        this.p_like = p_like;
    }

    public Date getP_last_edit_time() {
        return p_last_edit_time;
    }

    public void setP_last_edit_time(Date p_last_edit_time) {
        this.p_last_edit_time = p_last_edit_time;
    }

    @Override
    public String toString() {
        return "Post{" +
                "p_id=" + p_id +
                ", p_title='" + p_title + '\'' +
                ", u_username='" + u_username + '\'' +
                ", img_dir='" + img_dir + '\'' +
                ", upload_time=" + upload_time +
                ", p_like=" + p_like +
                ", p_last_edit_time=" + p_last_edit_time +
                '}';
    }*/
}
