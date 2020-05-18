package com.example.demo.controller;

import com.example.demo.ProductWebSocket;
import com.example.demo.po.User;
import com.example.demo.service.ReportDaoService;
import com.example.demo.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public String findOne(User loginInfo, HttpSession s, Model model) {
        System.out.println("登录信息:\n" + loginInfo);
        User user = userDaoService.findOne(loginInfo);
        System.out.println(user);
        if (user == null) {
            model.addAttribute("msg", "用户名或密码错误!");
        } else if (user.getFrozeUntil() != null) {
            if (!(user.getFrozeUntil().before(new Date()))) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                model.addAttribute("msg", "账户已被冻结!\n解冻日期：" + formatter.format(user.getFrozeUntil()));
//            model.addAttribute("frozeUntil", user.getFrozeUntil());
            } else {
                user.setPassword("");
                s.setAttribute("user", user);
                model.addAttribute("success", "登录成功.");
            }
        } else {
            user.setPassword("");
            s.setAttribute("user", user);
            model.addAttribute("success", "登录成功.");
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
                state = userDaoService.removeSubscription(user.getId(), sub_id);
            } else {
                state = userDaoService.addSubscription(user.getId(), sub_id);
            }
        }
        if (state >= 1) {
            check = userDaoService.checkSub(user.getId(), sub_id);
            model.addAttribute("subState", check);
            model.addAttribute("one", one);
            return "oneUser::subBtn";
        } else return "error/404";
    }

    @Transactional
    @RequestMapping("/toggleBlock")
    public String toggleBlock(HttpSession session, @RequestParam(value = "blockId",defaultValue = "") Integer blockId, Model model){
        User user = (User) session.getAttribute("user");
        User one = userDaoService.findById(blockId);
        Integer checkBlock;
        Integer state;
        if (null == user || null == blockId) {
            return "error/404";
        } else {
            System.out.println(user.getId() + " toggleBlock " + blockId);
            checkBlock = userDaoService.checkBlock(user.getId(), blockId);
            if (checkBlock == 1){
                state = userDaoService.removeBlock(user.getId(),blockId);
            }else {
                state = userDaoService.addBlock(user.getId(),blockId);
            }
            new ProductWebSocket().systemSendToUser(userDaoService.findById(blockId).getUsername(),"ub");
        }
        if (state>=1){
            checkBlock=userDaoService.checkBlock(user.getId(),blockId);
            model.addAttribute("blocked", checkBlock);
            model.addAttribute("one", one);
            return "oneUser::blockBtn";
        }else return "error/404";
    }

    @Transactional
    @ResponseBody
    @PostMapping("/report")
    public String addReport(@RequestParam(value = "pid", defaultValue = "") Integer pid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (pid == null || user == null) {
            return "举报失败";
        }
        Integer byPidAndFromId = reportDaoService.countReportByPidAndFromId(pid, user.getId());
        if (byPidAndFromId!=0){
            return "请勿重复举报";
        }
        Integer countReportByPid = reportDaoService.countReportByPid(pid);
        if (countReportByPid != 0) {
            return "ok";
        }
        reportDaoService.addReport(user.getId(), pid);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/updateProfile")
    public String updateProfile(User user1, HttpSession session){
        System.out.println("updateProfile: "+user1);
        User user = (User) session.getAttribute("user");
        if (user==null||user1==null) return "error";
//        if (user.equals(user1)) return "ok";
        userDaoService.updateProfile(user1);
        return "ok";
    }


    @Transactional
    @RequestMapping(value = "/newAvatar", method = RequestMethod.POST)
    @ResponseBody
    public String newAvatar(MultipartFile upload, Model m) throws IOException {

        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        String path = "D:\\00webappresources\\user_upload\\user_avatar" + File.separator + filename;
        File destFile = new File(path);
        destFile.getParentFile().mkdirs();
        upload.transferTo(destFile);
        m.addAttribute("filename", "/user_avatar/" + filename);
        System.out.println(filename + "保存在" + destFile);
        return "/user_avatar/" + filename;
    }

    @RequestMapping("/user/suspended")
    public String suspended(HttpSession session){
        session.invalidate();
        return "login";
    }


}
