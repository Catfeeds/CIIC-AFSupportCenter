package com.ciicsh.gto.afsupportcenter.util.client;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义自动化配置
 */
public class EntityIdClientConfiguration {

    @Bean
    @Lazy(false)
    public EntityIdHolder entityIdHolder() {
        return new EntityIdHolder();
    }

    /**
     * @return
     * @LoadBalanced 注解表明这个 restRemplate 开启负载均衡的功能
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
