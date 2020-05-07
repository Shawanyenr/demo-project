package com.example.demo.dao;

import com.example.demo.po.Message;
import com.example.demo.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageDao {
    @Insert("insert into Message (sender,receiver,content)values(#{sender},#{receiver},#{content})")
    void addMessage(String sender, String receiver, String content);

    @Select("select * from Message where sender=#{sender} and receiver=#{receiver} or sender=#{sender1} and receiver=#{receiver1}")
    List<Message> listMessage(String sender, String receiver, String sender1, String receiver1);

    List<User> chatListUser(@Param("username") String username);

    Message lastMessage(@Param("one") String one, @Param("another") String another);

    @Select("select count(*) from message where receiver=#{username} and checked=0")
    Integer allUnread(String username);

    @Update("update message set checked=1 where sender=#{uUsername} and receiver=#{username}")
    void setReadByUid(String username, String uUsername);

    Integer allUnreadNote(@Param("uid") Integer uid, @Param("username")String username);
}
