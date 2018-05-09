package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host;

import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.configuration.CorsConfiguration;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.configuration.MybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * SpringBoot 方式启动类
 * @Author: guwei
 */
@MapperScan("com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.credentialscommandservice"})
@EnableDiscoveryClient
@EnableFeignClients({"com.ciicsh.gto.basicdataservice.api",
    "com.ciicsh.gto.employeecenter.apiservice.api",
    "com.ciicsh.gto.salecenter.apiservice.api",
    "com.ciicsh.gto.billcenter.afmodule.cmd.api.proxy",
    "com.ciicsh.gto.identityservice.api",
    "com.ciicsh.gto.productcenter.apiservice.api.proxy"})
@Import({CorsConfiguration.class, MybatisPlusConfig.class})
public class CredentialsApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CredentialsApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CredentialsApp.class);
    }
}
