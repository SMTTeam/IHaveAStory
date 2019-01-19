package com.smtteam.smt.service;

import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.model.ProjectUser;

import java.lang.reflect.InvocationTargetException;

/**
 * 邀请相关
 */
public interface InviteService {
    /**
     * 邀请成员
     * @param userId
     * @param email
     * @param proId
     * @param proName
     * @param role
     * @return
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws ExistException
     */
    ProjectUser createInvitation(Integer userId, String email, Integer proId, String proName, Integer role) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ExistException;

    /**
     * 接受邀请
     * @param code
     * @return
     */
    ProjectUser acceptInvitation(String code) throws NoAccessException;

    /**
     * 查看用户对项目的权限
     * @param proId
     * @param userId
     * @return
     */
    ProjectUser findProjectUser(Integer proId, Integer userId);
}
