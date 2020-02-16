package com.example.demo.controller;

import com.example.demo.dao.PostDao;
import com.example.demo.po.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
public class UploadController {

    @Autowired
    private PostDao postDao;

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
        Integer check = postDao.saveOnePost(postInfo);
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
