package com.ciicsh.gto.afsupportcenter.util.client;

import com.ciicsh.gto.afsupportcenter.util.config.GlobalConfig;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.tips.Tip;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.client.RestTemplate;

/**
 * EntityIdHolder 工具箱
 */
public class EntityIdHolder implements ApplicationContextAware {

  private static RestTemplate template;
  private static String url;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    RestTemplateBuilder builder = applicationContext.getBean(RestTemplateBuilder.class);
    BusinessException.notNull(builder, "RestTemplateBuilder 属性为 null ，请检查是否初始化 RestTemplateBuilder ！");
    EntityIdHolder.template = builder.build();
    EntityIdHolder.url = GlobalConfig.commons().getString("url.api.genid");
  }

  /**
   * 业务实体编号生成
   *
   * @param id
   * @return
   */
  public static String genId(String id) {
    return genId(template, url, id);
  }

  public static String genId(RestTemplate template, String url, String id) {
    Tip<String> tip = template.getForObject(url + "/{id}", Tip.class, id);
    if (tip.getCode() == 200) {
      return String.valueOf(tip.getData());
    }
    throw new BusinessException(tip.getMessage());
  }

}
