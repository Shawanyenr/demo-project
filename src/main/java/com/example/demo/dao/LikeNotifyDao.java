package com.example.demo.dao;

import com.example.demo.po.LikeNotify;
import com.example.demo.po.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface LikeNotifyDao {
    @Insert("insert into likenotify (uid,pid,fromid)values(#{uid},#{pid},#{fromid})")
    void addLikeNotification(Integer uid, Integer pid, Integer fromid);

    List<LikeNotify> listLikeNotification(Integer uid);

    @Select("select count(*) from likenotify where uid=#{uid} and isread=0")
    Integer likeNotifyNum(Integer uid);

    @Select("select max(time) from likenotify where uid=#{uid}")
    Date lastLikeNotifyTime(Integer uid);

    @Update("update likenotify set isread=1 where uid=#{uid}")
    void emptyLikeUnchecked(Integer uid);
}
