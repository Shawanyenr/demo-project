package com.example.demo.service;

import com.example.demo.po.Notification;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface NotificationDaoService {
    void addNotification(Integer uid, String content, Integer pid, Integer fromid);

    List<Notification> listNotification(Integer uid);

    Integer notifyNum(Integer uid);

    Date lastNotifyTime(Integer uid);

    void emptyUnchecked(Integer uid);

}
