package com.example.demo.service.impl;

import com.example.demo.dao.NotificationDao;
import com.example.demo.po.Notification;
import com.example.demo.service.NotificationDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationDaoServiceImpl implements NotificationDaoService {

    @Autowired
    private NotificationDao notificationDao;

    @Override
    public void addNotification(Integer uid, String content,Integer pid, Integer fromid) {
        notificationDao.addNotification(uid, content,pid,fromid);
    }

    @Override
    public List<Notification> listNotification(Integer uid) {
        return notificationDao.listNotification(uid);
    }

    @Override
    public Integer notifyNum(Integer uid) {
        return notificationDao.notifyNum(uid);
    }

    @Override
    public Date lastNotifyTime(Integer uid) {
        return notificationDao.lastNotifyTime(uid);
    }

    @Override
    public void emptyUnchecked(Integer uid) {
        notificationDao.emptyUnchecked(uid);
    }

    @Override
    public Integer allUnchecked(Integer uid) {
        return notificationDao.allUnchecked(uid);
    }
}
