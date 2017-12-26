package com.ciicsh.gto.afsupportcenter.util.web.convert;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * 空字符串 转 null
 */
public class EmptyToNullConverter implements Converter<String, String> {

  @Override
  public String convert(String s) {
    if (StringUtils.isEmpty(s)) {
      return null;
    }
    return s;

  }
}
