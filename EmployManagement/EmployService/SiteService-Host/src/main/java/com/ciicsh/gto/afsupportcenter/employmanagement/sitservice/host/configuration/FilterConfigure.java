package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.configuration;


import com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.interceptor.CatInterceptor;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.AuthenticateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yuyong
 * @date 2018/1/8
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
        registry.addInterceptor(authenticateInterceptor()).addPathPatterns("/**").excludePathPatterns("/basic/data/getUserInfoByToken/**")
            .excludePathPatterns("/api/employ/**")
            .excludePathPatterns("/error");
        super.addInterceptors(registry);
    }

}
