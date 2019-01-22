package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectApiControllerTest extends SmtApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    private ShowUser user = new ShowUser();
    private static Integer id;


    @Test
    @Rollback
    @Transactional
    public void createProject() throws Exception{
        String name = "测试Name";
        String description = "测试描述";
        String result = mockMvc.perform(post("/api/project/create?proName="+name+"&description="+description)
                .sessionAttr("user",getUser()))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();

    }

    @Test
    @Rollback
    @Transactional
    public void modifyProject() throws Exception{
        Integer id = 2;
        String name = "测试Name-";
        String description = "测试描述";
        String result = mockMvc.perform(post("/api/project/modify?proId="+id+"&proName="+name+"&description="+description)
                .sessionAttr("user",getUser()))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getReleaseList() throws Exception{
        mockMvc.perform(get("/api/project/list").sessionAttr("user",getUser()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAttendedList() throws Exception{
        mockMvc.perform(get("/api/project/attended").sessionAttr("user",getUser()))
                .andExpect(status().isOk());
    }

    @Test
    public void getProjectDetail() throws Exception{
        mockMvc.perform(get("/api/project/detail?proId=2").sessionAttr("user",getUser()))
                .andExpect(status().isOk());
    }

    private ShowUser getUser(){
        user.setId(1);
        user.setUsername("zs");
        user.setEmail("150940958@qq.com");
        return user;
    }
}