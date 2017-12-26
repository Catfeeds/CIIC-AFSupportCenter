package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * war 启动器
 */
public class ServletInitializer extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SocialSecurityApplication.class);
  }

}
