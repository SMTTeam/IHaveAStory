package com.smtteam.smt.service.impl;

import com.smtteam.smt.dao.UserMapper;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类说明：
 * 创建者：Zeros
 * 创建时间：2018-12-28 21:04
 * 包名：com.smtteam.smt.service.impl
 */

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }
}
