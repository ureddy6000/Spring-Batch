package com.assignment.batch.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static String createDateYYYYMMddHHMMSS(){
        Date date = Calendar.getInstance().getTime();
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }
}
