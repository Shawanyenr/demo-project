package com.example.demo;

import com.example.demo.po.Post;
import com.example.demo.service.PostDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
public class MybatisTestPost {

    @Autowired
    private PostDaoService postDaoService;


    @Test
    public void findAll1() {
        List<Post> all = postDaoService.findAll();
        System.out.println(all);
        System.out.println(all.size());
    }

    @Test
    public void findOneUser() {
        List<Post> all = postDaoService.findAllOfOneUser(1);
        System.out.println(all);
    }
    @Test
    public void searchResult() {
        List<Post> all = postDaoService.searchResult("%哈哈%");
        System.out.println(all);
        System.out.println(all.size());
    }

    @Test
    public void findOne() {
        System.out.println(postDaoService.onePost(15));
    }

    @Test
    public void updateOne() {
        System.out.println(postDaoService.updatePostId(16,new Date(),"一个游艇"));
    }


}
