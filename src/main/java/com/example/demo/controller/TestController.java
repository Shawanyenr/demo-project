package com.example.demo.controller;

import com.example.demo.dao.PostDao;
import com.example.demo.po.Post;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private PostDao postDao;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/loadMore")
    @ResponseBody
    public PageInfo<Post> loadMore(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {

        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDao.findAll();
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println(page);
        return page;
    }

    @RequestMapping("/loadMine")
    @ResponseBody
    public PageInfo<Post> loadMine(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "u_username") String u_username) throws Exception {

        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDao.findAllOfOneUser(u_username);
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println(page);
        return page;
    }

    @Transactional
    @RequestMapping("/delete_post_id")
    @ResponseBody
    public String deletePostId(Integer p_id) {
        System.out.println("deleting: " + p_id);
        Integer check = postDao.deletePostId(p_id);
        if (check == 1) {
            return "OK";
        } else
            return "FAIL";
    }

    @RequestMapping("/update_post_id")
    @ResponseBody
    public String updatePostId(Integer p_id) {
        System.out.println("updating: " + p_id);
        Integer check = postDao.updatePostId(p_id);
        if (check == 1) {
            return "OK";
        } else
            return "FAIL";
    }

    @RequestMapping("/search_post")
    public String searchPost(@RequestParam(value = "search_item") String search_item, Model model) {
        System.out.println("search_post: " + search_item);
        model.addAttribute("search_item", search_item);
        return "search_result";
    }

    @RequestMapping("/search_result")
    @ResponseBody
    public PageInfo<Post> searchResult(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "search_item") String search_item) {
        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDao.searchResult("%" + search_item + "%");
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println("searching: " + search_item);
        System.out.println(page);
        return page;
    }

    @RequestMapping("/home_page")
    public String home_page() {
        return "home_page";
    }

    @RequestMapping("/to_index")
    public String index() {
        return "test";
    }

}
