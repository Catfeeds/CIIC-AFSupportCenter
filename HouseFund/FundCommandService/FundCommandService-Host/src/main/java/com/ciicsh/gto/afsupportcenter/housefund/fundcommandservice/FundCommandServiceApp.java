package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.configuration.MybatisPlusConfig;
import com.ciicsh.gto.afsupportcenter.util.config.CustomConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by houwanhua on 2018/1/24.
 */
@MapperScan("com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao")
@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice"})
@Import({CustomConfiguration.class, MybatisPlusConfig.class})
public class FundCommandServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(FundCommandServiceApp.class, args);
    }
}
