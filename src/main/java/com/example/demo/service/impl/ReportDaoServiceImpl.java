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
    public void addReport(Integer fromId, Integer pid) {
        reportDao.addReport(fromId, pid);
    }

    @Override
    public void updateReport(Integer pid, Integer duration) {
        reportDao.updateReport(pid, duration);
    }

    @Override
    public List<Report> listReports(String content, Integer archived) {
        if (content.equals("")) {
            return reportDao.listReports(null, archived);
        }
        return reportDao.listReports("%" + content + "%", archived);
    }

    @Override
    public Integer deleteReport(Integer pid) {
        return reportDao.deleteReport(pid);
    }

    @Override
    public Integer countReportByPid(Integer pid) {
        return reportDao.countReportByPid(pid);
    }
}
