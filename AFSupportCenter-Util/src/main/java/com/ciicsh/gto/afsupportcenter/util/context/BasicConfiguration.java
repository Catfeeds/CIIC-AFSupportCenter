package com.ciicsh.gto.afsupportcenter.util.context;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

/**
 * 基础配置类
 */
public class BasicConfiguration
    implements EnvironmentAware, ApplicationContextAware, ResourcePatternResolver, InitializingBean {
  private Environment environment;
  private ApplicationContext applicationContext;

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public Environment getEnvironment() {
    return this.environment;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  public ApplicationContext getApplicationContext() {
    Assert.notNull(this.applicationContext, "applicationContext 不能为空！");
    return this.applicationContext;
  }

  @Override
  public Resource[] getResources(String locationPattern) throws IOException {
    return getApplicationContext().getResources(locationPattern);
  }

  @Override
  public Resource getResource(String location) {
    return getApplicationContext().getResource(location);
  }

  @Override
  public ClassLoader getClassLoader() {
    return getApplicationContext().getClassLoader();
  }

  public Object getBean(String name) throws BeansException {
    return getApplicationContext().getBean(name);
  }


  public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
    return getApplicationContext().getBean(name, requiredType);
  }


  public <T> T getBean(Class<T> requiredType) {
    return getApplicationContext().getBean(requiredType);
  }

  @Override
  public void afterPropertiesSet() throws Exception {

  }
}
