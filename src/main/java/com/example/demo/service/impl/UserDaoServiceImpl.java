package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.po.User;
import com.example.demo.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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

    @Override
    public User findByIdExPassword(Integer id) {
        User user = userDao.findByIdExPassword(id);
        user.setPassword("");
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void freezeAccount(Integer id, Integer duration) {
        if (duration==-1){
            duration=36500;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, duration);

        Date date = cal.getTime();
        System.out.println(date);
        userDao.freezeAccount(id,date);
    }

    @Override
    public Integer checkBlock(Integer uid, Integer bid) {
        return userDao.checkBlock(uid, bid);
    }

    @Override
    public Integer removeBlock(Integer uid, Integer bid) {
        return userDao.removeBlock(uid, bid);
    }

    @Override
    public Integer addBlock(Integer uid, Integer bid) {
        return userDao.addBlock(uid, bid);
    }

    @Override
    public void updateProfile(User user) {
        userDao.updateProfile(user);
    }

    @Override
    public List<User> USER_LIST(String keyWord) {
        return userDao.USER_LIST("%" + keyWord + "%");
    }
}
