package com.example.demo.controller;

import com.example.demo.ProductWebSocket;
import com.example.demo.po.Admin;
import com.example.demo.po.Report;
import com.example.demo.service.AdminDaoService;
import com.example.demo.service.PostDaoService;
import com.example.demo.service.ReportDaoService;
import com.example.demo.service.UserDaoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private PostDaoService postDaoService;

    @RequestMapping("/login")
    public String adminLogin(HttpSession session) {
        session.removeAttribute("user");
        return "/admin/adminLogin";
    }

    @RequestMapping("/login.action")
    public String adminLoginInfo(Admin admin, Model model, HttpSession session) {
        Admin admin1 = adminDaoService.findOne(admin);
        if (admin1 == null) {
            model.addAttribute("msg", "用户名或密码错误!");
        } else {
            admin1.setPassword("");
            session.setAttribute("admin", admin1);
            model.addAttribute("success", "登录成功.");
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

    @RequestMapping
    public String admin(HttpSession session, @RequestParam(value = "page", defaultValue = "1") int start, @RequestParam(value = "limit", defaultValue = "5") int size, Model model, RedirectAttributes attributes, @RequestParam(value = "s", defaultValue = "") String content, @RequestParam(value = "a", defaultValue = "0") Integer archived) {
        /*Admin admin = (Admin) session.getAttribute("admin");
        System.out.println(admin);
        if (null == admin) {
            attributes.addFlashAttribute("msg", "请先登录");
            return "redirect:/admin/login";
        }*/
        System.out.println("content: " + content + " archived: " + archived);
        List<Report> reportList = reportDaoService.listReports(content, archived);
        System.out.println(reportList);
        PageHelper.startPage(start, size);
        PageInfo<Report> reportPage = new PageInfo<>(reportList);
        model.addAttribute("reportList", reportPage);
        model.addAttribute("s", content);
        model.addAttribute("a", archived);
        return "/admin/admin";
    }
    /*@RequestMapping("/archived")
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
    }*/


    @Transactional
    @RequestMapping("/freeze/{uid}/{pid}/{duration}")
    @ResponseBody
    public String freezeAccount(@PathVariable Integer uid, @PathVariable Integer pid, @PathVariable Integer duration) {
        System.out.println("/admin/freeze uid=" + uid + " pid=" + pid + " duration=" + duration);
        Integer integer = null;
        if (uid == null || duration == null) {
            return "操作失败，id或期限为空";
        } else if (duration == 0) {
            integer = reportDaoService.deleteReport(pid);
            userDaoService.freezeAccount(uid, duration);
            System.out.println("integer=" + integer);
        } else {
            userDaoService.freezeAccount(uid, duration);
            reportDaoService.updateReport(uid, duration);
            integer = postDaoService.deletePostId(pid);
            System.out.println("integer=" + integer);
        }
        if (integer >= 1) {
            return "ok";
        }
        return "操作失败";
    }


    @ResponseBody
    @GetMapping("/sendNotification")
    public String test(String userId, String message) throws Exception {
        if (userId.equals("")) {
            return "发送用户id不能为空";
        }
        if (message.equals("")) {
            return "发送信息不能为空";
        }
        new ProductWebSocket().systemSendToUser(userId, message);
        return "发送成功！";
    }
}
