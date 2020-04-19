package com.example.demo.dao;

import com.example.demo.po.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository("commentDao")
@Mapper
public interface CommentDao {
    @Insert("insert into comment(pid, uid, parentId, content)values(#{pid},#{uid},#{parentId},#{content})")
    Integer addComment(Integer pid, Integer uid, Integer parentId, String content);

    Comment selectCommentById(Integer id);
}
