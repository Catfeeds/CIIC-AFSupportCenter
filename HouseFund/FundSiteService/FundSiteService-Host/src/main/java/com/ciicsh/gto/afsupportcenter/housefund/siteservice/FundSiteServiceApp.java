package com.ciicsh.gto.afsupportcenter.housefund.siteservice;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * Created by houwanhua on 2018/1/24.
 */
@EnableFeignClients({
    "com.ciicsh.gto.basicdataservice.api"
})
@MapperScan("com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.housefund.siteservice"})
@EnableDiscoveryClient
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
public class FundSiteServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(FundSiteServiceApp.class, args);
    }
}
