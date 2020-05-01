package com.example.demo;

import com.example.demo.dao.CommentDao;
import com.example.demo.po.Comment;
import com.example.demo.po.Post;
import com.example.demo.po.Report;
import com.example.demo.service.PostDaoService;
import com.example.demo.service.ReportDaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportComment {

    @Autowired
    private ReportDaoService reportDaoService;

    @Test
    public void listReports(){
        List<Report> listReports = reportDaoService.listReports(null,1);
        System.out.println(listReports);
    }

    @Test
    public void listArchived(){
        List<Report> listArchived = reportDaoService.listArchived();
        System.out.println(listArchived);
    }

    @Test
    public void listSearch(){
        List<Report> listSearch = reportDaoService.listSearch("%2020%");
        System.out.println(listSearch);
    }


}
