package com.example.demo;

import com.example.demo.dao.CommentDao;
import com.example.demo.po.Comment;
import com.example.demo.po.Post;
import com.example.demo.service.PostDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestComment {

    @Autowired
    private PostDaoService postDaoService;

    @Autowired
    private CommentDao commentDao;

    @Test
    public void selectCommentById(){
        Comment comment = commentDao.selectCommentById(2);
        System.out.println(comment);
    }

    @Test
    public void selectChildCommentsById(){
        List<Comment> childCommentsById = commentDao.selectChildCommentsById(1);
        for (Comment comment: childCommentsById) {
            System.out.println(comment);
        }
//        System.out.println(childCommentsById);
    }

    @Test
    public void selectCommentsByPid(){
        List<Comment> commentsByPid = commentDao.selectCommentsByPid(16);
        for (Comment comment: commentsByPid) {
            System.out.println(comment);
        }
        System.out.println(commentsByPid);
    }

    @Test
    public void findAll1() {
        List<Post> all = postDaoService.findAll(null);
        System.out.println(all);
        System.out.println(all.size());
    }

    @Test
    public void findOneUser() {
        List<Post> all = postDaoService.findAllOfOneUser(7,1);
        System.out.println(all);
    }

    @Test
    public void findAllLike() {
        List<Post> all = postDaoService.findAllLike(1);
        System.out.println(all);
    }
    @Test
    public void searchResult() {
        List<Post> all = postDaoService.searchResult("%游艇%",1);
        System.out.println(all);
        System.out.println(all.size());
    }

    @Test
    public void findOne() {
        System.out.println(postDaoService.onePost(32,5));
    }

    @Test
    public void updateOne() {
        System.out.println(postDaoService.updatePostId(16,new Date(),"一个游艇"));
    }

    @Test
    public void mySubs() {
        System.out.println(postDaoService.mySubs(1,1));
    }

    @Test
    public void checkPostFlag() {
        System.out.println(postDaoService.checkLike(49,5));
    }



}
