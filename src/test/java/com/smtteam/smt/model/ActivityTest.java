package com.smtteam.smt.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * created by Kimone
 * date 2019/1/21
 */
public class ActivityTest {
    @Test
    public void test(){
        Activity activity = new Activity();
        activity.setId(1);
        activity.setName("activity");
        activity.setProId(1);
        activity.setPosId(1);
        activity.getProId();
        activity.getId();
        activity.getName();
        activity.getPosId();
    }

}