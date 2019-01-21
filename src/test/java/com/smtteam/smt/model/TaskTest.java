package com.smtteam.smt.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * created by Kimone
 * date 2019/1/21
 */
public class TaskTest {
    @Test
    public void test(){
        Task task = new Task();
        task.setId(1);
        task.setName("task");
        task.setActivityId(1);
        task.setPosId(1);
        task.getActivityId();
        task.getId();
        task.getPosId();
        task.getName();
    }
}