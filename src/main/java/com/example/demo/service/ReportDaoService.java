package com.example.demo.service;

import com.example.demo.po.Admin;
import com.example.demo.po.Report;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReportDaoService {
    void addReport(Report report);
    void updateReport(Integer id, Integer operation);
    List<Report> listReports();
    List<Report> listArchived();
    List<Report> listSearch(String content);
    void deleteReport(Integer id);
}
