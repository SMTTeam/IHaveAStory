package com.smtteam.smt.service.impl;

import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.common.enums.ProjectRole;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.dao.ProjectDao;
import com.smtteam.smt.dao.ProjectUserDao;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.model.ProjectUser;
import com.smtteam.smt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public List<Project> findReleaseList(int userId) {
        return projectDao.findByUserIdOrderByIdDesc(userId);
    }

    /**
     * 查看我参与的项目
     * @param userId
     * @return
     */
    @Override
    public List<Project> findAttendList(int userId) {
        List<ProjectUser> list = projectUserDao.findByUserIdAndStatus(userId, Constants.PROJECT_INVITED);
        List<Integer> projectIdList = list.stream()
                .filter(pro -> pro.getRole() != ProjectRole.Owner.getRole())
                .map(ProjectUser::getProId).collect(Collectors.toList());
        List<Project> projects = projectDao.findByIdIn(projectIdList);
        return projects;
    }

    /**
     * 修改项目
     * @param proId
     * @param userId
     * @param name
     * @param description
     * @return
     */
    @Override
    public Project modifyProject(Integer proId, Integer userId, String name, String description) throws NoAccessException {
        Project project = projectDao.findById(proId).get();
        if(!project.getUserId().equals(userId)){
            throw new NoAccessException("No access to the project.");
        }
        project.setDescription(description);
        project.setProName(name);
        project = projectDao.saveAndFlush(project);
        return project;
    }

    /**
     * 根据ID查找项目
     * @param proId
     * @return
     */
    @Override
    public Project findById(Integer proId) {
        Optional<Project> project = projectDao.findById(proId);
        return project.orElse(null);
    }


}
