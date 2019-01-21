package com.smtteam.smt.service.impl;

import com.smtteam.smt.dao.StoryDao;
import com.smtteam.smt.model.Story;
import com.smtteam.smt.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryDao storyDao;

    @Override
    @Transactional
    public Story createStory(Story story){
        storyDao.updateCreatePosID(story.getPosId()-1);
        return storyDao.save(story);
    }

    @Override
    public Story getStoryById(int id){
        Optional<Story> story = storyDao.findById(id);
        return story.orElse(null);
    }

    @Override
    public Story modifyStory(Story story){
        return storyDao.save(story);
    }

    @Override
    public void deleteStory(int id){
        Story story = storyDao.findById(id).get();
        storyDao.delete(story);
    }

    @Override
    public List<Story> getByTask(int taskId){
        return storyDao.findByTaskIdOrderByPosIdDesc(taskId);
    }

}
