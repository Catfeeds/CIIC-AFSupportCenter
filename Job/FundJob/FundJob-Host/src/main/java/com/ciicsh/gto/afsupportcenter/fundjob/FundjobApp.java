package com.ciicsh.gto.afsupportcenter.fundjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by houwanhua on 2018/2/7.
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto"})
@EnableDiscoveryClient
@MapperScan("com.ciicsh.gto.afsupportcenter.fundjob.dao")
public class FundjobApp {
    public static void main(String[] args){
        SpringApplication.run(FundjobApp.class, args);
    }
}
