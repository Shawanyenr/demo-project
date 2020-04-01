package com.example.demo.controller;

import com.example.demo.po.Post;
import com.example.demo.po.User;
import com.example.demo.service.PostDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ViewController {
    @Autowired
    private PostDaoService postDaoService;

    @RequestMapping("/new_home")
    public String toNewHome() {
        return "new_home";
    }

    @RequestMapping("/post_detail")
    public String toPostDetail() {
        return "post_detail";
    }

    @RequestMapping("/home")
    public String toHomePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);

        List<Post> posts = postDaoService.findAllOfOneUser(user.getId(), user.getId());
        model.addAttribute("posts", posts);
        return "home";
    }


}
