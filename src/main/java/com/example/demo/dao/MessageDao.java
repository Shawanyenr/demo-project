package com.example.demo.dao;

import com.example.demo.po.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MessageDao {
    @Insert("insert into Message (sender,receiver,content)values(#{sender},#{receiver},#{content})")
    void addMessage(Integer sender, Integer receiver, String content);
    @Select("select from Message where sender=#{sender} and receiver=#{receiver} or sender=#{sender1} and receiver=#{receiver1}")
    List<Message> listMessage(Integer sender, Integer receiver, Integer sender1, Integer receiver1);
}
