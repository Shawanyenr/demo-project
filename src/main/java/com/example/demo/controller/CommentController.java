package com.example.demo.controller;

import com.example.demo.ProductWebSocket;
import com.example.demo.dao.CommentDao;
import com.example.demo.po.Comment;
import com.example.demo.po.Post;
import com.example.demo.po.User;
import com.example.demo.service.CommentDaoService;
import com.example.demo.service.NotificationDaoService;
import com.example.demo.service.PostDaoService;
import com.example.demo.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentDaoService commentDaoService;
    @Autowired
    private NotificationDaoService notificationDaoService;
    @Autowired
    private PostDaoService postDaoService;
    @Autowired
    private UserDaoService userDaoService;

    @RequestMapping("/addComment")
    public String addComment(Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        comment.setUid(user.getId());
        commentDaoService.addComment(comment);
        User postOwner = userDaoService.findById(postDaoService.onePost(comment.getPid(), null).getU_id());
        if (user.getId()!=postOwner.getId()){
            notificationDaoService.addNotification(postOwner.getId(), comment.getContent(), comment.getPid(), user.getId());
            new ProductWebSocket().systemSendToUser(postOwner.getUsername(), "@" + user.getUsername() + " 评论了你: " + comment.getContent());
        }
        if (comment.getParentId() != 0) {
            User parentCommentUser = userDaoService.findById(commentDaoService.selectCommentById(comment.getParentId()).getUid());
            if (user.getId() != parentCommentUser.getId()) {
                notificationDaoService.addNotification(parentCommentUser.getId(), comment.getContent(), comment.getPid(), user.getId());
                new ProductWebSocket().systemSendToUser(parentCommentUser.getUsername(), "@" + user.getUsername() + " 评论了你: " + comment.getContent());
            }
        }
        return "redirect:/loadComment/" + comment.getPid();
    }

    @RequestMapping("/loadComment/{id}")
    public String loadCommentByPid(@PathVariable Integer id, Model model) {
        List<Comment> comments = commentDaoService.selectCommentsByPid(id);
        model.addAttribute("comments", comments);
        return "post_detail :: commentSection";
    }
}
