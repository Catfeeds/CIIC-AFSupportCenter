package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 *
 * LocalDateTime 序列号器
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public Class<LocalDateTime> handledType() {
    return LocalDateTime.class;
  }

  @Override
  public void serialize(LocalDateTime value, JsonGenerator jsonGenerator, SerializerProvider provider)
      throws IOException {
    jsonGenerator.writeString(FORMATTER.format(value));
  }

}
