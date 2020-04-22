package com.example.demo.controller;

import com.example.demo.po.User;
import com.example.demo.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String findOne(User loginInfo, HttpSession s,Model model) {
        System.out.println("登录信息:\n" + loginInfo);
        User user = userDaoService.findOne(loginInfo);
        System.out.println(user);
        if (user == null) {
            model.addAttribute("msg", "Wrong username or password!");
        } else if (user.getUserState() == 0) {
            model.addAttribute("msg", "Account suspended!");
        } else {
            user.setPassword("");
            s.setAttribute("user", user);
            model.addAttribute("success","Log in successfully.");
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
            model.addAttribute("msg", "Username is already taken!");
        } else {
            registerInfo.setAvatar("/user_avatar/default_user_avatar.jpg");
            Integer trySaveOne = userDaoService.saveUser(registerInfo);
            System.out.println("存储了" + trySaveOne + "个信息");
            if (trySaveOne == 1) {
                model.addAttribute("success", "Registration success!");
            } else {
                model.addAttribute("msg", "Registration failed!");
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
    @ResponseBody
    @RequestMapping("/addSub")
    public String addSub(HttpSession session, @RequestParam Integer sub_id) {
        User user = (User) session.getAttribute("user");
        String msg = "";
        if (null == user || null == sub_id) {
            msg = "error";
        } else {
            Integer check = userDaoService.addSubscription(user.getId(), sub_id);
            if (check == 1) {
                msg = "ok";
            } else msg = "error";
        }
        return msg;
    }

    @Transactional
    @ResponseBody
    @RequestMapping("/rmSub")
    public String rmSub(HttpSession session, @RequestParam Integer sub_id) {
        User user = (User) session.getAttribute("user");
        String msg = "";
        if (null == user || null == sub_id) {
            msg = "error";
        } else {
            Integer check = userDaoService.removeSubscription(user.getId(), sub_id);
            if (check == 1) {
                msg = "ok";
            } else msg = "error";
        }
        return msg;
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


}
