package com.example.demo.dao;

import com.example.demo.po.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface CommentDao {
    @Insert("insert into comment(pid, uid, parentId, content)values(#{pid},#{uid},#{parentId},#{content})")
    Integer addComment(Comment comment);

    Comment selectCommentById(Integer id);

    List<Comment> selectChildCommentsById(Integer parentId);

    List<Comment> selectCommentsByPid(Integer pid);
}
