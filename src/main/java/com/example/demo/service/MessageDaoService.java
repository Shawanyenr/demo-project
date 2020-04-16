package com.example.demo.service;

import com.example.demo.po.Message;

import java.util.List;

public interface MessageDaoService {
    void addMessage(Integer sender, Integer receiver, String content);

    List<Message> listMessage(Integer sender, Integer receiver, Integer sender1, Integer receiver1);
}
