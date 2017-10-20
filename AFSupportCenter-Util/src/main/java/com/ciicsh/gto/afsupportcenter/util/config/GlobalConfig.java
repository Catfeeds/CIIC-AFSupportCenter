package com.ciicsh.gto.afsupportcenter.util.config;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import com.ciicsh.gto.afsupportcenter.util.kit.PropertySourceKit;
import com.ciicsh.gto.afsupportcenter.util.kit.PropertySources;

/**
 * 全局属性资源，配置成静态方法方便全局使用
 */
public class GlobalConfig {

  public static PropertySources commons() {
    PropertySource<?> propertySource = PropertySourceKit.loadYml("commons", new ClassPathResource("commons.yml"));
    return PropertySources.onForCache(propertySource);
  }

  public static void main(String... args) {
    System.out.println(commons().getString("url.api.genid"));
  }
}
