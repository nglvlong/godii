package com.fpt.godii.application.utils;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtil {
    public static Timestamp now(){
        return new Timestamp(System.currentTimeMillis());
    }
    public static Timestamp toTimeStamp(Date date) {
        return new Timestamp(date.getTime());
    }
}
