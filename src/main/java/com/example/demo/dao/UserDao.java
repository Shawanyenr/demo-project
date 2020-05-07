package com.example.demo.dao;

import com.example.demo.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface UserDao {

    List<User> findAll();

    @Select("select * from user where username=#{username} and password=#{password}")
    User findOne(User user);

    @Insert("insert into user(name,username,password,avatar)values(#{name},#{username},#{password},#{avatar})")
    Integer saveUser(User user);

    @Select("select count(*) from user where username=#{username}")
    Integer checkUsername(String username);

    @Insert("insert into subscription(own_id,sub_id)values(#{own_id},#{sub_id})")
    Integer addSubscription(Integer own_id, Integer sub_id);

    @Delete("delete from subscription where own_id=#{own_id} and sub_id=#{sub_id}")
    Integer removeSubscription(Integer own_id, Integer sub_id);

    @Select("select count(*) from subscription where own_id=#{own_id} and sub_id=#{sub_id}")
    Integer checkSub(Integer own_id, Integer sub_id);

    @Select("select count(*) from blocklist where uid=#{uid} and bid=#{bid}")
    Integer checkBlock(Integer uid, Integer bid);

    @Delete("delete from blocklist where uid=#{uid} and bid=#{bid}")
    Integer removeBlock(Integer uid, Integer bid);

    @Insert("insert into blocklist(uid,bid)values(#{uid},#{bid})")
    Integer addBlock(Integer uid, Integer bid);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    @Select("select * from user where id = #{id}")
    User findByIdExPassword(Integer id);

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Update("update user set frozeUntil=#{date} where id=#{id}")
    void freezeAccount(Integer id, Date date);

    void updateProfile(User user);
}
