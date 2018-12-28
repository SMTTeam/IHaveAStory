package com.smtteam.smt.dao;

import com.smtteam.smt.model.Story;

public interface StoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Story record);

    int insertSelective(Story record);

    Story selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Story record);

    int updateByPrimaryKeyWithBLOBs(Story record);

    int updateByPrimaryKey(Story record);
}