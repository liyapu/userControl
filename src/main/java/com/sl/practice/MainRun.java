package com.sl.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 */
@MapperScan("com.sl.practice.mapper.base")
@SpringBootApplication
@EnableTransactionManagement
public class MainRun {
    public static void main(String[] args) {
        SpringApplication.run(MainRun.class,args);
    }
}
