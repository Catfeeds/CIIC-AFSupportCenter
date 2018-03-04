package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host;

import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * SpringBoot 方式启动类
 */
@EnableFeignClients({"com.ciicsh.gto.sheetservice.api",
    "com.ciicsh.gto.settlementcenter.payment.cmdapi",
    "com.ciicsh.gto.settlementcenter.invoicecommandservice.api",
    "com.ciicsh.gto.basicdataservice.api",
    "com.ciicsh.gto.afcompanycenter.queryservice.api",
    "com.ciicsh.gto.afcompanycenter.commandservice.api",
    "com.ciicsh.gto.employeecenter.apiservice.api",
    "com.ciicsh.gto.logservice.api",
    "com.ciicsh.gto.afsystemmanagecenter.apiservice.api"
})
@MapperScan("com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.socialsecurity","com.ciicsh.gto","com.ciicsh.common"})
@EnableDiscoveryClient
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
public class SiteServiceApp {

    private final static Logger logger = LoggerFactory.getLogger(SiteServiceApp.class);

    public static void main(String[] args) {
        logger.info("starting ...");
        SpringApplication.run(SiteServiceApp.class, args);
        logger.info("start is success!");
    }

}
