package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.po.User;
import com.example.demo.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoServiceImpl implements UserDaoService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findOne(User user) {
        return userDao.findOne(user);
    }

    @Override
    public Integer saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public Integer checkUsername(String username) {
        return userDao.checkUsername(username);
    }
}
