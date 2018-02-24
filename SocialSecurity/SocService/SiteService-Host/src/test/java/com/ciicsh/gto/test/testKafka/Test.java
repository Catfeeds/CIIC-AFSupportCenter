package com.ciicsh.gto.test.testKafka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {

        String date = "20170701";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date1 =simpleDateFormat.parse(date);
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
