package com.example.demo;

import com.example.demo.service.PostDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoApplication.class)
public class MybatisTestPost {

    @Autowired
    private PostDaoService postDaoService;


    @Test
    public void findAll1() {

        System.out.println(postDaoService.findAll());
    }

    @Test
    public void findOne() {
        System.out.println(postDaoService.onePost(15));
    }


}
