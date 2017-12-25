package com.ciicsh.gto.afsupportcenter.util.config;

import com.ciicsh.gto.afsupportcenter.util.aspect.json.JsonResultAspect;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.LogAspect;
import com.ciicsh.gto.afsupportcenter.util.aspect.param.RequestParamValidAspect;
import com.ciicsh.gto.afsupportcenter.util.context.SpringContextHolder;
import com.ciicsh.gto.afsupportcenter.util.context.annotation.CustomByNameCondition;
import com.ciicsh.gto.afsupportcenter.util.context.annotation.CustomByTypeCondition;
import com.ciicsh.gto.afsupportcenter.util.json.jackson2.JSR310DateFormatter;
import com.ciicsh.gto.afsupportcenter.util.json.jackson2.JsonResultSerializer;
import com.ciicsh.gto.afsupportcenter.util.json.jackson2.WafJsonSerializer;
import com.ciicsh.gto.afsupportcenter.util.web.convert.EmptyToNullConverter;
import com.ciicsh.gto.afsupportcenter.util.web.convert.StringToLocalDateConverter;
import com.ciicsh.gto.afsupportcenter.util.web.convert.StringToLocalDateTimeConverter;
import com.ciicsh.gto.afsupportcenter.util.web.convert.StringToLocalTimeConverter;
import com.ciicsh.gto.afsupportcenter.util.web.filter.WafFilter;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 自定义自动化配置
 */
public class CustomConfiguration {

    // -----------------------------------------------------------------------------
    // --------------------------------- type -------------------------------------
    // -----------------------------------------------------------------------------
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedHeaders("*")
                    .allowedMethods("*")
                    .allowedOrigins("*");
            }
        };
    }

    // -----------------------------------
    // ---------- Listener -------------
    // -----------------------------------
    /**
     * reqponse 响应参数序列号
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = create();
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return jackson2HttpMessageConverter;
    }

    // ----------------------------------------------
    // ---- Converter （request 请求参数转换器） --------
    // -----------------------------------------------

    @Bean
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public StringToLocalDateConverter stringToLocalDateConverter() {
        return new StringToLocalDateConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public StringToLocalDateTimeConverter stringToLocalDateTimeConverter() {
        return new StringToLocalDateTimeConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public StringToLocalTimeConverter stringToLocalTimeConverter() {
        return new StringToLocalTimeConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public EmptyToNullConverter emptyToNullConverter() {
        return new EmptyToNullConverter();
    }

    // -----------------------------------
    // ------------- aop ----------------
    // -----------------------------------


    @Bean
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public RequestParamValidAspect requestParamValidAspect() {
        return new RequestParamValidAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public LogAspect logAspect() {
        return new LogAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public JsonResultAspect jsonResultAspect() {
        return new JsonResultAspect();
    }

    @Bean
    @Lazy(false)
    @ConditionalOnMissingBean
    @Conditional(CustomByTypeCondition.class)
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    // -----------------------------------------------------------------------------
    // --------------------------------- name -------------------------------------
    // -----------------------------------------------------------------------------

    /**
     * 请求过滤 Filter
     *
     * @return
     */
    @Bean
    @Conditional(CustomByNameCondition.class)
    public FilterRegistrationBean wafFilter() {
        FilterRegistrationBean reg = new FilterRegistrationBean();
        reg.setFilter(new WafFilter(true, true));
        reg.addUrlPatterns("/api/*");
        return reg;
    }

    private static ObjectMapper create() {
        ObjectMapper objectMapper = new ObjectMapper();
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

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(String.class, new WafJsonSerializer());
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(JSR310DateFormatter.LOCAL_DATE));
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(JSR310DateFormatter.LOCAL_DATE_TIME));
        simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(JSR310DateFormatter.LOCAL_TIME));
        JsonResultSerializer.of(simpleModule, JsonResult.class);

        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(JSR310DateFormatter.LOCAL_DATE));
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(JSR310DateFormatter.LOCAL_DATE_TIME));
        simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(JSR310DateFormatter.LOCAL_TIME));
        // json 过滤，如 @RequestBody、@ResponseBody
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
