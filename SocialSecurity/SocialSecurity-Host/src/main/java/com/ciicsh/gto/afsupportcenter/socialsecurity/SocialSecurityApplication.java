package com.ciicsh.gto.afsupportcenter.socialsecurity;

import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * SpringBoot 方式启动类
 */
@ComponentScan(basePackages = {"com.ciicsh.gto.afsupportcenter.socialsecurity"})
@MapperScan(basePackages = {"com.ciicsh.gto.afsupportcenter.socialsecurity.dao"})
@SpringBootApplication
@Import({CustomConfiguration.class})
public class SocialSecurityApplication {

  private final static Logger logger = LoggerFactory.getLogger(SocialSecurityApplication.class);

  public static void main(String[] args) {
        logger.info("starting ...");
        SpringApplication.run(SocialSecurityApplication.class, args);
        logger.info("start is success!");
    }

}
