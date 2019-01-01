package com.smtteam.smt.service.impl;

import com.smtteam.smt.dao.StoryDao;
import com.smtteam.smt.dao.TaskDao;
import com.smtteam.smt.model.Story;
import com.smtteam.smt.model.Task;
import com.smtteam.smt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by Kimone
 * date 2018/12/31
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private StoryDao storyDao;

    @Override
    @Transactional
    public Task createTask(Task task) {
        taskDao.updateCreatePosID(task.getPosId()-1);
        return taskDao.save(task);
    }

    @Override
    public Task modifyTask(Task task) {
        return taskDao.save(task);
    }

    @Override
    public Task getTaskById(int id) {
        return taskDao.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteTask(int id) {
        Task task = taskDao.findById(id).get();
        List<Story> storyList = storyDao.findByTaskId(id);
        for(Story story: storyList) {
            storyDao.delete(story);
        }
//        taskDao.updateDeletePosID(task.getPosId());
        taskDao.delete(task);
    }

    @Override
    public List<Task> getByActivity(int activityId) {
        return taskDao.findByActivityIdOrderByPosId(activityId);
    }


}
