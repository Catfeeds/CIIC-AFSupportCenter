package com.ciicsh.gto.afsupportcenter.housefund.siteservice;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Created by houwanhua on 2018/1/24.
 */
@MapperScan("com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.housefund.siteservice"})
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
@EnableDiscoveryClient
public class FundSiteServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(FundSiteServiceApp.class, args);
    }
}
