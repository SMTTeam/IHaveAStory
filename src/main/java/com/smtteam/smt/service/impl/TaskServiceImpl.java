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
import java.util.Optional;

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
        taskDao.updateCreatePosID(task.getActivityId(), task.getPosId()-1);
        return taskDao.save(task);
    }

    @Override
    public Task modifyTask(Task task) {
        return taskDao.save(task);
    }

    @Override
    public Task getTaskById(int id) {
        Optional<Task> task = taskDao.findById(id);
        return task.orElse(null);
    }

    @Override
    @Transactional
    public void deleteTask(int id) {
        Optional<Task> optionalTask = taskDao.findById(id);
        Task task = optionalTask.orElse(null);
        List<Story> storyList = storyDao.findByTaskId(id);
        for(Story story: storyList) {
            storyDao.delete(story);
        }
        if(task!=null){
            taskDao.delete(task);
        }
    }

    @Override
    public List<Task> getByActivity(int activityId) {
        return taskDao.findByActivityIdOrderByPosId(activityId);
    }


}
