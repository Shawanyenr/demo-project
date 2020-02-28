package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/new_home")
    public String toNewHome() {
        return "new_home";
    }

    @RequestMapping("/post_detail")
    public String toPostDetail() {
        return "post_detail";
    }

    @RequestMapping("/home")
    public String toHomePage() {
        return "home";
    }



}
