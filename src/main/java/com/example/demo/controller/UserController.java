package com.example.demo.controller;

import com.example.demo.po.User;
import com.example.demo.service.ReportDaoService;
import com.example.demo.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDaoService userDaoService;
    @Autowired
    private ReportDaoService reportDaoService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll() {
        List<User> users = userDaoService.findAll();
        return users;
    }

    @RequestMapping("/login.action")
    public String findOne(User loginInfo, HttpSession s,Model model) {
        System.out.println("登录信息:\n" + loginInfo);
        User user = userDaoService.findOne(loginInfo);
        System.out.println(user);
        if (user == null) {
            model.addAttribute("msg", "用户名或密码错误!");
        } else if (user.getFrozeUntil()!=null) {
            if (!(user.getFrozeUntil().before(new Date()))){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                model.addAttribute("msg", "账户已被冻结!\n解冻日期：" + formatter.format(user.getFrozeUntil()));
//            model.addAttribute("frozeUntil", user.getFrozeUntil());
            }else {
                user.setPassword("");
                s.setAttribute("user", user);
                model.addAttribute("success","登录成功.");
            }
        } else {
            user.setPassword("");
            s.setAttribute("user", user);
            model.addAttribute("success","登录成功.");
        }
        return "login :: msg-section";
    }

    @Transactional
    @RequestMapping("/register.action")
    public String saveOne(User registerInfo, Model model) {
        System.out.println("注册信息:\n" + registerInfo);
        Integer check = userDaoService.checkUsername(registerInfo.getUsername());
        System.out.println("重名个数: " + check);
        if (check != 0) {
            model.addAttribute("msg", "用户名已被占用!");
        } else {
            registerInfo.setAvatar("/user_avatar/default_user_avatar.jpg");
            Integer trySaveOne = userDaoService.saveUser(registerInfo);
            System.out.println("存储了" + trySaveOne + "个信息");
            if (trySaveOne == 1) {
                model.addAttribute("success", "注册成功!");
            } else {
                model.addAttribute("msg", "注册失败!");
            }
        }
        return "register::msg-section";
    }

    @RequestMapping("/logout.action")
    public String logout(HttpSession session) {
        // 清除Session
        session.invalidate();
        // 重定向到登录页面的跳转方法
        return "redirect:/login";
    }

    @Transactional
    @RequestMapping("/toggleSub")
    public String toggleSub(HttpSession session, @RequestParam Integer sub_id, Model model) {
        User user = (User) session.getAttribute("user");
        User one = userDaoService.findById(sub_id);
        Integer check;
        Integer state;
        if (null == user || null == sub_id) {
            return "error/404";
        } else {
            System.out.println(user.getId() + " toggleSubing " + sub_id);
            check = userDaoService.checkSub(user.getId(), sub_id);
            if (check == 1) {
                state= userDaoService.removeSubscription(user.getId(),sub_id);
            } else {
                state = userDaoService.addSubscription(user.getId(),sub_id);
            }

        }
        if (state>=1){
            check = userDaoService.checkSub(user.getId(), sub_id);
            model.addAttribute("subState",check);
            model.addAttribute("one", one);
            return "oneUser::subBtn";
        }else return "error/404";

    }

    @ResponseBody
    @RequestMapping("/report/{pid}")
    public String addReport(@PathVariable Integer pid,HttpSession session){
        User user = (User) session.getAttribute("user");
        if (pid==null||user==null){
            return "error";
        }
        reportDaoService.addReport(user.getId(),pid);
        return "ok";
    }

}
