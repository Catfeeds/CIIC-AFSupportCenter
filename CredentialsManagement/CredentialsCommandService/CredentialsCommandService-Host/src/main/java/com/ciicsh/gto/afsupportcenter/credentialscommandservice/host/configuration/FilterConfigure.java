package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.configuration;

import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.AuthenticateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: guwei
 * @Description: 请求拦截处理
 * @Date: Created in 13:32 2018/1/16
 */
@Configuration
public class FilterConfigure extends WebMvcConfigurerAdapter {

    @Bean
    public AuthenticateInterceptor authenticateInterceptor() {
        return new AuthenticateInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticateInterceptor()).addPathPatterns("/api/**")
            .excludePathPatterns(
                "/basic/data/getUserInfoByToken/**",
                "/api/credentialsMaterial/download/**"
            );
        super.addInterceptors(registry);
    }

}
