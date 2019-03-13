package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;



/**
 * created by Kimone
 * date 2019/1/21
 */
public class ViewControllerTest extends SmtApplicationTests {

    private MockMvc mockMvc;

    private ShowUser user = new ShowUser();

    @Test
    public void test1() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get(""));
    }

    @Test
    public void test2() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/storyMapping?proId=1").sessionAttr("user",getUser()));
    }

    @Test
    public void test_login() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/login")).andExpect(status().isOk());
        mockMvc.perform(get("/login").sessionAttr("user",getUser()));
    }

    @Test
    public void test_register() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/register"));
    }

    @Test
    public void test_findBackPsw() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/findbackpsw"));
    }

    @Test
    public void test_findPswResetPsw() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/findpswResetPsw"));
    }

    @Test
    public void test_linkInvalid() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/linkInvalid"));
    }


    private ShowUser getUser(){
        user.setId(1);
        user.setUsername("zs");
        user.setEmail("150940958@qq.com");
        return user;
    }
}