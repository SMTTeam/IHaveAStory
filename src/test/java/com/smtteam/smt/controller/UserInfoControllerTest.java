package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * created by weishixin
 * date 2019-01-21
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserInfoControllerTest extends SmtApplicationTests {
    private MockMvc mockMvc;

    @Test
    public void test0_updateInfo() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(post("/userinfo/update?useremail=123@163.com&username=Jay"));
    }

    @Test
    public void test1_updateInfo() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(post("/userinfo/update?useremail=18206296783@163.com&username=Jay Chou"));
    }
}
