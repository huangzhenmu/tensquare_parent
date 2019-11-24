package com.hzm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {
   /* //装配雪花算法工具类，这个工具类是在common模块的，base要用就装配
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }*/
}
