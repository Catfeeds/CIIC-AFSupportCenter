package com.ciicsh.gto.adsupportcenter.employcommandservice.host;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;


/**
 * Created by shil on 2017/9/21.
 */

@MapperScan("com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business","com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller"})
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
@EnableFeignClients
public class MainApplication{

    private final static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        logger.info("starting ...");
        SpringApplication.run(MainApplication.class, args);
        logger.info("start is success!");
    }

}