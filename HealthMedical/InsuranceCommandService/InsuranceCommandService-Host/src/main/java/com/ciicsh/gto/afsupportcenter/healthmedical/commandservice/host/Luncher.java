package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host;

import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical"})
@MapperScan("com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.dao")
@EnableDiscoveryClient
@EnableFeignClients (basePackages = "com.ciicsh.gto.afsupportcenter.healthmedical")
@Import({CustomConfiguration.class})
public class Luncher extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Luncher.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(Luncher.class);
    }
}
