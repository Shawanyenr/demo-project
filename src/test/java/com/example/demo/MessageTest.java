package com.example.demo;

import com.example.demo.po.Message;
import com.example.demo.po.Report;
import com.example.demo.po.User;
import com.example.demo.service.MessageDaoService;
import com.example.demo.service.ReportDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageTest {

    @Autowired
    private MessageDaoService messageDaoService;

    @Test
    public void chatListUser(){
        List<User> listUsers = messageDaoService.chatListUser("m78");
        for (User user : listUsers) {
            System.out.println(user);
        }
        System.out.println(listUsers.size());
    }

    @Test
    public void lastMessage(){
        Message message = messageDaoService.lastMessage("pipi", "2016b11015");
        System.out.println(message);
    }

}
