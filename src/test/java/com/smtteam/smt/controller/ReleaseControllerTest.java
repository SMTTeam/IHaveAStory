package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReleaseControllerTest extends SmtApplicationTests {

    private MockMvc mockMvc;

    private ShowUser user = new ShowUser();

    private static int relId;

    @Test
    public void test00_getIterList() throws Exception{
        mockMvc = getMockMvc();
        int proId = 27;
        mockMvc.perform(get("/release/list/"+proId).sessionAttr("user",getUser()));
    }

    @Test
    public void test01_createRelease() throws Exception{
        mockMvc = getMockMvc();
        int proId = 27;
        String name = "迭代2";
        int posId = 2;
        String result = mockMvc.perform(post("/release/create?proId="+proId+"&name="+name+"&posId="+posId)
            .sessionAttr("user",getUser())).andReturn().getResponse().getContentAsString();
        result = result.substring(result.lastIndexOf("{")+6);
        relId = Integer.parseInt(result.substring(0,result.indexOf(",")));
    }

    @Test
    @Transactional
    @Rollback
    public void test02_modifyRelease()throws Exception{
        mockMvc = getMockMvc();
        int releaseId = 78;
        String name ="2333";
        mockMvc.perform(post("/release/modify?id="+releaseId+"&name="+name)
            .sessionAttr("user",getUser()));
    }

    @Test
    public void test03_deleteRelease()throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(post("/release/delete?id="+relId).sessionAttr("user",getUser()));
    }

    private ShowUser getUser(){
        user.setId(66);
        user.setUsername("Jay Chou");
        user.setEmail("18206296783@163.com");
        return user;
    }
}
