package com.ciicsh.gto.adsupportcenter.employcommandservice.host.configuration;


import com.ciicsh.gto.adsupportcenter.employcommandservice.host.interceptor.CatInterceptor;
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
        registry.addInterceptor(authenticateInterceptor()).addPathPatterns("/**").excludePathPatterns("/basic/data/getUserInfoByToken/**");
        super.addInterceptors(registry);
    }

}
