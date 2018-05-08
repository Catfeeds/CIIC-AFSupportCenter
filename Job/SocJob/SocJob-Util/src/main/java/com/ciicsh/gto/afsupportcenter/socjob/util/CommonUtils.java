package com.ciicsh.gto.afsupportcenter.socjob.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by houwanhua on 2018/1/15.
 */
public class CommonUtils {
    public static List<String> getMonths(String start, String end) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(simpleDateFormat.parse(start));
        endCal.setTime(simpleDateFormat.parse(end));

        int result = endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);
        int month = (endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR)) * 12;
        int totalMonth = Math.abs(month + result);

        List<String> monthStrs = new ArrayList<>();

        if (totalMonth > 2) {
            monthStrs.add(start);
            totalMonth = totalMonth - 1;
            for (int i = 0; i < totalMonth; i++) {
                startCal.add(Calendar.MONTH, 1);
                String belongMonth = simpleDateFormat.format(startCal.getTime());
                monthStrs.add(belongMonth);
            }
            monthStrs.add(end);
        } else {
            if (totalMonth == 1) {
                monthStrs.add(start);
                monthStrs.add(end);
                //两条信息
            } else {
                monthStrs.add(start);
            }
        }
        return monthStrs;
    }

    /**
     * 支付年月：如果运行Job是每月16-最后一天，那么支付年月=系统时间的当月，如果运行Job是每月1-15，那么支付年月=系统时间的月份-1 （上月)
     *
     * @return
     */
    public static String getPaymentMonth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        if (day > 15) {
            return dateFormat.format(calendar.getTime());
        } else {
            calendar.add(Calendar.MONTH, -1);
            return dateFormat.format(calendar.getTime());
        }
    }
}
