package com.ciicsh.gto.afsupportcenter.util.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * Spring 的 ApplicationContext 的持有者,可以用静态方法的方式获取 spring 容器中的 bean
 */
public class SpringContextHolder implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringContextHolder.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    assertNotNull();
    return applicationContext;
  }

  public static <T> T getBean(String beanName) {
    return (T) getApplicationContext().getBean(beanName);
  }

  public static <T> T getBean(Class<T> requiredType) {
    return getApplicationContext().getBean(requiredType);
  }

  private static void assertNotNull() {
    Assert.notNull(SpringContextHolder.applicationContext,
        "applicaitonContext 属性为 null ，请检查是否注入了 SpringContextHolder ！");
  }

}
