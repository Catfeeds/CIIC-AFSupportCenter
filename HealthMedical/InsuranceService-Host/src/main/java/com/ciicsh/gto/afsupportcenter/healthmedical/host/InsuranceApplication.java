package com.ciicsh.gto.afsupportcenter.healthmedical.host;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * SpringBoot 方式启动类
 *
 * @author xiweizhen
 */
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.dao"})
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.host"})
public class InsuranceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsuranceApplication.class);
    }
}
