package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class newTestController {
    @RequestMapping("/new_home")
    public String toNewHome(){
        return "new_home";
    }

    @RequestMapping("/test1")
    public String toNewHome1(){
        return "test1";
    }

    @RequestMapping("/test2")
    public String toNewHome2(){
        return "test2";
    }
    @RequestMapping("/test3")
    public String toNewHome3(){
        return "jqueryTest";
    }
    @RequestMapping("/test4")
    public String toNewHome4(){
        return "post_detail";
    }
}
