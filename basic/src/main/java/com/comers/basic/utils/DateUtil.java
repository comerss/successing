package com.comers.basic.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.hyphenate.util.TimeInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * DateUtil Description: 日期工具类
 *
 * @author Lpzahd
 * @date 2015-11-21 上午10:16:51
 */
public class DateUtil {

    /**
     * 获取两年之间月份总和
     *
     * @param sYear
     * @param eYear
     * @return
     */
    public static int getMonthOfYears(int sYear, int eYear) {
        return (eYear - sYear) * 12;
    }


    /**
     * 判断该年份是不是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeepYear(int year) {
        boolean isLeep = false;
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            isLeep = true;
        }
        return isLeep;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss字符串转换成日期<br/>
     *
     * @param dateStr    时间字符串
     * @param dataFormat 当前时间字符串的格式。
     * @return Date 日期 ,转换异常时返回null。
     */
    public static Date parseDate(String dateStr, String dataFormat) {
        try {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat dateFormat = new SimpleDateFormat(dataFormat);
            Date date = dateFormat.parse(dateStr);
            return date;
        } catch (Exception e) {
            return new Date();
        }
    }

    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        return str;
    }

    /**
     * 获取当前时间的年月日时分秒
     */
    public static String getNowDate() {
        try {
            return getFormatDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前时间的年月日
     */
    public static String getNowDates() {
        try {
            return getFormatDate(System.currentTimeMillis(), "yyyy-MM-dd");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 这是一个获取当前的年月日的方法
     *
     * @return
     */
    public static String getNowYearMonthDay() {
        try {
            return getFormatDate(System.currentTimeMillis(), "yyyy-MM-dd");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 这是一个给定特殊的日期格式,返回一个把毫秒值转换为字符串的日期
     *
     * @param date         给定日期毫秒值
     * @param formatString 给定需要格式化的日期格式例如:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getFormatDate(long date, String formatString) {
        String formatData;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        formatData = simpleDateFormat.format(date);
        return formatData;
    }

    /**
     * 这是一个处理时间的一个工具类
     *
     * @param time
     * @return 年月日
     */
    public static String subTime(String time) {
        String date = time.substring(0, 10);
        String times = time.substring(11, 19);
        return date + "-" + times;
    }

    /**
     * 返回一个年月日的方法
     *
     * @param time
     * @return 年月日
     */
    public static String getYearMonthDay(String time) {
        String date = null;
        if (!TextUtils.isEmpty(time)) {
            date = time.substring(0, 10);
        } else {
            date = "";
        }
        return date;
    }

    /**
     * 这是一个获取时分秒的方法
     *
     * @param time
     * @return 时分秒
     */
    public static String getTime(String time) {
        String date = time.substring(11, 19);
        return date;
    }

    /**
     * 获取时分的方法
     *
     * @return 时分
     */
    public static String getHourMinutes(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        String date = time.substring(11, 16);
        return date;
    }

    /**
     * 获取时的方法
     *
     * @return 时
     */
    public static String getHour(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        String date = time.substring(11, 13);
        return date;
    }

    /**
     * 获取分的方法
     *
     * @return 分
     */
    public static String getMinutes(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        String date = time.substring(14, 16);
        return date;
    }

    /**
     * 这是一个获取年的方法
     *
     * @param time
     * @return 年
     */
    public static String getyear(String time) {
        String date = time.substring(0, 4);
        return date;
    }

    /**
     * 这是一个获取月的方法
     *
     * @param time
     * @return 月
     */
    public static String getMonth(String time) {
        String date = time.substring(5, 7);
        return date;
    }

    /**
     * 这是一个获取日的方法
     *
     * @param time
     * @return 日
     */
    public static String getday(String time) {
        String date = time.substring(8, 10);
        return date;
    }

    /**
     * 获取月日
     *
     * @param time
     * @return
     */
    public static String getMonthDay(String time) {
        String date = time.substring(5, 10);
        return date;
    }

    /**
     * 获取该年份的天数
     *
     * @param year
     * @return
     */
    public static int getDaysInYear(int year) {
        int days;
        if (isLeepYear(year)) {
            days = 366;
        } else {
            days = 365;
        }
        return days;
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 选中的日期yyyy-MM-dd
     * @return 周几
     */
    public static String dayForWeek(String pTime) {
        String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return weekDays[dayForWeek - 1];
    }


    public static String getTimestampString(Date var0) {
        String var1 = null;
        String var2 = Locale.getDefault().getLanguage();
        boolean var3 = var2.startsWith("zh");
        long var4 = var0.getTime();
        if (isSameDay(var4)) {
            if (var3) {
                var1 = "aa HH:mm";
            } else {
                var1 = "HH:mm aa";
            }
        } else if (isYesterday(var4)) {
            if (!var3) {
                return "Yesterday " + (new SimpleDateFormat("HH:mm aa", Locale.ENGLISH)).format(var0);
            }

            var1 = "昨天aa HH:mm";
        } else if (var3) {
            var1 = "M月d日aa HH:mm";
        } else {
            var1 = "MMM dd HH:mm aa";
        }

        return var3 ? (new SimpleDateFormat(var1, Locale.CHINESE)).format(var0) : (new SimpleDateFormat(var1, Locale.ENGLISH)).format(var0);
    }

    public static boolean isCloseEnough(long var0, long var2) {
        long var4 = var0 - var2;
        if (var4 < 0L) {
            var4 = -var4;
        }

        return var4 < 30000L;
    }

    private static boolean isSameDay(long var0) {
        TimeInfo var2 = getTodayStartAndEndTime();
        return var0 > var2.getStartTime() && var0 < var2.getEndTime();
    }

    private static boolean isYesterday(long var0) {
        TimeInfo var2 = getYesterdayStartAndEndTime();
        return var0 > var2.getStartTime() && var0 < var2.getEndTime();
    }

    @SuppressLint({"SimpleDateFormat"})
    public static Date StringToDate(String var0, String var1) {
        SimpleDateFormat var2 = new SimpleDateFormat(var1);
        Date var3 = null;

        try {
            var3 = var2.parse(var0);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return var3;
    }

    private static final long INTERVAL_IN_MILLISECONDS = 30000L;

    @SuppressLint({"DefaultLocale"})
    public static String toTime(int var0) {
        var0 /= 1000;
        int var1 = var0 / 60;
        boolean var2 = false;
        if (var1 >= 60) {
            int var4 = var1 / 60;
            var1 %= 60;
        }

        int var3 = var0 % 60;
        return String.format("%02d:%02d", new Object[]{Integer.valueOf(var1), Integer.valueOf(var3)});
    }

    @SuppressLint({"DefaultLocale"})
    public static String toTimeBySecond(int var0) {
        int var1 = var0 / 60;
        boolean var2 = false;
        if (var1 >= 60) {
            int var4 = var1 / 60;
            var1 %= 60;
        }

        int var3 = var0 % 60;
        return String.format("%02d:%02d", new Object[]{Integer.valueOf(var1), Integer.valueOf(var3)});
    }

    public static TimeInfo getYesterdayStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.add(5, -1);
        var0.set(11, 0);
        var0.set(12, 0);
        var0.set(13, 0);
        var0.set(14, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        Calendar var4 = Calendar.getInstance();
        var4.add(5, -1);
        var4.set(11, 23);
        var4.set(12, 59);
        var4.set(13, 59);
        var4.set(14, 999);
        Date var5 = var4.getTime();
        long var6 = var5.getTime();
        TimeInfo var8 = new TimeInfo();
        var8.setStartTime(var2);
        var8.setEndTime(var6);
        return var8;
    }

    public static TimeInfo getTodayStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.set(11, 0);
        var0.set(12, 0);
        var0.set(13, 0);
        var0.set(14, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        Calendar var4 = Calendar.getInstance();
        var4.set(11, 23);
        var4.set(12, 59);
        var4.set(13, 59);
        var4.set(14, 999);
        Date var5 = var4.getTime();
        long var6 = var5.getTime();
        TimeInfo var8 = new TimeInfo();
        var8.setStartTime(var2);
        var8.setEndTime(var6);
        return var8;
    }

    public static TimeInfo getBeforeYesterdayStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.add(5, -2);
        var0.set(11, 0);
        var0.set(12, 0);
        var0.set(13, 0);
        var0.set(14, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        Calendar var4 = Calendar.getInstance();
        var4.add(5, -2);
        var4.set(11, 23);
        var4.set(12, 59);
        var4.set(13, 59);
        var4.set(14, 999);
        Date var5 = var4.getTime();
        long var6 = var5.getTime();
        TimeInfo var8 = new TimeInfo();
        var8.setStartTime(var2);
        var8.setEndTime(var6);
        return var8;
    }

    public static TimeInfo getCurrentMonthStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.set(5, 1);
        var0.set(11, 0);
        var0.set(12, 0);
        var0.set(13, 0);
        var0.set(14, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        Calendar var4 = Calendar.getInstance();
        Date var5 = var4.getTime();
        long var6 = var5.getTime();
        TimeInfo var8 = new TimeInfo();
        var8.setStartTime(var2);
        var8.setEndTime(var6);
        return var8;
    }

    public static TimeInfo getLastMonthStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.add(2, -1);
        var0.set(5, 1);
        var0.set(11, 0);
        var0.set(12, 0);
        var0.set(13, 0);
        var0.set(14, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        Calendar var4 = Calendar.getInstance();
        var4.add(2, -1);
        var4.set(5, 1);
        var4.set(11, 23);
        var4.set(12, 59);
        var4.set(13, 59);
        var4.set(14, 999);
        var4.roll(5, -1);
        Date var5 = var4.getTime();
        long var6 = var5.getTime();
        TimeInfo var8 = new TimeInfo();
        var8.setStartTime(var2);
        var8.setEndTime(var6);
        return var8;
    }

    public static String getTimestampStr() {
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static String getNowMonth() {
        return String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
    }

    public static String getNowDay() {
        return String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 是否大于当前时间
     *
     * @return
     */
    public static boolean isbefoNowDate(Date date) {
        return date.getTime() > System.currentTimeMillis();
    }

    /**
     * 减去指定天数的工具类
     *
     * @param startime 开始的时间
     * @param days     减去的天数
     */
    public static String toDeletedays(String startime, int days) {
        long starttime = parseDate(startime, "yyyy-MM-dd").getTime();
        long todeletetime = days * 24l * 60 * 60l * 1000l;
        return getDate(new Date(starttime - todeletetime));
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getNowYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
