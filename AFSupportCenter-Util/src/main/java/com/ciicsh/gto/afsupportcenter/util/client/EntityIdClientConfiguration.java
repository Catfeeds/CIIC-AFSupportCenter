package com.ciicsh.gto.afsupportcenter.util.client;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 自定义自动化配置
 */
@Configuration
public class EntityIdClientConfiguration {

  @Bean
  @Lazy(false)
  public EntityIdHolder entityIdClientHolder() {
    return new EntityIdHolder();
  }
}
