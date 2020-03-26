package com.example.demo.dao;

import com.example.demo.po.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PostDao {

    /*@Select("SELECT post.p_id as p_id,user.id as u_id, user.username as u_username, user.avatar as u_avatar,post.p_title,post.img_dir,post.p_like_count,post.p_fav_count,post.upload_time,post.p_last_edit_time " +
            "from user " +
            "inner join post " +
            "on user.id=post.u_id " +
            "order by p_id")*/
    public List<Post> findAll();

    /*@Select("select * from (SELECT post.p_id as p_id,user.id as u_id,user.username as u_username, user.avatar as u_avatar,post.p_title,post.img_dir,post.p_like,post.upload_time,post.p_last_edit_time " +
            "from user " +
            "inner join post " +
            "on user.id=post.u_id " +
            "order by p_id) as m_post " +
            "where " +
            "u_id = #{u_id}")*/
    public List<Post> findAllOfOneUser(Integer u_id);

    @Insert("insert into post(p_title,img_dir,upload_time,u_id)values(#{p_title},#{img_dir},#{upload_time},#{u_id})")
    public Integer saveOnePost(Post postInfo);

    @Select("select * from post order by id #{order} limit #{start},#{end}")
    public List<Post> loadSomePost(Integer start, Integer end, String order);

    @Delete("delete from post where p_id=#{p_id}")
    public Integer deletePostId(Integer p_id);

    @Update("update post set p_title=#{p_title} where p_id=#{p_id}")
    public Integer updatePostId(Integer p_id,String p_title);

    /*@Select("select * from (SELECT post.p_id as p_id,user.id as u_id,user.username as u_username, user.avatar as u_avatar,post.p_title,post.img_dir,post.p_like,post.upload_time,post.p_last_edit_time " +
            "from user " +
            "inner join post " +
            "on user.id=post.u_id " +
            "order by p_id) as m_post " +
            "where p_title like #{search_item}")*/
    public List<Post> searchResult(String search_item);

    @Select("select * from (SELECT \n" +
            "post.p_id as p_id, \n" +
            "user.id as u_id, \n" +
            "user.username as u_username, \n" +
            "user.avatar as u_avatar, \n" +
            "post.p_title, post.img_dir, post.p_like, post.upload_time, post.p_last_edit_time\n" +
            "from user\n" +
            "inner join post\n" +
            "on user.id=post.u_id\n" +
            "order by p_id) as m_post\n" +
            "where\n" +
            "p_id=#{p_id}")
    public Post onePost(Integer p_id);
}
