package com.example.demo.dao;

import com.example.demo.po.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface PostDao {

    List<Post> findAll(Integer u_id);

    List<Post> findAllOfOneUser(@Param("u_id") Integer u_id,@Param("uid") Integer uid);

    List<Post> mySubs(@Param("u_id") Integer u_id,@Param("u_id") Integer uid);

    @Insert("insert into post(p_title,img_dir,upload_time,u_id)values(#{p_title},#{img_dir},#{upload_time},#{u_id})")
    Integer saveOnePost(Post postInfo);

    @Select("select * from post order by id #{order} limit #{start},#{end}")
    List<Post> loadSomePost(Integer start, Integer end, String order);

    @Delete("delete from post where p_id=#{p_id}")
    Integer deletePostId(Integer p_id);

    @Update("update post set p_title=#{p_title}, p_last_edit_time=#{p_last_edit_time} where p_id=#{p_id}")
    Integer updatePostId(Integer p_id, Date p_last_edit_time, String p_title);

    List<Post> searchResult(@Param("search_item") String search_item, @Param("u_id") Integer u_id);

    Post onePost(@Param("p_id") Integer p_id, @Param("u_id") Integer u_id);

    List<Post> findAllLike(@Param("u_id") Integer u_id);

    List<Post> findAllFav(@Param("u_id") Integer u_id);

    @Select("select p_like_flag from postflag where p_id=#{p_id} and u_id=#{u_id}")
    Integer checkLike(Integer p_id, Integer u_id);

    @Update("update postflag set p_like_flag=1 where p_id=#{p_id} and u_id=#{u_id}")
    void addLike(Integer p_id, Integer u_id);

    @Update("update postflag set p_like_flag=0 where p_id=#{p_id} and u_id=#{u_id}")
    void removeLike(Integer p_id, Integer u_id);

    @Select("select p_fav_flag from postflag where p_id=#{p_id} and u_id=#{u_id}")
    Integer checkFav(Integer p_id, Integer u_id);

    @Update("update postflag set p_fav_flag=1 where p_id=#{p_id} and u_id=#{u_id}")
    void addFav(Integer p_id, Integer u_id);

    @Update("update postflag set p_fav_flag=0 where p_id=#{p_id} and u_id=#{u_id}")
    void removeFav(Integer p_id, Integer u_id);

    @Insert("insert into postflag (p_id,u_id,p_like_flag,p_fav_flag) values(#{p_id},#{u_id},#{like},#{fav})")
    void addPostFlag(Integer p_id, Integer u_id, Integer like, Integer fav);

    @Select("select * from post where p_id=#{id}")
    Post findById(Integer id);

    @Update("update post set publicity=#{publicity} where p_id=#{pid}")
    void setPublicityByPid(Integer pid, Integer publicity);
}
