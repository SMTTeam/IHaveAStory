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

    /**
     * 根据email查找用户（一个email对应1个用户）
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 根据email和psw来查找用户
     * @param email
     * @param psw
     * @return
     */
    User findByEmailAndPsw( String email , String psw);

    /**
     * 根据email 和 status 查找用户
     * @param email
     * @param status
     * @return
     */
    User findByEmailAndStatus( String email ,Integer status );
    /**
     * 更新个人信息，主要更新username字段
     * @param useremail
     * @param username
     * @return
     */
    int updateUserInfoByEmail(String useremail , String username);

    /**
     * 查找验证过的邮箱用户
     * @param email
     * @return
     */
    User findByEmailAndStatus(String email);

    /**
     * 根据useremail 更新个人信息的 密码字段
     * @param useremail
     * @param newpsw
     * @return
     */
    int updateUserPswByEmail(String useremail , String newpsw);
}
