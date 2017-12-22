package com.ciicsh.gto.afsupportcenter.socialsecurity.socqueryservice.host;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * SpringBoot 方式启动类
 */
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.socialsecurity.socqueryservice"})
@EnableDiscoveryClient
public class SocQueryServiceApplication {

    private final static Logger logger = LoggerFactory.getLogger(SocQueryServiceApplication.class);

    public static void main(String[] args) {
        logger.info("starting ...");
        SpringApplication.run(SocQueryServiceApplication.class, args);
        logger.info("start is success!");
    }

}
