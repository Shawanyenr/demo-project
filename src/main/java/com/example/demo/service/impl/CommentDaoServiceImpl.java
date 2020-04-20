package com.example.demo.service.impl;

import com.example.demo.dao.CommentDao;
import com.example.demo.po.Comment;
import com.example.demo.service.CommentDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDaoServiceImpl implements CommentDaoService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public Integer addComment(Integer pid, Integer uid, Integer parentId, String content) {
        return commentDao.addComment(pid, uid, parentId, content);
    }

    @Override
    public Comment selectCommentById(Integer id) {
        return commentDao.selectCommentById(id);
    }

    @Override
    public List<Comment> selectChildCommentsById(Integer parentId) {
        return commentDao.selectChildCommentsById(parentId);
    }

    @Override
    public List<Comment> selectCommentsByPid(Integer pid) {
        return commentDao.selectCommentsByPid(pid);
    }
}
