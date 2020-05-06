package com.example.demo;

import com.example.demo.po.Post;
import com.example.demo.service.PostDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostTest {

    @Autowired
    private PostDaoService postDaoService;


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

    @Test
    public void onePost() {
        System.out.println(postDaoService.onePost(19,null));
    }



}
