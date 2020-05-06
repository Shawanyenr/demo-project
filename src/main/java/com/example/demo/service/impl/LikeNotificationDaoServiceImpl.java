package com.example.demo.service.impl;

import com.example.demo.dao.LikeNotifyDao;
import com.example.demo.po.LikeNotify;
import com.example.demo.po.Notification;
import com.example.demo.service.LikeNotificationDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LikeNotificationDaoServiceImpl implements LikeNotificationDaoService {

    @Autowired
    private LikeNotifyDao likeNotifyDao;

    @Override
    public void addLikeNotification(Integer uid, Integer pid, Integer fromid) {
        likeNotifyDao.addLikeNotification(uid, pid, fromid);
    }

    @Override
    public List<LikeNotify> listLikeNotification(Integer uid) {
        return likeNotifyDao.listLikeNotification(uid);
    }

    @Override
    public Integer likeNotifyNum(Integer uid) {
        return likeNotifyDao.likeNotifyNum(uid);
    }

    @Override
    public Date lastLikeNotifyTime(Integer uid) {
        return likeNotifyDao.lastLikeNotifyTime(uid);
    }

    @Override
    public void emptyLikeUnchecked(Integer uid) {
        likeNotifyDao.emptyLikeUnchecked(uid);
    }
}
