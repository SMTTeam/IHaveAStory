package com.smtteam.smt.common.bean;

import org.junit.Test;

public class StoryVOTest {

    @Test
    public void test01(){
        Integer taskId = 286;
        String name = "我是Story测试";
        Integer storyPoint = 2;
        String priority = "低";
        String description = "这是一个story测试,这是一个story测试,这是一个story测试,这是一个story测试";
        Integer posId = 2;
        String acceptance ="必须怎么怎么样";
        Integer releaseId = 50;
        StoryVO storyVO = new StoryVO(taskId, name, storyPoint, priority, description, posId, acceptance, releaseId);
        storyVO.toString();
    }

    @Test
    public void test02(){
        StoryVO storyVO = new StoryVO();
        storyVO.setTaskId(1);
        storyVO.getTaskId();

        storyVO.setName("123");
        storyVO.getName();
        storyVO.setStoryPoint(3);
        storyVO.getStoryPoint();
        storyVO.setPriority("高");
        storyVO.getPriority();
        storyVO.setDescription("fgsrgwgew");
        storyVO.getDescription();
        storyVO.setPosId(4);
        storyVO.getPosId();
        storyVO.setAcceptance("验收条件测试");
        storyVO.getAcceptance();
        storyVO.setReleaseId(50);
        storyVO.getReleaseId();
    }
}
