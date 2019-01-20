package com.smtteam.smt.service.impl;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.service.VerifyService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by weishixin
 * date 2019-01-20
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerifyServiceImplTest extends SmtApplicationTests {
    @Autowired
    private VerifyService verifyService;

    private final Logger logger = LoggerFactory.getLogger(VerifyServiceImplTest.class);


    @Test
    public void test01_sendVerifyEmailTest(){
        //测试 该邮箱已注册但未验证，请前往验证！
        try {
            verifyService.sendVerifyEmail("1204353094@qq.com","123","wsx");
        }catch (Exception e){
            logger.info(e.getMessage());
        }

        //测试 该邮箱已经注册验证了！
        try {
            verifyService.sendVerifyEmail("18206296783@163.com","123","wsx");
        }catch (Exception e){
            logger.info(e.getMessage());
        }

        try {
            verifyService.sendVerifyEmail("wsx1204353094@gmail.com","123","wsx");
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    @Test
    public void test02_acceptVerifyEmailTest(){
        //测试邮箱已经验证
        try {
            verifyService.acceptVerifyEmail("MTgyMDYyOTY3ODNAMTYzLmNvbSY0MjExZGEzM2QyN2I2NWNlNzg1OTk0OWEyYTY4OTQ4ZGI2ZmNiZDA4MzhiZTkyZmRiZTllZWFjM2IyMGRiOWM1");
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        //测试验证邮箱不存在
        try {
            verifyService.acceptVerifyEmail("MTgyMDYyOTY3ODNAMTYAAAAAASZiZTUwNzY3YjE4ZDI0NDliOTViZDRkNmQ4MDkyZGExYzEzNzUxAAAAAjMwZjUzNmFjNDExY2M2ODMyODg5NmQ0");
        }catch (Exception e ){
            logger.info(e.getMessage());
        }

        //测试 验证信息被串改，验证失败
        try {
            verifyService.acceptVerifyEmail("MTgyMDYyOTY3ODNAMTYzLmNvbSZiZTUwNzY3YjE4ZDI0NDliOTViZDRkNmQ4MDkyZGExYzEzNzUxZDkwNjMwZjUzNmFjNDExY2M2ODMyODg5NmQ0");
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }
}
