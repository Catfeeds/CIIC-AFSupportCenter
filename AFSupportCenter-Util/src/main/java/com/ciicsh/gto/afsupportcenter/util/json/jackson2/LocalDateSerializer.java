package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * LocalDate 序列号器
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public Class<LocalDate> handledType() {
    return LocalDate.class;
  }

  @Override
  public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
    jsonGenerator.writeString(FORMATTER.format(value));
  }
}
