package com.example.demo;


import com.example.demo.dao.UserDao;
import com.example.demo.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
public class MybatisTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        System.out.println(users);
    }

    @Test
    public void findOne(){
        User user = new User();
        user.setUsername("m78");
        user.setPassword("123456");
        User one = userDao.findOne(user);
        System.out.println(one);
    }

    @Test
    public void saveUser(){
        User user = new User();
        user.setName("吼吼");
        user.setUsername("m19");
        user.setPassword("123456");
        Integer check = userDao.saveUser(user);
        System.out.println(check);
    }

    @Test
    public void checkUsername(){
        User user = new User();
        user.setName("吼吼");
        user.setUsername("m19");
        user.setPassword("123456");
        Integer check = userDao.checkUsername(user.getUsername());
        System.out.println(check);
    }
}
