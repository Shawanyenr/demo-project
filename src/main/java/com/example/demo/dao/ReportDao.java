package com.example.demo.dao;

import com.example.demo.po.Report;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ReportDao {
    @Insert("insert into reports(fromId,ownerId,pid)values(#{fromId},#{ownerId},#{pid})")
    void addReport(Report report);
    @Update("update reports set operation=#{operation} where id=#{id}")
    void updateReport(Integer id, Integer operation);
//    @Select("select * from reports where operation = 0")
    List<Report> listReports();
//    @Select("select * from reports where operation != 0")
    List<Report> listArchived();
    List<Report> listSearch(String content);

    @Delete("delete from reports where id=#{id}")
    void deleteReport(Integer id);
}
