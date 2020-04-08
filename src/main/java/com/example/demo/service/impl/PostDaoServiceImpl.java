package com.example.demo.service.impl;

import com.example.demo.dao.PostDao;
import com.example.demo.po.Post;
import com.example.demo.service.PostDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostDaoServiceImpl implements PostDaoService {
    @Autowired
    private PostDao postDao;

    @Override
    public List<Post> findAll(Integer u_id) {
        return postDao.findAll(u_id);
    }

    @Override
    public List<Post> findAllOfOneUser(Integer u_id,Integer uid) {
        return postDao.findAllOfOneUser(u_id,uid);
    }

    @Override
    public List<Post> mySubs(Integer u_id, Integer uid) {
        return postDao.mySubs(u_id,uid);
    }

    @Override
    public Integer saveOnePost(Post postInfo) {
        return postDao.saveOnePost(postInfo);
    }

    @Override
    public List<Post> loadSomePost(Integer start, Integer end, String order) {
        return postDao.loadSomePost(start, end, order);
    }

    @Override
    public Integer deletePostId(Integer p_id) {
        return postDao.deletePostId(p_id);
    }

    @Override
    public Integer updatePostId(Integer p_id, Date p_last_edit_time, String p_title) {
        return postDao.updatePostId(p_id, p_last_edit_time, p_title);
    }

    @Override
    public List<Post> searchResult(String search_item, Integer u_id) {
        return postDao.searchResult(search_item,u_id);
    }

    @Override
    public Post onePost(Integer p_id, Integer u_id) {
        return postDao.onePost(p_id,u_id);
    }

    @Override
    public List<Post> findAllLike(Integer u_id) {
        return postDao.findAllLike(u_id);
    }

    @Override
    public List<Post> findAllFav(Integer u_id) {
        return postDao.findAllFav(u_id);
    }
}
