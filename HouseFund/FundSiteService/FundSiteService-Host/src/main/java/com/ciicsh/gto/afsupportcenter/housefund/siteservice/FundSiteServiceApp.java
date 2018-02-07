package com.ciicsh.gto.afsupportcenter.housefund.siteservice;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * Created by houwanhua on 2018/1/24.
 */
@EnableFeignClients({"com.ciicsh.gto.sheetservice.api",
    "com.ciicsh.gto.settlementcenter.payment.cmdapi",
    "com.ciicsh.gto.settlementcenter.invoicecommandservice.api",
    "com.ciicsh.gto.basicdataservice.api",
    "com.ciicsh.gto.afcompanycenter.queryservice.api",
    "com.ciicsh.gto.afcompanycenter.commandservice.api"
//    "com.ciicsh.gto.employeecenter.apiservice.api"
})
@MapperScan("com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.housefund.siteservice"})
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
public class FundSiteServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(FundSiteServiceApp.class, args);
    }
}
