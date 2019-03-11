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
public class InviteApiControllerTest extends SmtApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    private ShowUser user = new ShowUser();

    @Test
    @Rollback
    @Transactional
    public void search() throws Exception{
        mockMvc.perform(get("/api/invite/search?email=1509403958")
                .sessionAttr("user",getUser()))
                .andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
    }

    @Test
    @Rollback
    @Transactional
    public void sendInviteEmail() throws Exception{
        mockMvc.perform(post("/api/invite/create?proId=3&email=111&role=1")
                .sessionAttr("user",getUser()));
        mockMvc.perform(post("/api/invite/create?proId=1&email=111&role=1")
                .sessionAttr("user",getUser()));
        mockMvc.perform(post("/api/invite/create?proId=3&email=1509403958@qq.com&role=1")
                .sessionAttr("user",getUser()));
        mockMvc.perform(post("/api/invite/create?proId=5&email=1509403958@qq.com&role=1")
                .sessionAttr("user",getUser()));
    }

    @Test
    @Rollback
    @Transactional
    public void getProjectRole() throws Exception{
        mockMvc.perform(get("/api/invite/role?proId=3&userId=1").sessionAttr("user",getUser()));
    }

    @Test
    @Rollback
    @Transactional
    public void getInviteList() throws Exception{
        mockMvc.perform(get("/api/invite/list?proId=3").sessionAttr("user",getUser()));
    }

    @Test
    @Rollback
    @Transactional
    public void deleteInvite() throws Exception{
        mockMvc.perform(post("/api/invite/delete?proId=3&userId=4").sessionAttr("user",getUser()));
        mockMvc.perform(post("/api/invite/delete?proId=3&userId=5").sessionAttr("user",getUser()));
    }

    @Test
    @Rollback
    @Transactional
    public void modifyInvite() throws Exception{
        mockMvc.perform(post("/api/invite/modify?proId=3&userId=4&role=4").sessionAttr("user",getUser()));
        mockMvc.perform(post("/api/invite/modify?proId=3&userId=5&role=4").sessionAttr("user",getUser()));
        mockMvc.perform(post("/api/invite/modify?proId=3&userId=5&role=1").sessionAttr("user",getUser()));
    }



    private ShowUser getUser(){
        user.setId(1);
        user.setUsername("zs");
        user.setEmail("150940958@qq.com");
        return user;
    }
}