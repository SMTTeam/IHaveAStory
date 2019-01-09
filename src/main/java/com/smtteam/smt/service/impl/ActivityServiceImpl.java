package com.smtteam.smt.service.impl;

import com.smtteam.smt.dao.ActivityDao;
import com.smtteam.smt.dao.TaskDao;
import com.smtteam.smt.model.Activity;
import com.smtteam.smt.model.Task;
import com.smtteam.smt.service.ActivityService;
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
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskService taskService;

    @Override
    @Transactional
    public Activity createActivity(Activity activity) {
        activityDao.updateCreatePosID(activity.getPosId()-1);
        Activity result = activityDao.save(activity);
        return result;
    }

    @Override
    @Transactional
    public void deleteActivity(int id) {
        Activity activity = activityDao.findById(id).get();
        List<Task> taskList = taskDao.findByActivityIdOrderByPosId(id);
        for(Task task: taskList) {
            taskService.deleteTask(task.getId());
        }
//        activityDao.updateDeletePosID(activity.getPosId());
        activityDao.delete(activity);
    }

    @Override
    public Activity getActivityById(int id) {
        return activityDao.findById(id).get();
    }

    @Override
    public Activity modifyActivity(Activity activity) {
        return activityDao.save(activity);
    }

    @Override
    public List<Activity> getActivityList(int proId) {
        return activityDao.findByProIdOrderByPosId(proId);
    }

    @Override
    public int findMaxID() {
        Integer num = activityDao.findMaxID();
        if(num==null){
            return 0;
        }else {
            return num;
        }

    }
}
