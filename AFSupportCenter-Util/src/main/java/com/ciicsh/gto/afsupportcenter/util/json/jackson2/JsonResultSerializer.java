package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

/**
 * JsonResult 序列号器
 */
public class JsonResultSerializer extends JsonSerializer<Object> {

    private final Class<Object> type;

    public JsonResultSerializer(Class<Object> type) {
        this.type = type;
    }

    public static JsonResultSerializer of(SimpleModule simpleModule, Class<?> type) {
        if (JsonResultKit.isJsonResult(type)) {
            JsonResultSerializer serializer = new JsonResultSerializer((Class<Object>) type);
            simpleModule.addSerializer(serializer);
            return serializer;
        }
        throw BusinessException.unchecked("当前类型结构不是 JsonResult,class:" + type.getName());
    }

    public Class<Object> handledType() {
        return type;
    }

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null) {
            JsonResult jsonResult = JsonKit.castToObject(value, JsonResult.class);
            jsonGenerator.writeStartObject();
            // code 特殊处理
            // 数字也加引号，关闭
            jsonGenerator.disable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
            jsonGenerator.disable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);
            jsonGenerator.writeNumberField("code", jsonResult.getCode());
            Long total = jsonResult.getTotal();
            if (total != null) {
                jsonGenerator.writeNumberField("total", total);
            }
            // 数字也加引号，开启
            jsonGenerator.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
            jsonGenerator.enable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);
            jsonGenerator.writeStringField("message", jsonResult.getMessage());
            jsonGenerator.writeObjectField("data", jsonResult.getData());
            jsonGenerator.writeEndObject();
        }
    }

}
