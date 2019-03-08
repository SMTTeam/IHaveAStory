package com.smtteam.smt.service;

import com.smtteam.smt.common.bean.ShowProjectUser;
import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.model.ProjectUser;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

    /**
     * 获取项目的参与列表
     * @param proId
     * @return
     */
    List<ShowProjectUser> findInviteList(Integer proId);

    /**
     * 删除项目协作的成员
     * @param proId
     * @param userId
     * @param askUserId
     * @return
     */
    boolean deleteInvite(Integer proId, Integer userId, Integer askUserId);

    /**
     * 修改项目协作成员的权限
     * @param proId
     * @param userId
     * @param askUserId
     * @param role
     * @return
     */
    boolean modifyInvite(Integer proId, Integer userId, Integer askUserId, Integer role) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException;
}
