package com.ciicsh.gto.afsupportcenter.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel 注解，用于 dto mapping
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ExcelSheet {

    /**
     * sheetName 工作表名称
     *
     * @return
     */
    String value();

    /**
     * 默认查询 field
     *
     * @return
     */
    boolean field() default true;

    /**
     * 扩展名 xls 2003 ，xlsx 2007
     *
     * @return
     */
    String ext() default "xlsx";
}
