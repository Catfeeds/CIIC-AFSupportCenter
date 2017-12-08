package com.ciicsh.gto.afsupportcenter.util.kit;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateKit {

    public static final String[] yyyyMMddHHmmss = {
        "yyyy-MM-dd'T'HH:mm:ss"
        , "yyyy-MM-dd HH:mm:ss"
        , "yyyy/MM/dd HH-mm-ss"
        , "yyyy-MM-dd HH-mm-ss"
        , "yyyy年MM月dd日HH时mm分ss秒"
        , "yyyyMMddHHmmss"
    };

    public static final String[] yyyyMMdd = {
        "yyyy-MM-dd"
        , "yyyy/MM/dd"
        , "yyyyMMdd"
        , "yyyy年MM月dd日"
    };

    public static Date parseDate(String str) {
        try {
            return DateUtils.parseDate(str, ArrayUtils.addAll(yyyyMMddHHmmss, yyyyMMdd));
        } catch (ParseException e) {
            throw BusinessException.unchecked("日期解析错误：" + str);
        }
    }

    public static LocalDateTime parseLocalDateTime(String str) {
        return LocalDateTime.ofInstant(parseDate(str).toInstant(), ZoneId.systemDefault());
    }
}
