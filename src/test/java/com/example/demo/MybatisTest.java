package com.example.demo;

import com.example.demo.po.User;
import com.example.demo.service.UserDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
public class MybatisTest {

    @Autowired
    private UserDaoService userDaoService;

    @Test
    public void findAll() {
        List<User> users = userDaoService.findAll();
        System.out.println(users);
    }

    @Test
    public void findOne(){
        User user = new User();
        user.setUsername("m78");
        user.setPassword("123456");
        User one = userDaoService.findOne(user);
        System.out.println(one);
    }

    @Test
    public void saveUser(){
        User user = new User();
        user.setName("吼吼");
        user.setUsername("m19");
        user.setPassword("123456");
        Integer check = userDaoService.saveUser(user);
        System.out.println(check);
    }

    @Test
    public void checkUsername(){
        User user = new User();
        user.setName("吼吼");
        user.setUsername("m19");
        user.setPassword("123456");
        Integer check = userDaoService.checkUsername(user.getUsername());
        System.out.println(check);
    }
}
