package com.smtteam.smt.service.impl;

import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.dao.UserDao;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 类说明：用户具体实现
 * 创建者：Zeros
 * 创建时间：2019-01-01 21:27
 * 包名：com.smtteam.smt.service.impl
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    /**
     * 根据邮箱查找用户
     * @param email
     * @return
     */
    @Override
    public List<User> findByEmailLike(String email) {
        return userDao.findByEmailLike('%'+email+'%');
    }

    /**
     * 根据ID查找用户
     * @param userId
     * @return
     */
    @Override
    public User findById(Integer userId) {
        Optional<User> user = userDao.findById(userId);
        return user.orElse(null);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    @Transactional
    public User addUser(User user){
        return userDao.save(user);
    }

    /**
     * 根据email查找用户（一个email对应1个用户）
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }

    /**
     * 根据email和psw来查找用户
     * @param email
     * @param psw
     * @return
     */
    //验证身份登录时要把 邮箱验证的 status（验证完成）考虑进去，仅当status为verified状态时才查找成功
    @Override
    public User findByEmailAndPsw(String email , String psw){
        return userDao.findByEmailAndPsw(email,psw);
    }

    @Override
    public User findByEmailAndStatus( String email ,Integer status ){
        return userDao.findByEmailAndStatus( email, status);
    }
    @Override
    public int updateUserInfoByEmail(String useremail , String username){
        return userDao.updateUserInfoByEmail(username,useremail);
    }

    /**
     * 查找验证过的邮箱用户
     * @param email
     * @return
     */
    @Override
    public User findByEmailAndStatus(String email) {
        return userDao.findByEmailAndStatus(email, Constants.USEREMAIL_VERIFIED);
    }

    @Override
    public int updateUserPswByEmail(String useremail , String newpsw){
        return userDao.updateUserPswByEmail(newpsw, useremail);
    }
}
