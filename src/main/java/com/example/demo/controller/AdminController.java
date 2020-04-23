package com.example.demo.controller;

import com.example.demo.po.Admin;
import com.example.demo.po.User;
import com.example.demo.service.AdminDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminDaoService adminDaoService;

    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "/adminLogin";
    }

    @RequestMapping("/adminLogin.action")
    public String adminLoginInfo(Admin admin, Model model,HttpSession session){
        Admin admin1 = adminDaoService.findOne(admin);
        if (admin1==null){
            model.addAttribute("msg","Wrong username or password!");
        }else{
            admin1.setPassword("");
            session.setAttribute("admin",admin1);
            model.addAttribute("success","Log in successfully.");
        }
        return "adminLogin :: msg-section";
    }

    @RequestMapping("/admin")
    public String admin(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if (null == admin) {
            return "adminLogin";
        }
        return "/admin";
    }


}
