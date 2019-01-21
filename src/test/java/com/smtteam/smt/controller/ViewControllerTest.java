package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
        mockMvc.perform(get("/storyMapping").sessionAttr("user",getUser()));
    }

    @Test
    public void login() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/login"));
    }

    @Test
    public void register() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/register"));
    }

    @Test
    public void main() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(get("/main"));
    }

    private ShowUser getUser(){
        user.setId(1);
        user.setUsername("zs");
        user.setEmail("150940958@qq.com");
        return user;
    }
}