package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * created by weishixin
 * date 2019-01-21
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterControllerTest extends SmtApplicationTests {
    private MockMvc mockMvc;

    @Test
    public void test0_checkEmail() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/register/checkemail?emailname=123@qq.com"));
    }

    @Test
    public void test1_checkEmail() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/register/checkemail?emailname=18206296783@163.com"));
    }

    @Test
    @Transactional
    @Rollback
    public void test2_sendVerifyEmail() throws Exception {
        mockMvc = getMockMvc();
        mockMvc.perform(post("/register/verifyemail?email=wsx1204353094@gmail.com&username=六学家&psw=123hhh"));
    }

    @Test
    public void test3_acceptVerifyEmail() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/register/acceptverifyemail/"));
    }

    @Test
    public void test4_acceptVerifyEmail() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/register/acceptverifyemail/MTgyMDYyOTY3ODNAMTYzLmNvbSY0MjExZGEzM2QyN2I2NWNlNzg1OTk0OWEyYTY4OTQ4ZGI2ZmNiZDA4MzhiZTkyZmRiZTllZWFjM2IyMGRiOWM1"));
    }

    @Test
    public void test5_acceptVerifyEmail() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/register/acceptverifyemail/MTgyMDYyOTY3ODNAMTYzLmNvbSZiZTUwNzY3YjE4ZDI0NDliOTViZDRkNmQ4MDkyZGExYzEzNzUxZDkwNjMwZjUzNmFjNDExY2M2ODMyODg5NmQ0"));
    }
}
