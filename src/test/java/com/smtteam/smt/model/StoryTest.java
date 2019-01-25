package com.smtteam.smt.model;

import org.junit.Test;

public class StoryTest {
    @Test
    public void test00(){
        Story story = new Story();

        story.setId(1);
        story.setTaskId(1);
        story.setName("海贼王摧更");
        story.setStoryPoint(2);
        story.setPriority(0);
        story.setDescription("fwetwetwetewt");
        story.setPosId(2);
        story.setAcceptance("验收条件测试");
        story.setReleaseId(50);

        story.getId();
        story.getTaskId();
        story.getName();
        story.getStoryPoint();
        story.getPriority();
        story.getDescription();
        story.getPosId();
        story.getAcceptance();
        story.getReleaseId();

    }
}
