package com.ciicsh.gto.afsupportcenter.cmjob;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by guwei on 2018/9/19.
 */
@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.cmjob"})
@MapperScan("com.ciicsh.gto.afsupportcenter.cmjob.dao")
@EnableFeignClients({
    "com.ciicsh.gto.logservice.api",
    "com.ciicsh.gto.employeecenter.apiservice.api",
    "com.ciicsh.gto.salecenter.apiservice.api",
    "com.ciicsh.gto.billcenter.afmodule.cmd.api.proxy",
    "com.ciicsh.gto.productcenter.apiservice.api.proxy"})
public class CMjobApp {
    public static void main(String[] args){
        SpringApplication.run(CMjobApp.class, args);
    }
}
