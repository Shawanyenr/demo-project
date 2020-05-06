package com.example.demo.service.impl;

import com.example.demo.dao.MessageDao;
import com.example.demo.po.Message;
import com.example.demo.po.User;
import com.example.demo.service.MessageDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageDaoServiceImpl implements MessageDaoService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void addMessage(String sender, String receiver, String content) {
        messageDao.addMessage(sender, receiver, content);
    }

    @Override
    public List<Message> listMessage(String sender, String receiver, String sender1, String receiver1) {
        return messageDao.listMessage(sender, receiver, sender1, receiver1);
    }

    @Override
    public List<User> chatListUser(String username) {
        return messageDao.chatListUser(username);
    }
}
