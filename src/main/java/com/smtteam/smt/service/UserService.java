package com.smtteam.smt.service;

import com.smtteam.smt.model.User;

import java.util.List;

/**
 * 用户相关
 */
public interface UserService {

    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    List<User> findByEmailLike(String email);

    /**
     * 根据ID查找用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 新增用户
     * @param user
     * @return
     */
    User addUser(User user);
}
