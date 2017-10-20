package com.ciicsh.gto.afsupportcenter.util.web.convert;

import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

/**
 * LocalDate 日期解析器
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public LocalDate convert(String s) {
    if (!StringUtils.isNotBlank(s)) {
      return null;
    }
    return LocalDate.parse(s, FORMATTER);
  }
}
