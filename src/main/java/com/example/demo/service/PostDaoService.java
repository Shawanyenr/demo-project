package com.example.demo.service;

import com.example.demo.po.Post;

import java.util.Date;
import java.util.List;

public interface PostDaoService {
    public List<Post> findAll();

    public List<Post> findAllOfOneUser(Integer u_id);

    public Integer saveOnePost(Post postInfo);

    public List<Post> loadSomePost(Integer start, Integer end, String order);

    public Integer deletePostId(Integer p_id);

    public Integer updatePostId(Integer p_id, Date p_last_edit_time, String p_title);

    public List<Post> searchResult(String search_item);

    public Post onePost(Integer p_id, Integer u_id);

}
