package com.ciicsh.gto.afsupportcenter.housefund.apiservice.host;

import com.ciicsh.gto.afsupportcenter.housefund.apiservice.host.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * Created by houwanhua on 2018/2/27.
 */
@EnableFeignClients({"com.ciicsh.gto.sheetservice.api",
    "com.ciicsh.gto.settlementcenter.payment.cmdapi",
    "com.ciicsh.gto.basicdataservice.api",
    "com.ciicsh.gto.afcompanycenter.queryservice.api",
    "com.ciicsh.gto.afcompanycenter.commandservice.api",
    "com.ciicsh.gto.afsystemmanagecenter.apiservice.api",
    "com.ciicsh.gto.logservice.api"})
@MapperScan("com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.housefund.fundservice","com.ciicsh.gto.afsupportcenter.housefund.apiservice.host","com.ciicsh.common"})
@EnableDiscoveryClient
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
public class FundApiServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(FundApiServiceApp.class, args);
    }
}
