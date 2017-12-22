package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host;

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
@MapperScan(basePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.dao"})
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.commandservice"})
@Import(CustomConfiguration.class)
public class InsuranceCommandApplication
{
    private final static Logger logger = LoggerFactory.getLogger(InsuranceCommandApplication.class);

    public static void main(String[] args) {
        logger.info("starting ...");
        SpringApplication.run(InsuranceCommandApplication.class, args);
        logger.info("start is success!");
    }
}
