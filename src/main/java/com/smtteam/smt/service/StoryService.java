package com.smtteam.smt.service;

import com.smtteam.smt.model.Story;

import java.util.List;

public interface StoryService {

    Story createStory(Story story);

    Story getStoryById(int id);

    Story modifyStory(Story story);

    void deleteStory(int id);

    List<Story> getByTask(int taskId);

    int findMaxPosID();

//    List<IterationVO> findIterNum(int proId);
}
