package com.example.demo.service.impl;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.UserDao;
import com.example.demo.po.Admin;
import com.example.demo.po.User;
import com.example.demo.service.AdminDaoService;
import com.example.demo.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDaoServiceImpl implements AdminDaoService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin findOne(Admin admin) {
        return adminDao.findOne(admin);
    }


}
