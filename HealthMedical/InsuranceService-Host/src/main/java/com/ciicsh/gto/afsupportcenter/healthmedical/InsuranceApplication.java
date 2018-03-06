package com.ciicsh.gto.afsupportcenter.healthmedical;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot 方式启动类
 *
 * @author xiweizhen
 */
@EnableFeignClients({"com.ciicsh.gto.sheetservice.api",
    "com.ciicsh.gto.afcompanycenter.queryservice.api",
    "com.ciicsh.gto.employeecenter.apiservice.api",
    "com.ciicsh.gto.productcenter.apiservice.api",
    "com.ciicsh.gto.settlementcenter.payment.cmdapi"
})
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gt1","com.ciicsh.gto.afsupportcenter.healthmedical"})
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.dao"})
@EnableScheduling
public class InsuranceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsuranceApplication.class);
    }
}
