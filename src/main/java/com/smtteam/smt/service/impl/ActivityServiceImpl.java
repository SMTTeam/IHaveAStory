package com.smtteam.smt.service.impl;

import com.smtteam.smt.dao.ActivityDao;
import com.smtteam.smt.dao.StoryDao;
import com.smtteam.smt.dao.TaskDao;
import com.smtteam.smt.model.Activity;
import com.smtteam.smt.model.Task;
import com.smtteam.smt.model.Story;
import com.smtteam.smt.service.ActivityService;
import com.smtteam.smt.service.StoryService;
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
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskService taskService;
    @Autowired
    private StoryDao storyDao;
    @Autowired
    private StoryService storyService;

    @Override
    @Transactional
    public Activity createActivity(Activity activity) {
        activityDao.updateCreatePosID(activity.getProId(),activity.getPosId()-1);
        return activityDao.save(activity);
    }

    @Override
    @Transactional
    public void deleteActivity(int id) {
        Optional<Activity> optional = activityDao.findById(id);
        Activity activity = optional.orElse(null);
        List<Task> taskList = taskDao.findByActivityIdOrderByPosId(id);
        for(Task task: taskList) {
            List<Story> storyList = storyDao.findByTaskId(task.getId());
            for (Story story: storyList) {
                storyService.deleteStory(story.getId());
            }
            taskService.deleteTask(task.getId());
        }
        activityDao.delete(activity);
    }

    @Override
    public Activity getActivityById(int id) {
        Optional<Activity> activity = activityDao.findById(id);
        return activity.orElse(null);
    }

    @Override
    public Activity modifyActivity(Activity activity) {
        return activityDao.save(activity);
    }

    @Override
    public List<Activity> getActivityList(int proId) {
        return activityDao.findByProIdOrderByPosId(proId);
    }

}
