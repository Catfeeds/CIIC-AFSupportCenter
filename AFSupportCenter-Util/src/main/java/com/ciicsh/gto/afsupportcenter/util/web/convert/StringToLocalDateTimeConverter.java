package com.ciicsh.gto.afsupportcenter.util.web.convert;

import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

/**
 * LocalDateTime 日期解析器
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @Override
  public LocalDateTime convert(String s) {
    if (!StringUtils.isNotBlank(s)) {
      return null;
    }
    return LocalDateTime.parse(s, FORMATTER);
  }
}
