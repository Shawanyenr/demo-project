package com.example.demo.service;

import com.example.demo.po.Message;
import com.example.demo.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MessageDaoService {
    void addMessage(String sender, String receiver, String content);

    List<Message> listMessage(String sender, String receiver, String sender1, String receiver1);

    List<User> chatListUser(String username);

    Message lastMessage(String one, String another);

    Integer allUnread(String username);

    void setReadByUid(String username, String uUsername);

    Integer allUnreadNote(Integer uid, String username);

}
