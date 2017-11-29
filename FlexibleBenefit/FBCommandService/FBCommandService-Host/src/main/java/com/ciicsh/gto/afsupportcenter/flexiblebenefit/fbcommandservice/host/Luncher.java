package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = {"com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice"})
@MapperScan("com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao")
@EnableDiscoveryClient
public class Luncher extends SpringBootServletInitializer {

    /**
     * 解决跨域问题
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Luncher.class);
    }
}
