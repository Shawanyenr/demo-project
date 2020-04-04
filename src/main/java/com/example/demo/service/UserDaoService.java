package com.example.demo.service;

import com.example.demo.po.User;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface UserDaoService {
    List<User> findAll();

    User findOne(User user);

    Integer saveUser(User user);

    Integer checkUsername(String username);

    Integer addSubscription(Integer own_id, Integer sub_id);

    Integer removeSubscription(Integer own_id, Integer sub_id);
}
