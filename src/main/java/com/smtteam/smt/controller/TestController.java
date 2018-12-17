package com.smtteam.smt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明：测试Controller
 * 创建者：Zeros
 * 创建时间：2018-12-13 20:34
 * 包名：com.smtteam.smt.controller
 */

@RestController
public class TestController {

    @GetMapping("")
    public String test(){
        return "This is a test";
    }

    @GetMapping("another")
    public String testOther(){
        return "This is a another test";
    }

}
