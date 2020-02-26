package com.example.demo.controller;

import com.example.demo.dao.PostDao;
import com.example.demo.po.Post;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private PostDao postDao;


    @RequestMapping("/to_index")
    public String index() {
        return "test";
    }

    @RequestMapping("/tt")
    public String tt(){
        return "tt";
    }

}
