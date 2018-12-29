package com.smtteam.smt.service.impl;

import com.smtteam.smt.dao.ProjectDao;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类说明：
 * 创建者：Zeros
 * 创建时间：2018-12-29 15:57
 * 包名：com.smtteam.smt.service.impl
 */

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    /**
     * 创建项目
     * @param project
     * @return
     */
    @Override
    public Project createProject(Project project) {
        Project result = projectDao.save(project);
        return result;
    }
}
