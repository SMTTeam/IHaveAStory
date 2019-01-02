package com.smtteam.smt.service;

import com.smtteam.smt.common.exception.ExistException;
import com.smtteam.smt.model.ProjectUser;

import java.lang.reflect.InvocationTargetException;

/**
 * 邀请相关
 */
public interface InviteService {
    ProjectUser createInvitation(Integer userId, String email, Integer proId, String proName, Integer role) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ExistException;
}
