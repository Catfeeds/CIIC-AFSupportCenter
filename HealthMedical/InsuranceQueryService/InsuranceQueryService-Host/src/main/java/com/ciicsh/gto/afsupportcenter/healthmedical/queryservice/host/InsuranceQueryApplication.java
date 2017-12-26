package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.host.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * SpringBoot 方式启动类
 */
@MapperScan(basePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao"})
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.queryservice"})
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
public class InsuranceQueryApplication
{
    private final static Logger logger = LoggerFactory.getLogger(InsuranceQueryApplication.class);

    public static void main(String[] args) {
        logger.info("starting ...");
        SpringApplication.run(InsuranceQueryApplication.class, args);
        logger.info("start is success!");
    }
}
