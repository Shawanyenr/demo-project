package com.example.demo.controller;

import com.example.demo.po.Admin;
import com.example.demo.po.Report;
import com.example.demo.service.AdminDaoService;
import com.example.demo.service.ReportDaoService;
import com.example.demo.service.UserDaoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDaoService adminDaoService;
    @Autowired
    private ReportDaoService reportDaoService;
    @Autowired
    private UserDaoService userDaoService;

    @RequestMapping("/login")
    public String adminLogin(){
        return "/admin/adminLogin";
    }

    @RequestMapping("/login.action")
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

    @RequestMapping("/logout.action")
    public String adminLogout(HttpSession session) {
        // 清除Session
        session.invalidate();
        // 重定向到登录页面的跳转方法
        return "redirect:/admin/login";
    }
    @RequestMapping("")
    public String admin(HttpSession session, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size, Model model, RedirectAttributes attributes, @RequestParam(value = "s", defaultValue = "") String content){
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if (null == admin) {
            attributes.addFlashAttribute("msg","请先登录");
            return "redirect:/admin/login";
        }
        List<Report> reportList = reportDaoService.listReports();
        PageHelper.startPage(start,size,"time desc");
        PageInfo<Report> reportPage = new PageInfo<>(reportList);
        model.addAttribute("reportList", reportPage);
        return "/admin/admin";
    }
    @RequestMapping("/archived")
    public String adminArchived(HttpSession session, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size, Model model, RedirectAttributes attributes){
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if (null == admin) {
            attributes.addFlashAttribute("msg","请先登录");
            return "redirect:/admin/login";
        }
        List<Report> reportList = reportDaoService.listArchived();
        PageHelper.startPage(start,size,"time desc");
        PageInfo<Report> reportPage = new PageInfo<>(reportList);
        model.addAttribute("reportList", reportPage);
        return "/admin/admin";
    }

    @RequestMapping("/s")
    public String adminSearch(HttpSession session, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size, Model model, RedirectAttributes attributes, @RequestParam(value = "search_item", defaultValue = "") String content){
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if (null == admin) {
            attributes.addFlashAttribute("msg","请先登录");
            return "redirect:/admin/login";
        }
        List<Report> reportList = reportDaoService.listSearch("%"+content+"%");
        PageHelper.startPage(start,size,"time desc");
        PageInfo<Report> reportPage = new PageInfo<>(reportList);
        model.addAttribute("reportList", reportPage);
        return "/admin/admin";
    }

    @RequestMapping("/freeze")
    public String freezeAccount(HttpSession session, @RequestParam(value = "id") Integer id, @RequestParam(value = "duration") Integer duration, Model model, RedirectAttributes attributes){
        Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if (null == admin) {
            attributes.addFlashAttribute("msg","请先登录");
            return "redirect:/admin/login";
        }
        if (id==null||duration==null){
            model.addAttribute("error","操作失败，id或期限为空");
        }else {
            userDaoService.freezeAccount(id, duration);
            model.addAttribute("success", "操作成功");
        }
        return "admin/admin :: message";
    }

}
