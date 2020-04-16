package com.example.demo.service.impl;

import com.example.demo.dao.MessageDao;
import com.example.demo.po.Message;
import com.example.demo.service.MessageDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageDaoServiceImpl implements MessageDaoService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void addMessage(Integer sender, Integer receiver, String content) {
        messageDao.addMessage(sender, receiver, content);
    }

    @Override
    public List<Message> listMessage(Integer sender, Integer receiver, Integer sender1, Integer receiver1) {
        return messageDao.listMessage(sender, receiver, sender1, receiver1);
    }
}
