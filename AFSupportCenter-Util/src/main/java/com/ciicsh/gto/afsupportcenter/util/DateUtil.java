package com.ciicsh.gto.afsupportcenter.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Date;

public class DateUtil {

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

}
