package com.hzm;

import com.hzm.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching //使用SpringCache缓存,与redis差别在于不能设置过期时间
@EnableEurekaClient
public class GatheringApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatheringApplication.class,args);
    }

    //装配雪花算法工具类
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

}
