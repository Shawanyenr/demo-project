package com.example.demo.service;

import com.example.demo.po.Comment;

import java.util.List;

public interface CommentDaoService {
    Integer addComment(Integer pid, Integer uid, Integer parentId, String content);

    Comment selectCommentById(Integer id);

    List<Comment> selectChildCommentsById(Integer parentId);

    List<Comment> selectCommentsByPid(Integer pid);
}
