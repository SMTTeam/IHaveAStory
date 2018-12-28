package com.smtteam.smt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
//@EnableTransactionManagement
@MapperScan("com.smtteam.smt.dao")
public class SmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmtApplication.class, args);
    }

}

