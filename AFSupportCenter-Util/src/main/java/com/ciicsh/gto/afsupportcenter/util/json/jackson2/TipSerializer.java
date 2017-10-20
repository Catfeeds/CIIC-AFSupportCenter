package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import java.io.IOException;

import com.ciicsh.gto.afsupportcenter.util.tips.Tip;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Tip 序列号器
 */
public class TipSerializer extends JsonSerializer<Tip> {

  public Class<Tip> handledType() {
    return Tip.class;
  }

  @Override
  public void serialize(Tip value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
    if (value != null) {
      jsonGenerator.writeStartObject();
      // code 特殊处理
      // 数字也加引号，关闭
      jsonGenerator.disable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
      jsonGenerator.disable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);
      jsonGenerator.writeNumberField("code", value.getCode());
      // 数字也加引号，开启
      jsonGenerator.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
      jsonGenerator.enable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);
      jsonGenerator.writeStringField("message", value.getMessage());
      jsonGenerator.writeObjectField("data", value.getData());
      jsonGenerator.writeEndObject();
    }
  }

}
