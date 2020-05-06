package com.example.demo.controller;

import com.example.demo.po.Comment;
import com.example.demo.po.User;
import com.example.demo.service.CommentDaoService;
import com.example.demo.service.MessageDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/reloadMessage")
    public String reloadMessage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<User> userList = messageDaoService.chatListUser(user.getUsername());
        model.addAttribute("userList", userList);
        return "message :: messageList";
    }

    @Transactional
    @ResponseBody
    @RequestMapping("/message/setRead")
    public String setRead(HttpSession session, @RequestParam(value = "uid", defaultValue = "") String username){
        User user = (User) session.getAttribute("user");
        if (username.equals("")||user==null) return "error: uid或user为空";
        System.out.println("/message/setRead "+user.getUsername()+"正在把来自"+username+"的消息设置为已读");
        messageDaoService.setReadByUid(user.getUsername(),username);
        return "ok";
    }
}
