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
    public static List<String> getMonths(String start,String end) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(simpleDateFormat.parse(start));
        endCal.setTime(simpleDateFormat.parse(end));

        int result = endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);
        int month = (endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR)) * 12;
        int totalMonth = Math.abs(month + result);

        List<String> monthStrs = new ArrayList<>();

        if(totalMonth > 2){
            monthStrs.add(start);
            totalMonth = totalMonth -1;
            for (int i = 0;i<totalMonth;i++){
                startCal.add(Calendar.MONTH,1);
                String belongMonth = simpleDateFormat.format(startCal.getTime());
                monthStrs.add(belongMonth);
            }
            monthStrs.add(end);
        }
        else{
            if(totalMonth == 1){
                monthStrs.add(start);
                monthStrs.add(end);
                //两条信息
            }
            else{
                monthStrs.add(start);
            }
        }
        return monthStrs;
    }
}
