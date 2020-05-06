package com.example.demo.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Integer userState;
    private String avatar;
    private Date frozeUntil;
}
