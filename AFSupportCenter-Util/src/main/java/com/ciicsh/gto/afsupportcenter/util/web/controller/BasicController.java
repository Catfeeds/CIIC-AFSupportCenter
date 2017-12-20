package com.ciicsh.gto.afsupportcenter.util.web.controller;

import com.ciicsh.gto.afsupportcenter.util.json.jackson2.LocalDateSerializer;
import com.ciicsh.gto.afsupportcenter.util.web.convert.StringToLocalDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * controller 基类
 */
public abstract class BasicController<T> {
  /**
   * spring 4 支持泛型注入
   */
  @Autowired
  protected T business;

  @InitBinder
  public void initBinder(WebDataBinder binder) {

  }
}
