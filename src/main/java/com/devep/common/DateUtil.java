package com.devep.common;

import java.util.Date;

public class DateUtil {

    public static long DateDiff(Date d1,Date d2){
        long diff = d2.getTime()-d1.getTime();
        return diff/1000*60*60*24;
    }
}
