package com.summer.oath;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yichuan
 */
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = {"com.summer.oath.mapper"})
@ServletComponentScan
@EnableTransactionManagement
public class OathServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OathServerApplication.class, args);
    }
}