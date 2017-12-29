package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class JacksonConfig {
    protected final Map<Class<?>, JsonSerializer<?>> serializerMap = new HashMap<>();
    protected final Map<Class<?>, JsonDeserializer<?>> deserializerMap = new HashMap<>();
    private ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JacksonConfig addSerializer(JsonSerializer<?> ser) {
        serializerMap.put(ser.handledType(), ser);
        return this;
    }

    public <T> JacksonConfig addDeserializer(Class<T> type, JsonDeserializer<? extends T> dser) {
        deserializerMap.put(type, dser);
        return this;
    }

    public ObjectMapper build() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        SimpleModule simpleModule = new SimpleModule();

        handleObjectMapper(objectMapper);
        handleSerializer();
        handleDeserializer();

        deserializerMap.forEach((k, v) -> simpleModule.addDeserializer((Class) k, v));
        serializerMap.forEach((k, v) -> simpleModule.addSerializer(v));

        // json 过滤，如 @RequestBody、@ResponseBody
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    /**
     * 处理 Deserializer
     */
    protected void handleDeserializer() {
        addDeserializer(LocalDate.class, new LocalDateDeserializer(JSR310DateFormatter.LOCAL_DATE));
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(JSR310DateFormatter.LOCAL_DATE_TIME));
        addDeserializer(LocalTime.class, new LocalTimeDeserializer(JSR310DateFormatter.LOCAL_TIME));
    }


    /**
     * 处理 Serializer
     */
    protected void handleSerializer() {
        addSerializer(new LocalDateSerializer(JSR310DateFormatter.LOCAL_DATE));
        addSerializer(new LocalDateTimeSerializer(JSR310DateFormatter.LOCAL_DATE_TIME));
        addSerializer(new LocalTimeSerializer(JSR310DateFormatter.LOCAL_TIME));
        addSerializer(JsonResultSerializer.of(JsonResult.class));
    }

    /**
     * 处理 ObjectMapper
     *
     * @param objectMapper
     */
    protected void handleObjectMapper(ObjectMapper objectMapper) {
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
        // Include.NON_NULL 属性为NULL 不序列化
        // 注意：只对 bean 起作用，Map List 不起作用
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 允许单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 字段和值都加引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 数字也加引号
        objectMapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        objectMapper.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
        // 忽略多余字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}
