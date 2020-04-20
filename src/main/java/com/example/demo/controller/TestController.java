package com.example.demo.controller;

import com.example.demo.ProductWebSocket;
import com.example.demo.dao.PostDao;
import com.example.demo.po.Comment;
import com.example.demo.po.Message;
import com.example.demo.po.Post;
import com.example.demo.po.User;
import com.example.demo.service.CommentDaoService;
import com.example.demo.service.MessageDaoService;
import com.example.demo.service.PostDaoService;
import com.example.demo.service.UserDaoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
            }
        }
        List<Comment> comments = commentDaoService.selectCommentsByPid(id);
        model.addAttribute("comments", comments);
        System.out.println("comments: \n" + comments);
        return "post_detail";
    }


    @RequestMapping("/to_index")
    public String index() {
        return "test";
    }

    @RequestMapping("/tt")
    public String tt() {
        return "tt";
    }

    /*@RequestMapping("/test")
    public String toTestPage() {
        return "test";
    }*/

    @RequestMapping("/123")
    public String to123() {
        return "123";
    }

    @RequestMapping("/index")
    public String toIndex(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null == user) {
            List<Post> posts = postDaoService.findAll(null);
            model.addAttribute("posts", posts);
        } else {
            List<Post> posts  = postDaoService.findAll(user.getId());/*.findAll(user.getId());*/
            model.addAttribute("posts", posts);
        }
        return "index";
    }

    @RequestMapping("/subscription")
    public String toSubs(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null == user) {
            return "login";
        } else {
            List<Post> posts  = postDaoService.mySubs(user.getId(),user.getId());
            model.addAttribute("posts", posts);
        }
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
            model.addAttribute("one", one);
        } else {
            if (user.getId()==id) return "redirect:/home";
            Integer checkSub = userDaoService.checkSub(user.getId(),id);
            List<Post> posts  = postDaoService.findAllOfOneUser(user.getId(),id);
            model.addAttribute("posts", posts);
            model.addAttribute("one", one);
            model.addAttribute("subState", checkSub);
        }
        return "oneUser";
    }


    @RequestMapping("/message")
    public String ws(Model model, HttpSession session, @RequestParam(value = "sub_id") Integer id) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null == user || null == id){
            return "redirect:/login";
        }
        User one = userDaoService.findById(id);
        one.setPassword("");
        model.addAttribute("one", one);
        List<Message> messageList = messageDaoService.listMessage(user.getUsername(),one.getUsername(),one.getUsername(),user.getUsername());
        model.addAttribute("messageList", messageList);
        System.out.println("chat history: \n" + messageList);
        return "ws";
    }


    @ResponseBody
    @GetMapping("test")
    public String test(String userId, String message) throws Exception {
        if (userId == "" || userId == null) {
            return "发送用户id不能为空";
        }
        if (message == "" || message == null) {
            return "发送信息不能为空";
        }
        new ProductWebSocket().systemSendToUser(userId, message);
        return "发送成功！";
    }

    /*@GetMapping("testMessage")
    public void testM(String userId, String message) throws Exception {

        new ProductWebSocket().sendToUser();
    }*/



}
