package com.smtteam.smt.service;

import com.smtteam.smt.model.Project;

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


}
