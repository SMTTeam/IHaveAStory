package com.smtteam.smt.service;

import com.smtteam.smt.model.Activity;

import java.util.List;

/**
 * created by Kimone
 * date 2018/12/31
 */
public interface ActivityService {
    Activity createActivity(Activity activity);

    void deleteActivity(int id);

    Activity getActivityById(int id);

    Activity modifyActivity(Activity activity);

    List<Activity> getActivityList(int proId);

}
