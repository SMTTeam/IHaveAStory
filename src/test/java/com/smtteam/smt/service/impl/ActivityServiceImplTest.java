package com.smtteam.smt.service.impl;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.dao.StoryDao;
import com.smtteam.smt.dao.TaskDao;
import com.smtteam.smt.model.Activity;
import com.smtteam.smt.model.Story;
import com.smtteam.smt.model.Task;
import com.smtteam.smt.service.ActivityService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * created by Kimone
 * date 2019/1/20
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActivityServiceImplTest extends SmtApplicationTests {
    @Autowired
    private ActivityService activityService;

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private StoryDao storyDao;

    private static Integer id;

    @Test
    public void test01_createActivity() {
        Activity activity = new Activity(100,"test_create",1);
        Activity activity1 = activityService.createActivity(activity);
        id = activity1.getId();
        Task task = new Task(id,"test_task",1);
        Task res = taskDao.save(task);
        Story story = new Story(res.getId(),"test_story",1,1,"",1,"",1);
        storyDao.save(story);
        assertNotNull(activity1.getId());
    }

    @Test
    public void test02_getActivityById() {
        Activity activity = activityService.getActivityById(id);
        assertEquals("test_create", activity.getName());
    }

    @Test
    public void test03_modifyActivity() {
        Activity activity = activityService.getActivityById(id);
        activity.setName("test_modify");
        Activity activity1 = activityService.modifyActivity(activity);
        assertEquals("test_modify", activity1.getName());
    }


    @Test
    public void test04_getActivityList() {
        List<Activity> list = activityService.getActivityList(100);
        assertEquals(1,list.size());
    }

    @Test
    public void test05_deleteActivity() {
        activityService.deleteActivity(id);
        Activity activity = activityService.getActivityById(id);
        assertNull(activity);
        activityService.deleteActivity(1000);
    }
}