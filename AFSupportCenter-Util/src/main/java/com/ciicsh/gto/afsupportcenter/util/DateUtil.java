package com.ciicsh.gto.afsupportcenter.util;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
    private static DateTimeFormatter yyyyMMCNformatter = DateTimeFormatter.ofPattern("uuuu年MM月");
    private static DateTimeFormatter yyyyMMddSlashFormatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    private static DateTimeFormatter yyyyMMddHyphenFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private static DateTimeFormatter yyyyMMddCNformatter = DateTimeFormatter.ofPattern("uuuu年MM月dd日");

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

    public static LocalDateTime dateToLocaleDateTime(Date date){
        if(date==null){
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if(localDateTime==null){
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static String plusMonth(String month, int months){
        YearMonth hfMonthDate = YearMonth.parse(month , formatter);
        return hfMonthDate.plusMonths(months).format(formatter);
    }

    public static String minusMonth(String month, int months){
        YearMonth hfMonthDate = YearMonth.parse(month , formatter);
        return hfMonthDate.minusMonths(months).format(formatter);
    }

    public static int compareMonth(String reference, String compared) {
        YearMonth referenceDate = YearMonth.parse(reference , formatter);
        YearMonth comparedDate = YearMonth.parse(compared , formatter);
        return referenceDate.compareTo(comparedDate);
    }

    public static String yyyyMMddHyphen(LocalDate localDate) {
        return localDate.format(yyyyMMddHyphenFormatter);
    }

    public static String yyyyMMddSlash(LocalDate localDate) {
        return localDate.format(yyyyMMddSlashFormatter);
    }

    public static String yyyyMMddCN(LocalDate localDate) {
        return localDate.format(yyyyMMddCNformatter);
    }

    public static String yyyyMMddCN(LocalDateTime localDateTime) {
        return localDateTime.format(yyyyMMddCNformatter);
    }

    public static String yyyyMMCN(String yyyyMM) {
        if (StringUtils.isEmpty(yyyyMM)) {
            return yyyyMM;
        }
        YearMonth monthDate = YearMonth.parse(yyyyMM , formatter);
        return monthDate.format(yyyyMMCNformatter);
    }
}
