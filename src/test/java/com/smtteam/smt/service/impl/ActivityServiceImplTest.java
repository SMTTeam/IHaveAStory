package com.smtteam.smt.service.impl;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.model.Activity;
import com.smtteam.smt.service.ActivityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * created by Kimone
 * date 2019/1/20
 */
public class ActivityServiceImplTest extends SmtApplicationTests {
    @Autowired
    private ActivityService activityService;

    @Test
    public void getActivityById() {
        int id = 1;
        Activity activity = activityService.getActivityById(id);
        System.out.println(activity.toString());
    }
}