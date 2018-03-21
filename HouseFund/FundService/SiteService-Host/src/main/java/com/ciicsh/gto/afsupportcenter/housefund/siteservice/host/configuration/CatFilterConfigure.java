package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.configuration;


import com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.interceptor.AuthenticateInterceptor;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.interceptor.CatInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yuyong
 * @date 2018/1/8
 */
@Configuration
public class CatFilterConfigure extends WebMvcConfigurerAdapter {
    @Bean
    public AuthenticateInterceptor authenticateInterceptor() {
        return new AuthenticateInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CatInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(authenticateInterceptor()).addPathPatterns("/**").excludePathPatterns("/basic/data/getUserInfoByToken/**");
        super.addInterceptors(registry);
    }

}
