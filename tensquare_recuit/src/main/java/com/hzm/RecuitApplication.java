package com.hzm;

import com.hzm.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class RecuitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecuitApplication.class,args);
    }

    //装配雪花算法工具类
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
