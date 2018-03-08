package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author xwz
 * @SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice"})
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.ciicsh.gto.afcompanycenter.queryservice.api","com.ciicsh.gto.salecenter.apiservice.api","com.ciicsh.gto.basicdataservice.api"})
@MapperScan("com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao")
public class FlexibleQueryApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FlexibleQueryApplication.class);
    }
}
