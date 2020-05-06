package com.example.demo.service;

import com.example.demo.po.LikeNotify;
import com.example.demo.po.Notification;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface LikeNotificationDaoService {
    void addLikeNotification(Integer uid, Integer pid, Integer fromid);

    List<LikeNotify> listLikeNotification(Integer uid);

    Integer likeNotifyNum(Integer uid);

    Date lastLikeNotifyTime(Integer uid);

    void emptyLikeUnchecked(Integer uid);

}
