package com.smtteam.smt.service.impl;

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
        User result = userDao.save(user);
        return result;
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
    @Override
    public User findByEmailAndPsw(String email , String psw){
        return userDao.findByEmailAndPsw(email,psw);
    }
}
