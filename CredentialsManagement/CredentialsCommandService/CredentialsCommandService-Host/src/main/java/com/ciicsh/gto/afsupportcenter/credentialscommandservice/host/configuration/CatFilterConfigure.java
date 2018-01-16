package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.configuration;

import com.ciicsh.gto.companycenter.webcommandservice.host.interceptor.CatInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 13:32 2018/1/16
 */
public class CatFilterConfigure extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CatInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
