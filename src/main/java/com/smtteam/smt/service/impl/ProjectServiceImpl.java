package com.smtteam.smt.service.impl;

import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.common.enums.ProjectRole;
import com.smtteam.smt.dao.ProjectDao;
import com.smtteam.smt.dao.ProjectUserDao;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.model.ProjectUser;
import com.smtteam.smt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private ProjectUserDao projectUserDao;

    /**
     * 创建项目
     * @param project
     * @return
     */
    @Override
    public Project createProject(Project project) {
        Project result = projectDao.save(project);
        ProjectUser projectUser = new ProjectUser(result.getId(), project.getUserId(), ProjectRole.Owner.getRole(), Constants.PROJECT_INVITED, "");
        projectUserDao.save(projectUser);
        return result;
    }

    /**
     * 查看我发布的项目
     * @param userId
     * @return
     */
    @Override
    public List<Project> findByUserId(int userId) {
        return projectDao.findByUserId(userId);
    }
}
