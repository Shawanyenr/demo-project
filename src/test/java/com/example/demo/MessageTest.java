package com.example.demo;

import com.example.demo.po.Message;
import com.example.demo.po.Notification;
import com.example.demo.po.Report;
import com.example.demo.po.User;
import com.example.demo.service.MessageDaoService;
import com.example.demo.service.NotificationDaoService;
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

    @Autowired
    private NotificationDaoService notificationDaoService;

    @Test
    public void chatListUser(){
        List<User> listUsers = messageDaoService.chatListUser("pipi");
        for (User user : listUsers) {
            System.out.println(user);
        }
        System.out.println(listUsers.size());
    }

    @Test
    public void lastMessage(){
        Message message = messageDaoService.lastMessage("pipi", "m78");
        System.out.println(message);
    }

    @Test
    public void checked(){
        Integer checked = notificationDaoService.notifyNum(5);
        System.out.println(checked);
    }

    @Test
    public void listNotification(){
        List<Notification> nlist = notificationDaoService.listNotification(5);
        System.out.println(nlist);
    }

}
