package com.example.demo.dao;

import com.example.demo.po.Report;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Component
public interface ReportDao {
    @Insert("insert into reports(fromId,pid)values(#{fromId},#{pid})")
    void addReport(Integer fromId, Integer pid);

    @Update("update reports set duration=#{duration}, archived=1 where pid=#{pid}")
    void updateReport(Integer pid, Integer duration);

    List<Report> listReports(@RequestParam String content, @RequestParam Integer archived);

    @Delete("delete from reports where pid=#{pid}")
    Integer deleteReport(Integer pid);

    @Select("select count(*) from reports where pid=#{pid}")
    Integer countReportByPid(Integer pid);

}
