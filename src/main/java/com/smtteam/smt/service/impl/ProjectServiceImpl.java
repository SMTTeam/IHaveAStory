package com.smtteam.smt.service.impl;

import com.smtteam.smt.common.bean.Constants;
import com.smtteam.smt.common.enums.ProjectRole;
import com.smtteam.smt.common.exception.NoAccessException;
import com.smtteam.smt.dao.ProjectDao;
import com.smtteam.smt.dao.ProjectUserDao;
import com.smtteam.smt.model.Project;
import com.smtteam.smt.model.ProjectUser;
import com.smtteam.smt.service.ActivityService;
import com.smtteam.smt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;

    @Autowired
    private ActivityService activityService;

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

        return projectDao.findByIdIn(projectIdList);
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
        Project project = projectDao.findById(proId).orElse(null);
        ProjectUser user = projectUserDao.findByUserIdAndProId(userId, proId);
        if(project == null || user == null || user.getRole() < ProjectRole.Project_Editor.getRole()){
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

    /**
     * 删除项目
     * @param proId
     * @param userId
     * @return
     */
    @Override
    public boolean deleteProject(Integer proId, Integer userId) {
        Optional<Project> pro = projectDao.findById(proId);
        Project project = pro.orElse(null);
        if(project != null && project.getUserId().equals(userId)){
            List<ProjectUser> userList = projectUserDao.findByProIdOrderByRoleDesc(proId);
            for(ProjectUser user: userList){
                projectUserDao.deleteById(user.getId());
            }
            activityService.deleteByProId(proId);
            projectDao.deleteById(proId);
            return true;
        }
        return false;
    }


}
