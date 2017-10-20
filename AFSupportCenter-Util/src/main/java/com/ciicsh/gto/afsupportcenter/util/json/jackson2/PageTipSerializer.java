package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import java.io.IOException;

import com.ciicsh.gto.afsupportcenter.util.tips.PageTip;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * PageTip 序列号器
 */
public class PageTipSerializer extends JsonSerializer<PageTip> {

  public Class<PageTip> handledType() {
    return PageTip.class;
  }

  @Override
  public void serialize(PageTip value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
    if (value != null) {
      // total，code 特殊处理
      jsonGenerator.writeStartObject();
      // 数字也加引号，关闭
      jsonGenerator.disable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
      jsonGenerator.disable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);
      jsonGenerator.writeNumberField("total", value.getTotal());
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
