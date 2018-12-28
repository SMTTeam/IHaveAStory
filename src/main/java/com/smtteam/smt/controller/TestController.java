package com.smtteam.smt.controller;

import com.alibaba.fastjson.JSON;
import com.smtteam.smt.model.User;
import com.smtteam.smt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类说明：测试Controller
 * 创建者：Zeros
 * 创建时间：2018-12-13 20:34
 * 包名：com.smtteam.smt.controller
 */

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("")
    public String test(){
        return "This is a test";
    }

    @GetMapping("another")
    public String testOther(){
        List<User> userList = testService.getUsers();
        User user = testService.getUser();
        System.out.println(JSON.toJSONString(userList));
        return JSON.toJSONString(userList);
    }

}
