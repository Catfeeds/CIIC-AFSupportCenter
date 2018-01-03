package com.ciicsh.gto.afsupportcenter.util.web.convert;

import org.apache.commons.lang3.StringUtils;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

/**
 * LocalTime 日期解析器
 */
public class StringToLocalTimeConverter implements Converter<String, LocalTime> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

  @Override
  public LocalTime convert(String s) {
    if (!StringUtils.isNotBlank(s)) {
      return null;
    }
    return LocalTime.parse(s, FORMATTER);
  }
}
