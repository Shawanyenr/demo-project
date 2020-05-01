package com.example.demo.service.impl;

import com.example.demo.dao.ReportDao;
import com.example.demo.po.Report;
import com.example.demo.service.ReportDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportDaoServiceImpl implements ReportDaoService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public void addReport(Report report) {
        reportDao.addReport(report);
    }

    @Override
    public void updateReport(Integer id, Integer operation) {
        reportDao.updateReport(id, operation);
    }

    @Override
    public List<Report> listReports(String content, Integer archived) {
        if (content.equals("")){
            return reportDao.listReports(null, archived);
        }
        return reportDao.listReports(content, archived);
    }

    @Override
    public List<Report> listArchived() {
        return reportDao.listArchived();
    }

    @Override
    public List<Report> listSearch(String content) {
        return reportDao.listSearch(content);
    }

    @Override
    public void deleteReport(Integer id) {
        reportDao.deleteReport(id);
    }
}
