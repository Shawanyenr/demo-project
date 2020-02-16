package com.example.demo.dao;

import com.example.demo.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    public List<User> findAll();

    @Select("select name,userState from user where username=#{username} and password=#{password}")
    public User findOne(User user);

    @Insert("insert into user(name,username,password)values(#{name},#{username},#{password})")
    public Integer saveUser(User user);

    @Select("select count(*) from user where username=#{username}")
    public Integer checkUsername(String username);

}
