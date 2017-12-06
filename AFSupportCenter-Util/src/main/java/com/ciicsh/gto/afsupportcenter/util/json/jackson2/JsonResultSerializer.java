package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Tip 序列号器
 */
public class JsonResultSerializer extends JsonSerializer<JsonResult> {

    public Class<JsonResult> handledType() {
        return JsonResult.class;
    }

    @Override
    public void serialize(JsonResult value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null) {
            jsonGenerator.writeStartObject();
            // code 特殊处理
            // 数字也加引号，关闭
            jsonGenerator.disable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
            jsonGenerator.disable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);
            jsonGenerator.writeNumberField("code", value.getCode());
            Long total = value.getTotal();
            if (total != null) {
                jsonGenerator.writeNumberField("total", total);
            }
            // 数字也加引号，开启
            jsonGenerator.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
            jsonGenerator.enable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);
            jsonGenerator.writeStringField("message", value.getMessage());
            jsonGenerator.writeObjectField("data", value.getData());
            jsonGenerator.writeEndObject();
        }
    }

}
