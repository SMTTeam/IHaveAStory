package com.smtteam.smt.service.impl;

import com.smtteam.smt.SmtApplicationTests;
import com.smtteam.smt.service.VerifyService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


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
    @Transactional
    @Rollback
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
    @Transactional
    @Rollback
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

        String code = "TUYxODMyMTc0QHNtYWlsLm5qdS5lZHUuY24mYjEzYzA4YmVhM2E5OGViMGNmYWUxOTJkNGQ1ZTk3M2I0ZWVkMDQ3MWM2NTkwNTJkMzk0M2JmM2YyYmU4NjVhZA==";

        //测试 验证信息被串改，验证失败
        String code_v1 = "TUYxODMyMTc0QHNtYWlsLm5qdS5lZHUuY24mYjEzYzA4YmVhM2E5OGViMGNmYWUxOTJkNGQ1ZTk3M2I0ZWVkMDQ3MWM2NTwkNTJkMzk0M2JmM2YyYmU4NjVhZA==";
        try {
            verifyService.acceptVerifyEmail(code_v1);
        }catch (Exception e){
            logger.info(e.getMessage());
        }

        //成功验证
        try {
            verifyService.acceptVerifyEmail(code);
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }
}

