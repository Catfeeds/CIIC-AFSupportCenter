package com.ciicsh.gto.afsupportcenter.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Date;

public class DateUtil {

    //转换日期类型
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ChronoZonedDateTime<LocalDate> zonedDateTime = localDate.atStartOfDay(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

}
