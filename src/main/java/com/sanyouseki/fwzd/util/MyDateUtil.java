package com.sanyouseki.fwzd.util;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtil extends SimpleDateFormat {
    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private Date date;

    public MyDateUtil(){
        date = new Date();
        this.applyPattern(DATE_FORMAT);
    }

    public MyDateUtil(String dateFormat){
        date = new Date();
        this.applyPattern(dateFormat);
    }

    public String getFormatDate(){
        return format(this.date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
