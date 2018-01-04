package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao")
public class FlexibleCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlexibleCommandApplication.class);
    }
}
