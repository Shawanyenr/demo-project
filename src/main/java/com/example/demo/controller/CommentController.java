package com.example.demo.controller;

import com.example.demo.ProductWebSocket;
import com.example.demo.dao.CommentDao;
import com.example.demo.po.Comment;
import com.example.demo.po.User;
import com.example.demo.service.CommentDaoService;
import com.example.demo.service.NotificationDaoService;
import com.example.demo.service.PostDaoService;
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
    @RequestMapping("/addComment")
    public String addComment(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        comment.setUid(user.getId());
        commentDaoService.addComment(comment);
        notificationDaoService.addNotification(postDaoService.onePost(comment.getPid(),null).getU_id(),comment.getContent(),comment.getPid(),user.getId());
        new ProductWebSocket().systemSendToUser(postDaoService.onePost(comment.getPid(),null).getU_username(), comment.getContent());
        if (comment.getParentId()!=0){
            notificationDaoService.addNotification(postDaoService.onePost(commentDaoService.selectCommentById(comment.getParentId()).getUid(),null).getU_id(),comment.getContent(),comment.getPid(),user.getId());
            new ProductWebSocket().systemSendToUser(postDaoService.onePost(commentDaoService.selectCommentById(comment.getParentId()).getUid(),null).getU_username(), comment.getContent());
        }
        return "redirect:/loadComment/"+comment.getPid();
    }

    @RequestMapping("/loadComment/{id}")
    public String loadCommentByPid(@PathVariable Integer id, Model model){
        List<Comment> comments = commentDaoService.selectCommentsByPid(id);
        model.addAttribute("comments", comments);
        return "post_detail :: commentSection";
    }
}
