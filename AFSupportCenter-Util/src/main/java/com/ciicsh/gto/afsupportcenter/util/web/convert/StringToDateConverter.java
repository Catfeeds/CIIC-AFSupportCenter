package com.ciicsh.gto.afsupportcenter.util.web.convert;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期解析器，目前只支持 long to date
 */
public class StringToDateConverter implements Converter<String, Date> {

    protected final static Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);
    protected final static String[] parsePatterns = {
        "yyyy-MM-dd'T'HH:mm:ss"
        , "yyyy-MM-dd HH:mm:ss"
        , "yyyy-MM-dd"
        , "HH:mm:ss"
    };

    @Override
    public Date convert(String s) {
        if (!StringUtils.isNotBlank(s)) {
            return null;
        }

        try {
            // 毫秒转换
            return new Date(Long.valueOf(s));
        } catch (NumberFormatException ignore) {
            try {
                return DateUtils.parseDate(s, parsePatterns);
            } catch (ParseException e) {
                logger.warn("Date 转换失败，String:{}", s);
                // 其他日期类型根据需求扩展
                return null;
            }
        }
    }
}
