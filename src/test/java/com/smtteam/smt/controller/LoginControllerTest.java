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

    private final Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);

    private MockMvc mockMvc ;
    @Test
    public void test0_checkLogin() throws Exception{
        mockMvc = getMockMvc();
        MvcResult result = mockMvc.perform(post("/checklogin?email=1204353094@qq.com&pwd=123sda"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

//        Assert.assertEquals(result.getModelAndView().getModel().get(""));


    }

    @Test
    public void test1_checkLogin() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(post("/checklogin?email=18206296783@163.com&pwd=123sd"));
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test2_checkLogin() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(post("/checklogin?email=18206296783@163.com&pwd=123wei"));
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test3_getUserLoginState() throws Exception{
        mockMvc = getMockMvc();

        mockMvc.perform(get("/getuserloginstate"));
//            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test4_Exit() throws Exception{
        mockMvc = getMockMvc();
        ShowUser showUser = new ShowUser();
        showUser.setEmail("18206296783@163.com");
        showUser.setUsername("wei");
        mockMvc.perform(get("/exit").sessionAttr("user",showUser ));
//                .andExpect(MockMvcResultMatchers.view().name("redirect:/login2"));
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
