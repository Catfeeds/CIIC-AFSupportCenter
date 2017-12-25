package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import java.time.format.DateTimeFormatter;

/**
 * jdk 8 日期格式 DateTimeFormatter
 */
public class JSR310DateFormatter {

    public static final DateTimeFormatter LOCAL_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter LOCAL_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
}
