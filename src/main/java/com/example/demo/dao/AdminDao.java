package com.example.demo.dao;

import com.example.demo.po.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AdminDao {
    @Select("select * from admin where username=#{username} and password=#{password}")
    Admin findOne(Admin admin);
}
