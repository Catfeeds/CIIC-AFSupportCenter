package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice"})
@MapperScan("com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao")
@EnableDiscoveryClient
public class Luncher extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Luncher.class);
    }
}
