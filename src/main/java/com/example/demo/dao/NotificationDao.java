package com.example.demo.dao;

import com.example.demo.po.Message;
import com.example.demo.po.Notification;
import com.example.demo.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface NotificationDao {
    @Insert("insert into commentnote (uid,content,pid,fromid)values(#{uid},#{content},#{pid},#{fromid})")
    void addNotification(Integer uid, String content, Integer pid, Integer fromid);

//    @Select("select * from commentnote where uid=#{uid}")
    List<Notification> listNotification(Integer uid);

//    @Select("select count(*) from notification where uid=#{uid} and checked=0")
    Integer notifyNum(@Param("uid") Integer uid);

    @Select("select max(time) from commentnote where uid=#{uid}")
    Date lastNotifyTime(Integer uid);

    @Update("update commentnote set isread=1 where uid=#{uid}")
    void emptyUnchecked(Integer uid);

    @Select("select count(*) from (select id from notification where uid=#{uid} union all select id from likenotify where uid=#{uid}) temp")
    Integer allUnchecked(@Param("uid") Integer uid);
}
