package com.example.demo.controller;

import com.example.demo.po.Comment;
import com.example.demo.po.Message;
import com.example.demo.po.Post;
import com.example.demo.po.User;
import com.example.demo.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private PostDaoService postDaoService;

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private MessageDaoService messageDaoService;

    @Autowired
    private CommentDaoService commentDaoService;
    @Autowired
    private NotificationDaoService notificationDaoService;

    @Autowired
    private LikeNotificationDaoService likeNotificationDaoService;
    /*@RequestMapping("/posts")
    @ResponseBody
    public PageInfo<Post> posts(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        System.out.println("请求第" + start + "页");
        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDaoService.findAll(null);
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println(page);
        if (start > page.getPages()) {
            return null;
        }
        return page;
    }*/

    @RequestMapping("/posts/{id}")
    public String posts_detail(@PathVariable Integer id, HttpSession session, Model model) {
        System.out.println("请求post_id:" + id);
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null == user) {
            Post post = postDaoService.onePost(id, null);
            if (null == post) {
                model.addAttribute("msg", "The resource you requested does not exist.");
                return "error/404";
            } else {
                System.out.println(post);
                model.addAttribute("post", post);
            }
        } else {
            Post post = postDaoService.onePost(id, user.getId());
            if (null == post) {
                model.addAttribute("msg", "The resource you requested does not exist.");
                return "error/404";
            } else {
                System.out.println(post);
                model.addAttribute("post", post);
                model.addAttribute("blocked", userDaoService.checkBlock(post.getU_id(), user.getId()));

                model.addAttribute("allUnchecked", notificationDaoService.allUnchecked(user.getId()));
                model.addAttribute("allUnreadMessage", messageDaoService.allUnread(user.getUsername()));
                model.addAttribute("reddot", messageDaoService.allUnreadNote(user.getId(), user.getUsername()));
            }
        }
        List<Comment> comments = commentDaoService.selectCommentsByPid(id);
        model.addAttribute("comments", comments);
        System.out.println("comments: \n" + comments);
        return "post_detail";
    }

    @RequestMapping("/index")
    public String toIndex(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null == user) {
//            List<Post> posts = postDaoService.findAll(null);
//            model.addAttribute("posts", posts);
        } else {
//            List<Post> posts  = postDaoService.findAll(user.getId());/*.findAll(user.getId());*/
//            model.addAttribute("posts", posts);
            model.addAttribute("allUnchecked", notificationDaoService.allUnchecked(user.getId()));
            model.addAttribute("allUnreadMessage", messageDaoService.allUnread(user.getUsername()));
            model.addAttribute("reddot", messageDaoService.allUnreadNote(user.getId(), user.getUsername()));
        }
        return "index";
    }

    @RequestMapping("/subscription")
    public String toSubs(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);


        model.addAttribute("allUnchecked", notificationDaoService.allUnchecked(user.getId()));
        model.addAttribute("allUnreadMessage", messageDaoService.allUnread(user.getUsername()));
        model.addAttribute("reddot", messageDaoService.allUnreadNote(user.getId(), user.getUsername()));
        return "subscription";
    }

    @RequestMapping("/user/{id}")
    public String toUser(Model model, HttpSession session, @PathVariable Integer id) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        User one = userDaoService.findById(id);
        one.setPassword("");
        if (null == user) {
            List<Post> allOfOneUser = postDaoService.findAllOfOneUser(null, id);
            model.addAttribute("posts", allOfOneUser);
            System.out.println("allOfOneUser: " + allOfOneUser);
            model.addAttribute("one", one);
        } else {
            if (user.getId() == id) return "redirect:/home";
            Integer checkSub = userDaoService.checkSub(user.getId(), id);
            List<Post> posts = postDaoService.findAllOfOneUser(user.getId(), id);
            Integer checkBlock = userDaoService.checkBlock(user.getId(), id);
            model.addAttribute("posts", posts);
            model.addAttribute("one", one);
            model.addAttribute("subState", checkSub);
            model.addAttribute("blocked", checkBlock);
            model.addAttribute("allUnreadMessage", messageDaoService.allUnread(user.getUsername()));
            model.addAttribute("reddot", messageDaoService.allUnreadNote(user.getId(), user.getUsername()));
        }
        return "oneUser";
    }

    @RequestMapping("/message/{id}")
    public String ws(Model model, HttpSession session, @PathVariable Integer id) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null == user || null == id) {
            return "redirect:/login";
        }
        User one = userDaoService.findById(id);
        one.setPassword("");
        model.addAttribute("one", one);
        List<Message> messageList = messageDaoService.listMessage(user.getUsername(), one.getUsername(), one.getUsername(), user.getUsername());
        model.addAttribute("messageList", messageList);
        System.out.println("chat history: \n" + messageList);
        model.addAttribute("blocked", userDaoService.checkBlock(id, user.getId()));
        return "ws";
    }


    /*@GetMapping("testMessage")
    public void testM(String userId, String message) throws Exception {

        new ProductWebSocket().sendToUser();
    }*/

    @RequestMapping("/infinite")
    public String infinite() {
        return "infinite";
    }

    @RequestMapping("/posts")
    public String loadMore(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size, Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        Integer uid = null;
        if (user != null) uid = user.getId();
        System.out.println("请求第" + start + "页");
        PageHelper.startPage(start, size);
        List<Post> cs = postDaoService.findAll(uid);
        PageInfo<Post> pageInfo = new PageInfo<>(cs);
        List<Post> data = new ArrayList<>(pageInfo.getList());
//        System.out.println(data);
        model.addAttribute("posts", data);
        return "include::genaralPosts";
    }

    @RequestMapping("/posts/subs")
    public String subPosts(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size, Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        if (user == null) return null;
        PageHelper.startPage(start, size);
        List<Post> cs = postDaoService.mySubs(user.getId(), user.getId());
        PageInfo<Post> pageInfo = new PageInfo<>(cs);
        List<Post> data = new ArrayList<>(pageInfo.getList());
        model.addAttribute("posts", data);
        return "include::genaralPosts";
    }


}
