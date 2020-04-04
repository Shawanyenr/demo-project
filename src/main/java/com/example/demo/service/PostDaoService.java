package com.example.demo.service;

import com.example.demo.po.Post;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PostDaoService {
    public List<Post> findAll(Integer u_id);

    public List<Post> findAllOfOneUser(Integer u_id,Integer uid);

    public List<Post> mySubs(Integer u_id,Integer uid);

    public Integer saveOnePost(Post postInfo);

    public List<Post> loadSomePost(Integer start, Integer end, String order);

    public Integer deletePostId(Integer p_id);

    public Integer updatePostId(Integer p_id, Date p_last_edit_time, String p_title);

    public List<Post> searchResult(String search_item, Integer u_id);

    public Post onePost(Integer p_id, Integer u_id);

}
