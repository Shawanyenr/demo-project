package com.example.demo.service;

import com.example.demo.po.User;

import java.util.List;

public interface UserDaoService {
    public List<User> findAll();

    public User findOne(User user);

    public Integer saveUser(User user);

    public Integer checkUsername(String username);
}
