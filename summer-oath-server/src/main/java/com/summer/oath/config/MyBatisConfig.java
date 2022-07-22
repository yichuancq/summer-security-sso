package com.summer.oath.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis配置不能删除
 *
 * @author yichuan
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.summer.oath.mapper"})
public class MyBatisConfig {
}
