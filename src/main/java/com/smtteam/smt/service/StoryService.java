package com.smtteam.smt.service;

import com.smtteam.smt.common.bean.IterationVO;
import com.smtteam.smt.model.Story;

import java.util.List;
import java.util.Map;

public interface StoryService {

    Story createStory(Story story);

    Story getStoryById(int id);

    Story modifyStory(Story story);

    void deleteStory(int id);

    List<Story> getByTask(int taskId);

    int findMaxID();

    List<IterationVO> findIterNum(int proId);
}
