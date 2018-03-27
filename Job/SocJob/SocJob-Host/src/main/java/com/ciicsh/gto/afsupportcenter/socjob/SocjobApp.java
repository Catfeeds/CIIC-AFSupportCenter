package com.ciicsh.gto.afsupportcenter.socjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by houwanhua on 2018/1/11.
 */
@EnableFeignClients({"com.ciicsh.gto.settlementcenter.payment.cmdapi","com.ciicsh.gto.logservice.api"})
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto","com.ciicsh.gto.afsupportcenter.util"})
@EnableDiscoveryClient
@MapperScan("com.ciicsh.gto.afsupportcenter.socjob.dao")
public class SocjobApp {
    public static void main(String[] args){
        SpringApplication.run(SocjobApp.class, args);
    }
}
