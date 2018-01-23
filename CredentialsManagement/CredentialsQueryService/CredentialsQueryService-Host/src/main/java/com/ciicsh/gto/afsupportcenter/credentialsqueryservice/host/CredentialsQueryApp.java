package com.ciicsh.gto.afsupportcenter.credentialsqueryservice.host;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:31 2018/1/15
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ciicsh.gto.afsupportcenter.credentialsQueryService.dao")
public class CredentialsQueryApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CredentialsQueryApp.class);
    }
}
