package com.smtteam.smt.controller;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.common.bean.ShowUser;
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

    @Test
    public void test2_checkOldPsw() throws Exception{
        mockMvc = getMockMvc();
        String oldpsw = "23123321";
        String useremail = "18206296783@163.com";
        mockMvc.perform(get("/userinfo/checkoldpsw?oldpsw="+oldpsw+"&useremail="+useremail)
                .sessionAttr("user",getUser()));
    }
    @Test
    public void test3_checkOldPsw() throws Exception{
        mockMvc = getMockMvc();
        String oldpsw = "123wei";
        String useremail = "18206296783@163.com";
        mockMvc.perform(get("/userinfo/checkoldpsw?oldpsw="+oldpsw+"&useremail="+useremail)
                .sessionAttr("user",getUser()));
    }

    @Test
    public void test4_changePsw() throws Exception{
        mockMvc = getMockMvc();
        String useremail = "8206296783@163.com";
        String newpsw = "123weii";
        mockMvc.perform(post("/userinfo/changepsw?useremail="+useremail+"&newpsw="+newpsw)
                .sessionAttr("user",getUser()));
    }

    @Test
    @Transactional
    @Rollback
    public void test5_changePsw() throws Exception{
        mockMvc = getMockMvc();
        String useremail = "18206296783@163.com";
        String newpsw = "123wei";
        mockMvc.perform(post("/userinfo/changepsw?useremail="+useremail+"&newpsw="+newpsw)
                .sessionAttr("user",getUser()));
    }

    @Test
    public void test6_sendResetPswEmail() throws Exception{
        mockMvc = getMockMvc();
        String useremail = "8206296783@163.com";
        mockMvc.perform(post("/userinfo/sendresetpswemail?useremail="+useremail)
                .sessionAttr("user",getUser()));
    }

    @Test
    public void test7_sendResetPswEmail() throws Exception{
        mockMvc = getMockMvc();
        String useremail = "18206296783@163.com";
        mockMvc.perform(post("/userinfo/sendresetpswemail?useremail="+useremail)
                .sessionAttr("user",getUser()));
    }

    @Test
    public void test8_resetPsw() throws Exception{
        mockMvc = getMockMvc();
        String code = "MTgyMDYyOTY3ODNAMTYzLmNvbSYyMDE5LTAzLTAzIDE2OjU4OjEyJjMzOGVmY2UzYzNjMTAxZjJjMDJmMjZjMTcwMTA5Mzg1MTU5NDA0NTc3M2NjNDMyYzNlNzcyNzU0MmM0NzQ1ZmQ=";
        mockMvc.perform(get("/userinfo/resetpsw/"+code)
                .sessionAttr("user",getUser()));
    }


    @Test
    public void test9_resetPsw() throws Exception{
        mockMvc = getMockMvc();
        String code = "MTgyMDYyOTY3ODNAMTYzLmNvbSYyMDE5LTAzLTEzIDAwOjU1OjQxJjFkNTViNWJhZjM2YTlhYjQ2ZGEwMjUxMzUxNmFjNTQ1ODc3OWE0MzFhNDdmMTgxYWU0ZjQwMTg5YzRmYjA4MDU=";
        mockMvc.perform(get("/userinfo/resetpsw/"+code)
                .sessionAttr("user",getUser()));
    }

    @Test
    public void test10_resetPassword() throws Exception{
        mockMvc = getMockMvc();
        String useremail = "8206296783@163.com";
        String newpsw = "123wei";
        mockMvc.perform(post("/userinfo/resetpassword?useremail="+useremail+"&newpsw="+useremail)
                .sessionAttr("user",getUser()));
    }

    @Test
    public void test11_resetPassword() throws Exception{
        mockMvc = getMockMvc();
        String useremail = "18206296783@163.com";
        String newpsw = "123wei";
        mockMvc.perform(post("/userinfo/resetpassword?useremail="+useremail+"&newpsw="+useremail)
                .sessionAttr("user",getUser()));
    }

    private ShowUser getUser(){
        user.setId(66);
        user.setUsername("weiShiXinX");
        user.setEmail("18206296783@163.com");
        return user;
    }
}
