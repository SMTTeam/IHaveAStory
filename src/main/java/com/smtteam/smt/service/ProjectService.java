package com.smtteam.smt.service;

import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.model.Project;

import java.util.List;

/**
 * 项目相关Service
 */
public interface ProjectService {
    /**
     * 创建项目
     * @param project
     * @return
     */
    Project createProject(Project project);

    /**
     * 查看我发布的项目
     * @param userId
     * @return
     */
    List<Project> findReleaseList(int userId);

    /**
     * 查看我参与的项目
     * @param userId
     * @return
     */
    List<Project> findAttendList(int userId);

    /**
     * 修改项目
     * @param proId
     * @param userId
     * @param name
     * @param description
     * @return
     */
    Project modifyProject(Integer proId, Integer userId, String name, String description) throws NoAccessException;

    /**
     * 根据ID查找项目
     * @param proId
     * @return
     */
    Project findById(Integer proId);

    /**
     * 删除项目
     * @param proId
     * @param userId
     * @return
     */
    boolean deleteProject(Integer proId, Integer userId);
}
