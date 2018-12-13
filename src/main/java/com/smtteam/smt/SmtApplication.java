package com.smtteam.smt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmtApplication.class, args);
    }

}

