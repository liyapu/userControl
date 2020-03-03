package com.sl.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@MapperScan("com.sl.practice.mapper.base")
@SpringBootApplication
public class MainRun {
    public static void main(String[] args) {
        SpringApplication.run(MainRun.class,args);
    }
}
