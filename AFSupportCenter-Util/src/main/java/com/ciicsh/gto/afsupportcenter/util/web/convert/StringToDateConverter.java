package com.ciicsh.gto.afsupportcenter.util.web.convert;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * 日期解析器，目前只支持 long to date
 */
public class StringToDateConverter implements Converter<String, Date> {

  protected final static Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);

  @Override
  public Date convert(String s) {
    if (!StringUtils.isNotBlank(s)) {
      return null;
    }

    if (s.matches("[0-9]\\d{12}")) {
      return new Date(Long.valueOf(s));
    }
    logger.warn("Date 转换失败，String:{}", s);
    // 其他日期类型根据需求扩展
    return null;
  }
}
