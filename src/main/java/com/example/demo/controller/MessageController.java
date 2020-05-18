package com.example.demo.controller;

import com.example.demo.dao.NotificationDao;
import com.example.demo.po.Comment;
import com.example.demo.po.User;
import com.example.demo.service.CommentDaoService;
import com.example.demo.service.LikeNotificationDaoService;
import com.example.demo.service.MessageDaoService;
import com.example.demo.service.NotificationDaoService;
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

    @Autowired
    private NotificationDaoService notificationDaoService;

    @Autowired
    private LikeNotificationDaoService likeNotificationDaoService;

    @RequestMapping("/message")
    public String messagePage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<User> userList = messageDaoService.chatListUser(user.getUsername());
        model.addAttribute("userList", userList);
        model.addAttribute("uncheckedNotify", notificationDaoService.notifyNum(user.getId()));
        model.addAttribute("lastNotifyDate", notificationDaoService.lastNotifyTime(user.getId()));
        model.addAttribute("likeUncheckedNum", likeNotificationDaoService.likeNotifyNum(user.getId()));
        model.addAttribute("likeLastDate", likeNotificationDaoService.lastLikeNotifyTime(user.getId()));
        return "message";
    }

    @RequestMapping("/reloadMessage")
    public String reloadMessage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<User> userList = messageDaoService.chatListUser(user.getUsername());
        model.addAttribute("userList", userList);
        model.addAttribute("uncheckedNotify", notificationDaoService.notifyNum(user.getId()));
        model.addAttribute("lastNotifyDate", notificationDaoService.lastNotifyTime(user.getId()));
        model.addAttribute("likeUncheckedNum", likeNotificationDaoService.likeNotifyNum(user.getId()));
        model.addAttribute("likeLastDate", likeNotificationDaoService.lastLikeNotifyTime(user.getId()));
        return "message :: messageList";
    }

    @RequestMapping("/reloadLike")
    public String reloadLike(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("likeNotifyList", likeNotificationDaoService.listLikeNotification(user.getId()));
        likeNotificationDaoService.emptyLikeUnchecked(user.getId());
        return "likeNotify :: messageList";
    }

    @RequestMapping("/reloadComment")
    public String reloadComment(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("commentNotiList", notificationDaoService.listNotification(user.getId()));
        notificationDaoService.emptyUnchecked(user.getId());
        return "commentNotify :: messageList";
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

    @RequestMapping("/message/comment")
    public String commentNotify(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("commentNotiList", notificationDaoService.listNotification(user.getId()));
        notificationDaoService.emptyUnchecked(user.getId());
        return "commentNotify";
    }

    @RequestMapping("/message/like")
    public String likeNotify(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("likeNotifyList", likeNotificationDaoService.listLikeNotification(user.getId()));
        likeNotificationDaoService.emptyLikeUnchecked(user.getId());
        return "likeNotify";
    }

    @RequestMapping("/reloadUnchecked")
    public String reloadUnchecked(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("allUnchecked", notificationDaoService.allUnchecked(user.getId()));
        model.addAttribute("allUnreadMessage", messageDaoService.allUnread(user.getUsername()));
        return "include :: notificationSection";
    }

    @RequestMapping("/reloadUncheckedMenu")
    public String reloadUncheckedMenu(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("allUnchecked", notificationDaoService.allUnchecked(user.getId()));
        model.addAttribute("allUnreadMessage", messageDaoService.allUnread(user.getUsername()));
        return "include :: flexMenu";
    }

    @RequestMapping("/reddot")
    public String reddot(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        Integer allUnreadNote = messageDaoService.allUnreadNote(user.getId(),user.getUsername());
        System.out.println("allUnreadNote="+allUnreadNote);
        model.addAttribute("reddot", allUnreadNote);
        return "include :: reddot";
    }
}
