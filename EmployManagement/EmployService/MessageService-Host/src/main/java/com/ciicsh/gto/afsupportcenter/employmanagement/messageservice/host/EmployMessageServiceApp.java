package com.ciicsh.gto.afsupportcenter.employmanagement.messageservice.host;

import com.ciicsh.gto.afsupportcenter.employmanagement.messageservice.host.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;


@EnableFeignClients({
    "com.ciicsh.gto.sheetservice.api",
    "com.ciicsh.gto.employeecenter.apiservice.api",
    "com.ciicsh.gto.afcompanycenter.queryservice.api",
    "com.ciicsh.gto.salecenter.apiservice.api.proxy",
    "com.ciicsh.gto.afsystemmanagecenter.apiservice.api",
    "com.ciicsh.gto.logservice.api",
    "com.ciicsh.gto.identityservice.api"
})
@MapperScan("com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.employmanagement.messageservice.host","com.ciicsh.gto","com.ciicsh.common","com.ciicsh.gto.afsupportcenter.util.logService"})
@EnableDiscoveryClient
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
public class EmployMessageServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(EmployMessageServiceApp.class, args);
    }
}
