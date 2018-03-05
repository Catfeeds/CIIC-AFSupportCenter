package com.ciicsh.gto.test.testKafka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String date1 = simpleDateFormat.format(date);
            System.out.println(date1);
    }
}
