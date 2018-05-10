package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.configuration;

import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.interceptor.CatInterceptor;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.AuthenticateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: guwei
 * @Description:
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
        registry.addInterceptor(new CatInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(authenticateInterceptor()).addPathPatterns("/**").excludePathPatterns("/basic/data/getUserInfoByToken/**");
        super.addInterceptors(registry);
    }

}
