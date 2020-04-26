package com.example.demo.controller;

import com.example.demo.po.Admin;
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
        return "/admin/adminLogin";
    }

    @RequestMapping("/adminLogin.action")
    public String adminLoginInfo(Admin admin, Model model,HttpSession session){
        Admin admin1 = adminDaoService.findOne(admin);
        if (admin1==null){
            model.addAttribute("msg","用户名或密码错误!");
        }else{
            admin1.setPassword("");
            session.setAttribute("admin",admin1);
            model.addAttribute("success","登录成功.");
        }
        return "admin/adminLogin :: msg-section";
    }

    @RequestMapping("/adminLogout.action")
    public String adminLogout(HttpSession session) {
        // 清除Session
        session.invalidate();
        // 重定向到登录页面的跳转方法
        return "redirect:/adminLogin";
    }
    @RequestMapping("/admin")
    public String admin(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if (null == admin) {
            return "/admin/adminLogin";
        }
        return "/admin/admin";
    }


}
