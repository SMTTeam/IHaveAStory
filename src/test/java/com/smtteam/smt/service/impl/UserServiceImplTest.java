package com.smtteam.smt.service.impl;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.UserService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest extends SmtApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void test0_findByEmailLike(){
        List<User> users = userService.findByEmailLike("@qq.");
        assertNotNull(users);
    }

    @Test
    public void test1_findById(){
        User user = userService.findById(3);
        assertNull(user);
    }

    @Test
    public void test2_findById(){
        User user = userService.findById(1);
        assertNotNull(user);
    }

    @Test
    public void test3_addUser(){
        User user = new User("test@gmail.com","123sda","六小龄童",0,"",1);
        User resultUser = userService.addUser(user);
        assertEquals("test@gmail.com", resultUser.getEmail());
    }

    @Test
    public void test4_findByEmail(){
        User user = userService.findByEmail("18206296783@163.com");
        Integer trueResult = new Integer(1);
        assertEquals(trueResult,user.getGender());
    }

    @Test
    public void test5_findByEmailAndPsw(){
        User user = userService.findByEmailAndPsw("120435309@qq.com","test");
        assertEquals("航海王", user.getUsername());
    }

    @Test
    public void test6_findByEmailAndStatus(){
        User user = userService.findByEmailAndStatus("1204353094@qq.com", 1);
        assertNotNull(user);
    }

    @Test
    public void test7_findByEmailAndStatus(){
        User user = userService.findByEmailAndStatus("1204353094@qq.com", 1);
        assertNotNull(user);
    }

    @Test
    public void test8_updateUserInfoByEmail(){
        int count = userService.updateUserInfoByEmail("1204353094@qq.com", "小威");
        assertEquals(1, count);
    }


}
