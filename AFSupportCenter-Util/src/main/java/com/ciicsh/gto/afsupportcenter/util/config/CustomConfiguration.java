package com.ciicsh.gto.afsupportcenter.util.config;

import com.ciicsh.gto.afsupportcenter.util.aspect.json.JsonResultAspect;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.LogAspect;
import com.ciicsh.gto.afsupportcenter.util.context.SpringContextHolder;
import com.ciicsh.gto.afsupportcenter.util.context.annotation.CustomByNameCondition;
import com.ciicsh.gto.afsupportcenter.util.context.annotation.CustomByTypeCondition;
import com.ciicsh.gto.afsupportcenter.util.json.jackson2.*;
import com.ciicsh.gto.afsupportcenter.util.web.convert.EmptyToNullConverter;
import com.ciicsh.gto.afsupportcenter.util.web.convert.StringToLocalDateConverter;
import com.ciicsh.gto.afsupportcenter.util.web.convert.StringToLocalDateTimeConverter;
import com.ciicsh.gto.afsupportcenter.util.web.convert.StringToLocalTimeConverter;
import com.ciicsh.gto.afsupportcenter.util.web.filter.WafFilter;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
        ObjectMapper objectMapper = new ObjectMapper();

        // 特殊处理属性名称，如：SSPolicyId
        objectMapper.setPropertyNamingStrategy(new CustomPropertyNamingStrategy());

        // 允许单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 字段和值都加引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 数字也加引号
        objectMapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        objectMapper.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);

        // json 过滤，如 @RequestBody、@ResponseBody
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(String.class, new WafJsonSerializer());
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer());
        simpleModule.addSerializer(JsonResult.class, new JsonResultSerializer());
        objectMapper.registerModule(simpleModule);

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
}
