package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * LocalTime 序列号器
 */
public class LocalTimeSerializer extends JsonSerializer<LocalTime> {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

  public Class<LocalTime> handledType() {
    return LocalTime.class;
  }

  @Override
  public void serialize(LocalTime value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
    jsonGenerator.writeString(FORMATTER.format(value));
  }
}
