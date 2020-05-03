package com.example.demo.dao;

import com.example.demo.po.Report;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Component
public interface ReportDao {
    @Insert("insert into reports(fromId,ownerId,pid)values(#{fromId},#{ownerId},#{pid})")
    void addReport(Report report);

    @Update("update reports set operation=#{operation}, operateTime=#{operateTime} where ownerId=#{ownerId}")
    void updateReport(Integer ownerId, Integer operation, Date operateTime);

    //    @Select("select * from reports where operation = 0")
    List<Report> listReports(@RequestParam String content, @RequestParam Integer archived);

    //    @Select("select * from reports where operation != 0")
    List<Report> listArchived();

    List<Report> listSearch(String content);

    @Delete("delete from reports where pid=#{id}")
    Integer deleteReport(Integer id);
/*
    @Update("update reports set ")
    void freeze(Integer ownerId, Integer duration);*/

    /*@Delete("delete from report where fromId=#{fromId} and pid=#{pid}")
    void  deleteReportByFromId(Integer fromId, Integer pid);*/
}
