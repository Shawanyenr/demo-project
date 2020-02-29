package com.example.demo;

import com.example.demo.po.Post;
import com.example.demo.service.PostDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
public class MybatisTestPost {

    @Autowired
    private PostDaoService postDaoService;


    @Test
    public void findAll1() {
        List<Post> all = postDaoService.findAll();
        System.out.println(all);
    }

    @Test
    public void findOne() {
        System.out.println(postDaoService.onePost(15));
    }


}
