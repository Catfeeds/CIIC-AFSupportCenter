package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.configuration;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by houwanhua on 2018/3/8.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.controller"))
            .paths(regex("/api/soc.*"))
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Gt1 AfSupport Center SocialSecurity Api")
            .description("更多swagger 问题：https://swagger.io/")
            .termsOfServiceUrl("https://swagger.io/")
            .version("1.0")
            .build();
    }
}
