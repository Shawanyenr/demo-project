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

    @RequestMapping({"/login",""})
    public String toLogin(HttpSession session) {
        session.removeAttribute("admin");
        return "login";
    }

    @RequestMapping("/register")
    public String toRegister(Model model) {
        return "register";
    }

    @RequestMapping("/home")
    public String toHomePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null == user) {
            return "redirect:/login";
        } else {
            List<Post> posts  = postDaoService.findAllOfOneUser(user.getId(),user.getId());
            List<Post> likes = postDaoService.findAllLike(user.getId());
            List<Post> favs = postDaoService.findAllFav(user.getId());
            System.out.println("posts: \n"+posts);
            System.out.println("likes: \n"+likes);
            System.out.println("favs: \n"+favs);
            model.addAttribute("posts", posts);
            model.addAttribute("likes", likes);
            model.addAttribute("favs", favs);
        }
        return "home";
    }



}
