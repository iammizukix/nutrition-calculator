package com.food.youeat.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private final static String yyyy_MM_dd = "uuuu-MM-dd";
    private final static String HH_mm_ss = "HH:mm:ss";
    private final static String SPACE = " ";

    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(yyyy_MM_dd));
    }

    public static LocalTime toLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(HH_mm_ss));
    }

}
