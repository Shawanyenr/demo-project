package com.example.demo.service;

import com.example.demo.po.Admin;
import com.example.demo.po.Report;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReportDaoService {
    void addReport(Integer fromId, Integer pid);

    void updateReport(Integer pid, Integer duration);

    List<Report> listReports(String content, Integer archived);

    Integer deleteReport(Integer pid);
}
