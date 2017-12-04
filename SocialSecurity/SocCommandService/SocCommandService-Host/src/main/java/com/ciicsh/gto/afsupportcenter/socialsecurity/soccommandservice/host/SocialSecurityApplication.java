package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 方式启动类
 */
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice","com.ciicsh.gto.afsupportcenter.util"})
//@EnableDiscoveryClient
public class SocialSecurityApplication {

    private final static Logger logger = LoggerFactory.getLogger(SocialSecurityApplication.class);

    public static void main(String[] args) {
        logger.info("starting ...");
        SpringApplication.run(SocialSecurityApplication.class, args);
        logger.info("start is success!");
    }

}
