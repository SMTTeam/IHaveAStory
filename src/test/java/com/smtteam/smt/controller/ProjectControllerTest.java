package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ProjectControllerTest extends SmtApplicationTests{

    private MockMvc mockMvc;
    private ShowUser user = new ShowUser();

    @Test
    public void project() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/project").sessionAttr("user",getUser()));
    }

    @Test
    public void attended() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/attended").sessionAttr("user",getUser()));
    }

    @Test
    public void createProject() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/project/create").sessionAttr("user",getUser()));
    }

    @Test
    public void editProject() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/project/edit?proId=2").sessionAttr("user",getUser()));
    }

    @Test
    @Transactional
    @Rollback
    public void acceptInvitation() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/invite/accept/xxx").sessionAttr("user",getUser()));
        mockMvc.perform(get("/invite/accept/MSYzJjhkOTY5ZWVmNmVjYWQzYzI5YTNhNjI5MjgwZTY4NmNmMGMzZjVkNWE4NmFmZjNjYTEyMDIwYzkyM2FkYzZjOTI=").sessionAttr("user",getUser()));
    }

    private ShowUser getUser(){
        user.setId(1);
        user.setUsername("zs");
        user.setEmail("150940958@qq.com");
        return user;
    }
}