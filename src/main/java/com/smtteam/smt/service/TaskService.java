package com.smtteam.smt.service;

import com.smtteam.smt.model.Task;

import java.util.List;

/**
 * created by Kimone
 * date 2018/12/31
 */
public interface TaskService {

    Task createTask(Task task);

    Task modifyTask(Task task);

    Task getTaskById(int id);

    void deleteTask(int id);

    List<Task> getByActivity(int activityId);

    int findMaxID();
}
