package com.smtteam.smt.service.impl;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.model.Story;
import com.smtteam.smt.service.StoryService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoryServiceImplTest extends SmtApplicationTests {
    @Autowired
    private StoryService storyService;

    private static Integer id;

    @Test
    public void test01_createStory() {
        Story story = new Story(94,"test_create",5,1,"",10,"",2);
        Story result = storyService.createStory(story);
        id = result.getId();
        assertNotNull(result.getId());
    }

    @Test
    public void test02_getStoryById() {
        Story result = storyService.getStoryById(id);
        assertEquals("test_create",result.getName());
    }

    @Test
    public void test03_modifyStory() {
        Story story = storyService.getStoryById(id);
        story.setName("test_modify");
        Story result = storyService.modifyStory(story);
        assertEquals("test_modify",result.getName());
    }

    @Test
    public void test04_getByTask() {
        List<Story> storyList = storyService.getByTask(4);
        assertEquals(4,storyList.size());
    }

    @Test
    public void test05_deleteStory() {
        storyService.deleteStory(id);
        Story result = storyService.getStoryById(id);
        assertNull(result);
    }
}
