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

    @Override
    public Integer addSubscription(Integer own_id, Integer sub_id) {
        return userDao.addSubscription(own_id,sub_id);
    }

    @Override
    public Integer removeSubscription(Integer own_id, Integer sub_id) {
        return userDao.removeSubscription(own_id,sub_id);
    }

    @Override
    public Integer checkSub(Integer own_id, Integer sub_id) {
        return userDao.checkSub(own_id,sub_id);
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }
}
