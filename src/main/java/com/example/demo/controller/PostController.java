package com.example.demo.controller;

import com.example.demo.po.Post;
import com.example.demo.service.PostDaoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class PostController {
    @Autowired
    private PostDaoService postDaoService;

    @RequestMapping("/loadMore")
    @ResponseBody
    public PageInfo<Post> loadMore(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {

        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDaoService.findAll();
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println(page);
        if (start>=page.getPages()){
            return null;
        }
        return page;
    }

    @RequestMapping("/loadMine")
    @ResponseBody
    public PageInfo<Post> loadMine(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "u_id") Integer u_id) throws Exception {

        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDaoService.findAllOfOneUser(u_id);
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println(page);
        return page;
    }

    @Transactional
    @RequestMapping("/delete_post_id")
    @ResponseBody
    public String deletePostId(Integer p_id) {
        System.out.println("deleting: " + p_id);
        Integer check = postDaoService.deletePostId(p_id);
        if (check == 1) {
            return "OK";
        } else
            return "FAIL";
    }

    @RequestMapping("/update_post_id")
    @ResponseBody
    public String updatePostId(Integer p_id) {
        System.out.println("updating: " + p_id);
        Integer check = postDaoService.updatePostId(p_id);
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
        List<Post> cs = postDaoService.searchResult("%" + search_item + "%");
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println("searching: " + search_item);
        System.out.println(page);
        return page;
    }

    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public String testupload(MultipartFile upload, Model m) throws IOException {

        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        String path = "D:\\00webappresources\\user_upload\\post_img" + File.separator + filename;
        File destFile = new File(path);
        destFile.getParentFile().mkdirs();
        upload.transferTo(destFile);
        m.addAttribute("filename", "/post_img/" + filename);
        System.out.println(filename + "保存在" + destFile);
        return "/post_img/" + filename;
    }

    @RequestMapping("/uploadForm")
    @ResponseBody
    public String uploadForm(Post postInfo) {
        System.out.println(postInfo);
        postInfo.setUpload_time(new Date());
        Integer check = postDaoService.saveOnePost(postInfo);
        System.out.println(postInfo);
        String msg = null;
        if (check != 1) {
            msg = "fail";
        }else {
            msg = "success";
        }
        return msg;
    }
}
