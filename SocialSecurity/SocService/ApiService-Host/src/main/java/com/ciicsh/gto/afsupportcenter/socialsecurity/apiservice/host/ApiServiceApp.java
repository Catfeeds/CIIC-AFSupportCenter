package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host;

import com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Created by houwanhua on 2018/2/24.
 */
@MapperScan("com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice","com.ciicsh.common"})
@EnableDiscoveryClient
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
public class ApiServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiServiceApp.class, args);
    }
}
