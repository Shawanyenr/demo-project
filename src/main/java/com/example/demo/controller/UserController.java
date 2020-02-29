package com.example.demo.controller;

import com.example.demo.po.User;
import com.example.demo.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll() {
        List<User> users = userDaoService.findAll();
        return users;
    }

    @RequestMapping("/login.action")
    @ResponseBody
    public String findOne(User loginInfo, Model m, HttpSession s) {
        System.out.println("登录信息" + loginInfo);
        User user = userDaoService.findOne(loginInfo);
        System.out.println(user);
        String msg = null;
        if (user == null) {
            msg = "fail: 0";
            s.invalidate();
        } else if (user.getUserState() == 0) {
            s.invalidate();
            msg = "fail: 1";
        } else {
            user.setPassword("");
            /*s.setAttribute("name", user.getName());
            s.setAttribute("username", loginInfo.getUsername());*/
            s.setAttribute("user", user);
            msg = "success";
        }
        return msg;
    }

    @Transactional
    @RequestMapping("/register.action")
    @ResponseBody
    public String saveOne(User registerInfo, Model m, HttpSession s) {
        System.out.println("注册信息：" + registerInfo);
        Integer check = userDaoService.checkUsername(registerInfo.getUsername());
        System.out.println("重名个数：" + check);
        String msg = null;
        if (check != 0) {
            msg = "fail";
            s.invalidate();
        } else {
            Integer trySaveOne = userDaoService.saveUser(registerInfo);
            System.out.println("存储了" + trySaveOne + "个信息");
            if (trySaveOne == 1) {
                msg = "ok";
            } else {
                s.invalidate();
                msg = "x";
            }
        }
        System.out.println("msg: " + msg);
        return msg;
    }

    @RequestMapping("/logout.action")
    public String logout(HttpSession session) {
        // 清除Session
        session.invalidate();
        // 重定向到登录页面的跳转方法
        return "redirect:test";
    }
}
