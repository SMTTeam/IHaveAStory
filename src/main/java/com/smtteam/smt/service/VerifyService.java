package com.smtteam.smt.service;

import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.model.User;


/**
 * 验证邮箱
 */
public interface VerifyService {
    /**
     * 发送验证邮件
     * @param email
     * @param psw
     * @param username
     * @return
     */
    User sendVerifyEmail(String email, String psw, String username) throws ExistException;

    /**
     * 点击链接，接受验证
     * @param code
     * @return
     */
    User acceptVerifyEmail(String code) throws NoAccessException;
}
