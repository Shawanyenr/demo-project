package com.example.demo.controller;

import com.example.demo.ProductWebSocket;
import com.example.demo.po.Post;
import com.example.demo.po.User;
import com.example.demo.service.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class PostController {
    @Autowired
    private PostDaoService postDaoService;
    @Autowired
    private NotificationDaoService notificationDaoService;

    @Autowired
    private LikeNotificationDaoService likeNotificationDaoService;
    @Autowired
    private MessageDaoService messageDaoService;

    @Autowired
    private UserDaoService userDaoService;
    /*@RequestMapping("/loadMore")
    @ResponseBody
    public PageInfo<Post> loadMore(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        System.out.println("请求第" + start + "页");
        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDaoService.findAll(null);
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println(page);
        if (start > page.getPages()) {
            return null;
        }
        return page;
    }

    @RequestMapping("/loadMine")
    @ResponseBody
    public PageInfo<Post> loadMine(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "u_id") Integer u_id) throws Exception {
        System.out.println(u_id + "请求第" + start + "页");
        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDaoService.findAllOfOneUser(null, u_id);
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println(page);
        if (start > page.getPages()) {
            return null;
        }
        return page;
    }*/

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

    @Transactional
    @RequestMapping("/update_post_id")
    @ResponseBody
    public String updatePostId(Integer p_id, String p_title) {
        System.out.println("updating: " + p_id + "set title = " + p_title);
        Integer check = postDaoService.updatePostId(p_id, new Date(), p_title);
        if (check == 1) {
            return "OK";
        } else
            return "FAIL";
    }

    @RequestMapping("/search_post")
    public String searchPost(@RequestParam(value = "search_item") String search_item, Model model, HttpSession session) {
        System.out.println("search_post: " + search_item);
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (null == user) {
            List<Post> posts = postDaoService.searchResult("%" + search_item + "%", null);
            model.addAttribute("posts", posts);
        } else {
            List<Post> posts = postDaoService.searchResult("%" + search_item + "%", user.getId());
            model.addAttribute("posts", posts);
            model.addAttribute("allUnchecked", notificationDaoService.allUnchecked(user.getId()));
            model.addAttribute("allUnreadMessage", messageDaoService.allUnread(user.getUsername()));
        }
        model.addAttribute("userList",userDaoService.USER_LIST(search_item));
        return "search_result";
    }

    @RequestMapping("/search_result")
    @ResponseBody
    public PageInfo<Post> searchResult(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "search_item") String search_item) {
        PageHelper.startPage(start, size, "p_id desc");
        List<Post> cs = postDaoService.searchResult("%" + search_item + "%", null);
        PageInfo<Post> page = new PageInfo<>(cs);
        System.out.println("searching: " + search_item);
        System.out.println(page);
        if (start > page.getPages()) {
            return null;
        }
        return page;
    }

    @Transactional
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

    @Transactional
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
        } else {
            msg = "success";
        }
        return msg;
    }

    @Transactional
    @RequestMapping("/toggleLike")
    @ResponseBody
    public String toggleLike(@RequestParam("id") Integer pid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        Integer check;
        if (null == user || null == pid) {
            return "FAIL";
        } else {
            System.out.println("user: " + user.getId() + " pid: " + pid);
            check = postDaoService.checkLike(pid, user.getId());
            System.out.println("check: "+ check);
            User postOwner=userDaoService.findById(postDaoService.onePost(pid,null).getU_id());
            if (null == check) {
                postDaoService.addPostFlag(pid, user.getId(), 1, 0);
                if (postOwner.getId()!=user.getId()){
                    likeNotificationDaoService.addLikeNotification(postDaoService.onePost(pid,null).getU_id(),pid,user.getId());
                }
            } else if (check == 0) {
                postDaoService.addLike(pid, user.getId());
                if (user.getId()!=postOwner.getId()){
                    likeNotificationDaoService.addLikeNotification(postOwner.getId(),pid,user.getId());
                    new ProductWebSocket().systemSendToUser(postOwner.getUsername(), "@"+user.getUsername()+"给你的帖子点了赞");
                }
            } else {
                postDaoService.removeLike(pid, user.getId());
            }
        }
        return "OK";
    }

    @Transactional
    @RequestMapping("/toggleFav")
    @ResponseBody
    public String toggleFav(@RequestParam("id") Integer pid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        Integer check;
        if (null == user || null == pid) {
            return "FAIL";
        } else {
            System.out.println("user: " + user.getId() + " pid: " + pid);
            check = postDaoService.checkFav(pid, user.getId());
            System.out.println("check: "+ check);
            if (null == check) {
                postDaoService.addPostFlag(pid, user.getId(), 0, 1);

            } else if (check == 0) {
                postDaoService.addFav(pid, user.getId());

            } else {
                postDaoService.removeFav(pid, user.getId());

            }
        }
        return "OK";
    }
}
