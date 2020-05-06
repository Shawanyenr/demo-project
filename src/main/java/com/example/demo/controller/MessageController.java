package com.example.demo.controller;

import com.example.demo.po.Comment;
import com.example.demo.po.User;
import com.example.demo.service.CommentDaoService;
import com.example.demo.service.MessageDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

@RequestMapping("/user")
@Controller
public class MessageController {

    @Autowired
    private MessageDaoService messageDaoService;

    @RequestMapping("/message")
    public String messagePage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<User> userList = messageDaoService.chatListUser(user.getUsername());

        model.addAttribute("userList", userList);
        return "message";
    }
}
