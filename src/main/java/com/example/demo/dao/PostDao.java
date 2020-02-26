package com.example.demo.dao;

import com.example.demo.po.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PostDao {

    @Select("select * from post")
    public List<Post> findAll();

    @Select("select * from post where u_id=#{u_id}")
    public List<Post> findAllOfOneUser(Integer u_id);

    @Insert("insert into post(p_title,u_username,img_dir,upload_time)values(#{p_title},#{u_username},#{img_dir},#{upload_time})")
    public Integer saveOnePost(Post postInfo);

    @Select("select * from post order by id #{order} limit #{start},#{end}")
    public List<Post> loadSomePost(Integer start,Integer end,String order);

    @Delete("delete from post where p_id=#{p_id}")
    public Integer deletePostId(Integer p_id);

    @Update("update post set p_title=#{p_title},img_dir=#{img_dir} where p_id=#{p_id}")
    public Integer updatePostId(Integer p_id);

    @Select("select * from post where p_title like #{search_item}")
    public List<Post> searchResult(String search_item);

    @Select("select * from post where p_id=#{p_id}")
    public Post onePost(Integer p_id);
}
