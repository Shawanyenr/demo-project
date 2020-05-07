package com.example.demo.service;

import com.example.demo.po.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;
import java.util.List;

public interface UserDaoService {
    List<User> findAll();

    User findOne(User user);

    Integer saveUser(User user);

    Integer checkUsername(String username);

    Integer addSubscription(Integer own_id, Integer sub_id);

    Integer removeSubscription(Integer own_id, Integer sub_id);

    Integer checkSub(Integer own_id, Integer sub_id);

    User findById(Integer id);

    User findByIdExPassword(Integer id);

    User findByUsername(String username);

    void freezeAccount(Integer id, Integer duration);

    Integer checkBlock(Integer uid, Integer bid);

    Integer removeBlock(Integer uid, Integer bid);

    Integer addBlock(Integer uid, Integer bid);

    void updateProfile(User user);

    List<User> USER_LIST(String keyWord);

}
