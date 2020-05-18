package com.example.demo;

import com.example.demo.po.Post;
import com.example.demo.po.User;
import com.example.demo.service.PostDaoService;
import com.example.demo.service.UserDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @Autowired
    private UserDaoService userDaoService;

    @Test
    public void findAll() {
        List<User> users = userDaoService.findAll();
        System.out.println(users);
    }

    @Test
    public void findOne() {
        User user = new User();
        user.setUsername("m78");
        user.setPassword("123456");
        User one = userDaoService.findOne(user);
        System.out.println(one);
    }

   /* @Test
    public void saveUser() {
        User user = new User();
        user.setName("吼吼");
        user.setUsername("m19");
        user.setPassword("123456");
        Integer check = userDaoService.saveUser(user);
        System.out.println(check);
    }*/

    @Test
    public void checkUsername() {
        User user = new User();
        user.setName("吼吼");
        user.setUsername("m19");
        user.setPassword("123456");
        Integer check = userDaoService.checkUsername(user.getUsername());
        System.out.println(check);
    }

    @Test
    public void addSub() {
        Integer check = userDaoService.addSubscription(1,7);
        System.out.println(check);
    }

    @Test
    public void rmSub() {
        Integer check = userDaoService.removeSubscription(1,2);
        System.out.println(check);
    }

    @Test
    public void checkSub() {
        System.out.println(userDaoService.checkSub(5,7));
    }

    @Test
    public void freezeAccount(){
        userDaoService.freezeAccount(2,-1);
    }

    @Test
    public void updateProfile(){
        User user = new User();
        user.setId(5);
        user.setName("嘿嘿");
        user.setPassword("123456");
        userDaoService.updateProfile(user);
        System.out.println(userDaoService.findById(5));
    }

    @Test
    public void oneUser(){
        System.out.println(userDaoService.findById(5));
    }
}
