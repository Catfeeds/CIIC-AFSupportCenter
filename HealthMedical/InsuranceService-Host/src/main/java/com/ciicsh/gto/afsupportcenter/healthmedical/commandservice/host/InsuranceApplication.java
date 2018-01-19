package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.host;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 方式启动类
 *
 * @author xiweizhen
 */
@MapperScan(basePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.dao"})
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.healthmedical.commandservice"})
public class InsuranceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsuranceApplication.class, args);
    }
}
