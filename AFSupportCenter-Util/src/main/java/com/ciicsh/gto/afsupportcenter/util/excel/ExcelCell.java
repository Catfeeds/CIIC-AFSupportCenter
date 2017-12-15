package com.ciicsh.gto.afsupportcenter.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel 注解，用于 dto mapping
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ExcelCell {

    /**
     * 标题
     */
    String value();

    /**
     * 转换器
     */
    Class<? extends ExcelConvert> converter() default ExcelConvert.class;

    /**
     * 序号
     */
    int order() default 9999;
}
