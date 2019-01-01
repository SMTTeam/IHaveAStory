package com.smtteam.smt.service;

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
}
