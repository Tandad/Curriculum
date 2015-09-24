package com.ss.task.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by liymm on 2015-01-25.
 */
public class Util {

    static public boolean invalidString(String s) {
        return (s==null) || "".equals(s);
    }

    /* utility */
    public final static SimpleDateFormat dtsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    static public Timestamp parseTime(String time) {
        try {
            return new Timestamp(dtsdf.parse(time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public final static SimpleDateFormat dsdf = new SimpleDateFormat("yyyy-MM-dd");

    static public java.sql.Date parseDate(String date) {
        try {
            return new java.sql.Date(dsdf.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    static public Timestamp getCurrentTime() {
        return new Timestamp(new Date().getTime());
    }
    static public java.sql.Date getCurrentDate() {
        return new java.sql.Date(new Date().getTime());
    }

    static public java.sql.Date getFirstDayOfMonth(long timeInMillis) {
        Calendar temp = new GregorianCalendar();
        temp.setTimeInMillis(timeInMillis);
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        temp.set(Calendar.SECOND, 0);
        temp.set(Calendar.MILLISECOND, 0);
        temp.set(Calendar.DAY_OF_MONTH, 1);
        temp.setFirstDayOfWeek(Calendar.MONDAY);
        temp.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return new java.sql.Date(temp.getTimeInMillis());
    }

    static public java.sql.Date getLastDayOfMonth(long timeInMillis) {
        Calendar temp = new GregorianCalendar();
        temp.setTimeInMillis(timeInMillis);
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        temp.set(Calendar.SECOND, 0);
        temp.set(Calendar.MILLISECOND, 0);
        temp.set(Calendar.DAY_OF_MONTH, temp.getActualMaximum(Calendar.DAY_OF_MONTH));

        if (temp.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            temp.add(Calendar.DATE, -Calendar.DAY_OF_WEEK);
            temp.setFirstDayOfWeek(Calendar.MONDAY);
            temp.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }

        return new java.sql.Date(temp.getTimeInMillis());
    }

    static public java.sql.Date getFirstDayOfWeek(long timeInMillis) {
        Calendar temp = new GregorianCalendar();
        temp.setTimeInMillis(timeInMillis);
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        temp.set(Calendar.SECOND, 0);
        temp.set(Calendar.MILLISECOND, 0);
        temp.setFirstDayOfWeek(Calendar.MONDAY);
        temp.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new java.sql.Date(temp.getTimeInMillis());
    }

    static public java.sql.Date getLastDayOfWeek(long timeInMillis) {
        Calendar temp = new GregorianCalendar();
        temp.setTimeInMillis(timeInMillis);
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        temp.set(Calendar.SECOND, 0);
        temp.set(Calendar.MILLISECOND, 0);
        temp.setFirstDayOfWeek(Calendar.MONDAY);
        temp.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new java.sql.Date(temp.getTimeInMillis());
    }
}
