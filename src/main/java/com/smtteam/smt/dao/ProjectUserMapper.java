package com.smtteam.smt.dao;

import com.smtteam.smt.model.ProjectUser;

public interface ProjectUserMapper {
    int deleteByPrimaryKey(Integer puId);

    int insert(ProjectUser record);

    int insertSelective(ProjectUser record);

    ProjectUser selectByPrimaryKey(Integer puId);

    int updateByPrimaryKeySelective(ProjectUser record);

    int updateByPrimaryKey(ProjectUser record);
}