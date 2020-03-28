package com.example.demo.controller;

import com.example.demo.dao.PostDao;
import com.example.demo.po.Post;
import com.example.demo.service.PostDaoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private PostDao postDao;

    @Autowired
    private PostDaoService postDaoService;

    @RequestMapping("/posts")
    @ResponseBody
    public PageInfo<Post> posts(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        System.out.println("请求第" + start + "页");
        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDaoService.findAll();
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println(page);
        if (start > page.getPages()) {
            return null;
        }
        return page;
    }

    @RequestMapping("/posts/{id}")
    public String posts_detail(@PathVariable Integer id, RequestParam(value="u_id", defaultValue = null) Integer u_id, Model model) {
        System.out.println("请求post_id:" + id);
        Post post = postDaoService.onePost(id);
        if (null==post){
            model.addAttribute("msg","The resource you requested dose not exist.");
            return "error/404";
        }else
            System.out.println(post);
            model.addAttribute("post", post);
            return "post_detail";
    }

    @RequestMapping("/to_index")
    public String index() {
        return "test";
    }

    @RequestMapping("/tt")
    public String tt() {
        return "tt";
    }

    @RequestMapping("/test")
    public String toTestPage() {
        return "test";
    }
    @RequestMapping("/123")
    public String to123() {
        return "123";
    }

}
