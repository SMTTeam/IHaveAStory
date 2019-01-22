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
    public void test00_checkEmail() throws Exception{
        mockMvc = getMockMvc();
        String emailname = "123@qq.com";
        mockMvc.perform(get("/register/checkemail?emailname="+emailname));
    }

    @Test
    public void test01_checkEmail() throws Exception{
        mockMvc = getMockMvc();
        String emailname = "18206296783@163.com";
        mockMvc.perform(get("/register/checkemail?emailname=" + emailname));
    }

    @Test
    @Transactional
    @Rollback
    public void test02_sendVerifyEmail() throws Exception {
        mockMvc = getMockMvc();
        String email = "MF1832174@smail.nju.edu.cn";
        String username = "wsx";
        String psw = "123wei" ;
        mockMvc.perform(post("/register/verifyemail?email="+email+"&username="+username+"&psw="+psw));
    }

    @Test
    @Transactional
    @Rollback
    public void test03_sendVerifyEmail() throws Exception {
        mockMvc = getMockMvc();
        String email = "18206296783@163.com";
        String username = "wsx";
        String psw = "123wei" ;
        mockMvc.perform(post("/register/verifyemail?email="+email+"&username="+username+"&psw="+psw));
    }

    @Test
    public void test04_acceptVerifyEmail() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/register/acceptverifyemail"));
    }

    @Test
    public void test05_acceptVerifyEmail() throws Exception{
        mockMvc = getMockMvc();
        mockMvc.perform(get("/register/acceptverifyemail/"));
    }


    @Test
    @Transactional
    @Rollback
    public void test06_acceptVerifyEmail() throws Exception{
        mockMvc = getMockMvc();
        String code = "TUYxODMyMTc0QHNtYWlsLm5qdS5lZHUuY24mYjEzYzA4YmVhM2E5OGViMGNmYWUxOTJkNGQ1ZTk3M2I0ZWVkMDQ3MWM2NTkwNTJkMzk0M2JmM2YyYmU4NjVhZA==";
        mockMvc.perform(get("/register/acceptverifyemail/"+code));
    }

    @Test
    public void test07_acceptVerifyEmail() throws Exception{
        mockMvc = getMockMvc();
        String code = "TUYxODMyMTc0QHNtYWlsLm5qdS5lZHUuY24mYjEzYzA4YmVhM2E5OGViMGNmYWUxOTJkNGQ1ZTk3M2I0ZWVkMDQ3MWM2NTkwNTJkMzk033JmM2YyYmU4NjVhZA==";
        mockMvc.perform(get("/register/acceptverifyemail/"+code));
    }
}
