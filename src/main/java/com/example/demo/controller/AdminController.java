package com.example.demo.controller;

import com.example.demo.service.AdminDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminDaoService adminDaoService;

    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "/adminLogin";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "/admin";
    }


}
