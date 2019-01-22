package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * created by weishixin
 * date 2019-01-21
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginControllerTest extends SmtApplicationTests {



    private ShowUser user = new ShowUser();
    private MockMvc mockMvc ;
    @Test
    public void test0_checkLogin() throws Exception{
        mockMvc = getMockMvc();
        String email = "1204353094@qq.com";
        String pwd = "123sda";
        mockMvc.perform( post("/checklogin?email"+email+"&pwd="+pwd) );
    }

    @Test
    public void test01_checkLogin() throws Exception{
        mockMvc = getMockMvc();
        String email = "18206296783@163.com";
        String pwd = "123sd";
        mockMvc.perform(post("/checklogin?email="+email+"&pwd="+pwd));
    }

    @Test
    public void test02_checkLogin() throws Exception{
        mockMvc = getMockMvc();
        String email = "18206296783@163.com";
        String pwd = "123wei";
        mockMvc.perform(post("/checklogin?email="+email+"&pwd="+pwd));
    }

    @Test
    public void test03_getUserLoginState() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/getuserloginstate").sessionAttr("user",getUser()));
    }

    @Test
    public void test04_getUserLoginState() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/getuserloginstate").sessionAttr("user",null));
    }

    @Test
    public void test05_Exit() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/exit").sessionAttr("user",getUser() ));
    }

    private ShowUser getUser(){
        user.setId(66);
        user.setUsername("weiShiXinX");
        user.setEmail("18206296783@163.com");
        return user;
    }
}
