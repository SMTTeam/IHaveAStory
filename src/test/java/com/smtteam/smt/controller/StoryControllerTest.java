package com.smtteam.smt.controller;

import com.alibaba.fastjson.JSONObject;
import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.common.bean.StoryVO;
import com.smtteam.smt.model.Story;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoryControllerTest extends SmtApplicationTests {

    private MockMvc mockMvc;

    private ShowUser user = new ShowUser();

    private static int storyId;

    private final Logger logger = LoggerFactory.getLogger(StoryControllerTest.class);

    @Test
    public void test00_getStoryById() throws Exception{
        mockMvc = getMockMvc();
        int storyId = 240;
        mockMvc.perform(get("/story/"+storyId).sessionAttr("user",getUser()));
    }

    @Test
    @Transactional
    @Rollback
    public void test01_createStory () throws Exception{
        mockMvc = getMockMvc();
        StoryVO storyVO = new StoryVO();
        storyVO.setTaskId(1);
        storyVO.setName("123");
        storyVO.setStoryPoint(3);
        storyVO.setPriority("中");
        storyVO.setDescription("fgsrgwgew");
        storyVO.setPosId(4);
        storyVO.setAcceptance("验收条件测试");
        storyVO.setReleaseId(50);
        String storyInput = JSONObject.toJSONString(storyVO);
        mockMvc.perform(post("/story/create").contentType(MediaType.APPLICATION_JSON).content(storyInput)
                .sessionAttr("user",getUser()));

    }

    @Test
    public void test02_createStory () throws Exception{
        mockMvc = getMockMvc();
        StoryVO storyVO = new StoryVO();
        storyVO.setTaskId(1);
        storyVO.setName("123");
        storyVO.setStoryPoint(3);
        storyVO.setPriority("高");
        storyVO.setDescription("fgsrgwgew");
        storyVO.setPosId(4);
        storyVO.setAcceptance("验收条件测试");
        storyVO.setReleaseId(50);
        String storyInput = JSONObject.toJSONString(storyVO);

        String result = mockMvc.perform(post("/story/create").contentType(MediaType.APPLICATION_JSON).content(storyInput)
                .sessionAttr("user",getUser()))
                .andReturn().getResponse().getContentAsString();
        logger.info(result);
        result = result.substring(result.lastIndexOf("{")+6);
        storyId = Integer.parseInt(result.substring(0,result.indexOf(",")));
      }

    @Test
    @Transactional
    @Rollback
    public void test03_modifyStory() throws Exception{
        mockMvc = getMockMvc();
        StoryVO storyVO = new StoryVO();
        storyVO.setTaskId(286);
        storyVO.setName("123");
        storyVO.setStoryPoint(5);
        storyVO.setPriority("中");
        storyVO.setDescription("fgsrgwgew");
        storyVO.setPosId(1);
        storyVO.setAcceptance("验收条件测试");
        storyVO.setReleaseId(50);
        int id = 247;
        String storyInput = JSONObject.toJSONString(storyVO);
        mockMvc.perform(post("/story/modify?id="+id).contentType(MediaType.APPLICATION_JSON).content(storyInput).sessionAttr("user",getUser()));
    }

    @Test
    @Transactional
    @Rollback
    public void test04_modifyStory() throws Exception{
        mockMvc = getMockMvc();
        StoryVO storyVO = new StoryVO();
        storyVO.setTaskId(286);
        storyVO.setName("123");
        storyVO.setStoryPoint(5);
        storyVO.setPriority("高");
        storyVO.setDescription("fgsrgwgew");
        storyVO.setPosId(1);
        storyVO.setAcceptance("验收条件测试");
        storyVO.setReleaseId(50);
        int id = 247;
        String storyInput = JSONObject.toJSONString(storyVO);
        mockMvc.perform(post("/story/modify?id="+id).contentType(MediaType.APPLICATION_JSON).content(storyInput).sessionAttr("user",getUser()));
    }

    @Test
    public void test05_deleteStory( ) throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(post("/story/delete?id="+storyId).sessionAttr("user",getUser()));
        mockMvc.perform(post("/story/delete?id="+1000000).sessionAttr("user",getUser()));
    }

    @Test
    public void test06_getByTask()throws Exception{
        mockMvc = getMockMvc();
        int taskId = 286;
        mockMvc.perform(get("/story/list/"+taskId).sessionAttr("user",getUser()));
    }

    @Test
    public void test07_exchangeById()throws Exception{
        mockMvc = getMockMvc();
        int src_id = 24;
        int tar_id = 297;
        mockMvc.perform(post("/story/exchange?src_id="+src_id+"&tar_id="+tar_id).sessionAttr("user",getUser()));
    }

    private ShowUser getUser(){
        user.setId(66);
        user.setUsername("Jay Chou");
        user.setEmail("18206296783@163.com");
        return user;
    }
}
