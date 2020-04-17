package com.example.demo.service;

import com.example.demo.po.Message;

import java.util.List;

public interface MessageDaoService {
    void addMessage(String sender, String receiver, String content);

    List<Message> listMessage(String sender, String receiver, String sender1, String receiver1);
}
