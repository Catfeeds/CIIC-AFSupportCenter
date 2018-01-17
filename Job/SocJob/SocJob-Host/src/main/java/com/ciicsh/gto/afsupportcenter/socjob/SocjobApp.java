package com.ciicsh.gto.afsupportcenter.socjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by houwanhua on 2018/1/11.
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.ciicsh.gto.afsupportcenter.socjob.dao")
public class SocjobApp {
    public static void main(String[] args){
        SpringApplication.run(SocjobApp.class, args);
    }
}
