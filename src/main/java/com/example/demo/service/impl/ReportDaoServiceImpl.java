package com.example.demo.service.impl;

import com.example.demo.dao.ReportDao;
import com.example.demo.po.Report;
import com.example.demo.service.ReportDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        reportDao.updateReport(id, operation, date);
    }

    @Override
    public List<Report> listReports(String content, Integer archived) {
        if (content.equals("")) {
            return reportDao.listReports(null, archived);
        }
        return reportDao.listReports("%" + content + "%", archived);
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
    public Integer deleteReport(Integer id) {
        return reportDao.deleteReport(id);
    }
}
