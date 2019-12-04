package com.hzm;

import com.hzm.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QaApplication {
    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class,args);
    }
    //装配雪花算法工具类
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
