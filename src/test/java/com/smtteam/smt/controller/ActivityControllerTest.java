package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import com.smtteam.smt.dao.StoryDao;
import com.smtteam.smt.dao.TaskDao;
import com.smtteam.smt.model.Story;
import com.smtteam.smt.model.Task;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by Kimone
 * date 2019/1/21
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActivityControllerTest extends SmtApplicationTests {

    private MockMvc mockMvc;

    private ShowUser user = new ShowUser();

    private static Integer id;

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private StoryDao storyDao;

    @Test
    public void test01_createActivity() throws Exception {
        mockMvc = getMockMvc();
        int proId = 1;
        String name = "test_create_activity";
        int posId = 1;
        String result = mockMvc.perform(post("/activity/create?proId="+proId+"&name="+name+"&posId="+posId)
                .sessionAttr("user",getUser()))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        result = result.substring(result.lastIndexOf("{")+6);
        id = Integer.parseInt(result.substring(0,result.indexOf(",")));
        Task task = taskDao.save(new Task(id,"test_task",1));
        Story story = storyDao.save(new Story(task.getId(),"test_story",1,1,"",1,"",1));

    }

    @Test
    public void test02_modifyActivity() throws Exception {
        mockMvc = getMockMvc();
        String name = "test_modify_activity";
        mockMvc.perform(post("/activity/modify?id="+id+"&name="+name)
                .sessionAttr("user",getUser()))
                .andExpect(status().isOk());
    }

    @Test
    public void test03_getAllActivity() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/activity/list/2").sessionAttr("user",getUser()))
                .andExpect(status().isOk());
    }

    @Test
    public void test04_deleteActivity() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(post("/activity/delete?id="+id).sessionAttr("user",getUser()))
                .andExpect(status().isOk());
    }

    private ShowUser getUser(){
        user.setId(1);
        user.setUsername("zs");
        user.setEmail("150940958@qq.com");
        return user;
    }
}