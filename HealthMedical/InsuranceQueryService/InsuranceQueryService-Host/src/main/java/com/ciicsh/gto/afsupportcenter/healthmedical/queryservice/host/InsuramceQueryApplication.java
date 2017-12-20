package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host;

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
@MapperScan(basePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao"})
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical"})
@Import(CustomConfiguration.class)
public class InsuramceQueryApplication
{
    private final static Logger logger = LoggerFactory.getLogger(InsuramceQueryApplication.class);

    public static void main(String[] args) {
        logger.info("starting ...");
        SpringApplication.run(InsuramceQueryApplication.class, args);
        logger.info("start is success!");
    }
}
