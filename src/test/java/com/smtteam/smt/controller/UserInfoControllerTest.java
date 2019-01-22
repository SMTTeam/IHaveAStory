package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * created by weishixin
 * date 2019-01-21
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserInfoControllerTest extends SmtApplicationTests {
    private MockMvc mockMvc;
    private ShowUser user = new ShowUser();

    @Test
    public void test0_updateInfo() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(post("/userinfo/update?useremail=123@163.com&username=Jay")
                .sessionAttr("user",getUser()));
    }

    @Test
    public void test1_updateInfo() throws Exception{
        mockMvc = getMockMvc();
        String useremail="18206296783@163.com";
        String username = "Jay Chou";
        mockMvc.perform(post("/userinfo/update?useremail="+useremail+"&username="+username)
                .sessionAttr("user", getUser()));
    }

    private ShowUser getUser(){
        user.setId(66);
        user.setUsername("weiShiXinX");
        user.setEmail("18206296783@163.com");
        return user;
    }
}
