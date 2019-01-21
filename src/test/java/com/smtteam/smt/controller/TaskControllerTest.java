package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.dao.StoryDao;
import com.smtteam.smt.model.Story;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * created by Kimone
 * date 2019/1/21
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskControllerTest extends SmtApplicationTests {

    private MockMvc mockMvc;

    private ShowUser user = new ShowUser();

    private static Integer id;

    @Autowired
    private StoryDao storyDao;

    @Test
    public void test01_createTask() throws Exception {
        mockMvc = getMockMvc();
        int activityId = 1;
        String name = "test_create_task";
        int posId = 1;
        String result = mockMvc.perform(post("/task/create?activityId="+activityId+"&name="+name+"&posId="+posId)
                .sessionAttr("user",getUser()))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        result = result.substring(result.lastIndexOf("{")+6);
        id = Integer.parseInt(result.substring(0,result.indexOf(",")));
        Story story = new Story(id,"tets_story",1,1,"",1,"",1);
        storyDao.save(story);
    }

    @Test
    public void test02_modifyTask() throws Exception {
        mockMvc = getMockMvc();
        String name = "test_modify_task";
        mockMvc.perform(post("/task/modify?id="+id+"&name="+name)
                .sessionAttr("user",getUser()))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void test03_getByActivity() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/task/list/"+id).sessionAttr("user",getUser()))
                .andExpect(status().isOk());

    }

    @Test
    public void test04_deleteTask() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(post("/task/delete?id="+id).sessionAttr("user",getUser()))
                .andExpect(status().isOk());
    }



    private ShowUser getUser(){
        user.setId(1);
        user.setUsername("zs");
        user.setEmail("150940958@qq.com");
        return user;
    }
}