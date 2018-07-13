package com.ciicsh.gto.afsupportcenter.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
    private static DateTimeFormatter yyyyMMddHyphenFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    //转换日期类型
    public static Date localDateToDate(LocalDate localDate) {
        if(localDate==null){
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        ChronoZonedDateTime<LocalDate> zonedDateTime = localDate.atStartOfDay(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }
    public static LocalDate dateToLocaleDate(Date date) {
        if(date==null){
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId  = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }
    public static String plusMonth(String month, int months){
        YearMonth hfMonthDate = YearMonth.parse(month , formatter);
        return hfMonthDate.plusMonths(months).format(formatter);
    }

    public static String minusMonth(String month, int months){
        YearMonth hfMonthDate = YearMonth.parse(month , formatter);
        return hfMonthDate.minusMonths(months).format(formatter);
    }

    public static String yyyyMMddHyphen(LocalDate localDate) {
        return localDate.format(yyyyMMddHyphenFormatter);
    }
}
