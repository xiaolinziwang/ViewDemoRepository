package com.zhugefang.viewdemo.tools;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sks on 2016/5/29.
 * <p/>
 * 常用工具类
 */
public class TimeUtil {

    private static final long DAY = 24 * 60 * 60 * 1000;
    static SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    static SimpleDateFormat format1 = new SimpleDateFormat("MM-dd HH:mm");

    //将格林威治时间转换为日期格式
    public static String GTMtoLocal(String GTMDate, SimpleDateFormat dateFormat) {
        try {
            if (!TextUtils.isEmpty(GTMDate) && TextUtils.isDigitsOnly(GTMDate)) {
                Date date = new Date(Long.parseLong(GTMDate));
                return dateFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //将格林威治时间转换为日期格式
    public static String GTMtoLocal(String GTMDate) {
        try {
            if (!TextUtils.isEmpty(GTMDate) && TextUtils.isDigitsOnly(GTMDate)) {
                Date date = new Date(Long.parseLong(GTMDate));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String YearMonthDay(String GTMDate) {
        try {
            if (!TextUtils.isEmpty(GTMDate) && TextUtils.isDigitsOnly(GTMDate)) {
                Date date = new Date(Long.parseLong(GTMDate));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                return dateFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String GTMtoLocalmm(String GTMDate) {
        try {
            if (!TextUtils.isEmpty(GTMDate) && TextUtils.isDigitsOnly(GTMDate)) {
                Date date = new Date(Long.parseLong(GTMDate));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return dateFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String timeTransfrom(String GTMDate) {
        try {
            if (!TextUtils.isEmpty(GTMDate) && TextUtils.isDigitsOnly(GTMDate)) {
                Date date = new Date(Long.parseLong(GTMDate));
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
                return dateFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 角标 0 最大值 1 最小值
     * <p/>
     * <<<<<<< HEAD
     *
     * @param num,flag租房除以10，整租除以100
     * @return
     */
    public static int num2Max(int num, boolean flag) {
        int unit = 100;
        if (flag) {//zh整租房
            unit = 10;
        }
        float f = num / unit;
        int ceil = (int) Math.ceil(f);
        return (ceil + 1) * unit;
    }

    public static int num2Min(int num, boolean flag) {
        int unit = 100;
        if (flag) {//是整租房
            unit = 10;
        }
        float f = num / unit;
        int floor = (int) Math.floor(f);

        return floor * unit;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒  <br>
     * 时间参数为 Unix时间戳
     *
     * @param str1 时间参数 1 格式：1407132214
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1) {
        float day = 0;
        try {
            if (!TextUtils.isEmpty(str1) && TextUtils.isDigitsOnly(str1)) {
                long time1 = Long.parseLong(str1) * 1000;
//                long time2 = Long.parseLong(str2);
                Calendar current = Calendar.getInstance();
                Calendar today = Calendar.getInstance();    //今天

                today.set(Calendar.YEAR, current.get(Calendar.YEAR));
                today.set(Calendar.MONTH, current.get(Calendar.MONTH));
                today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
                //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
                today.set(Calendar.HOUR_OF_DAY, 0);
                today.set(Calendar.MINUTE, 0);
                today.set(Calendar.SECOND, 0);

                long diff;
                diff = today.getTimeInMillis() - time1;
                day = ((float) diff / (24 * 60 * 60 * 1000));

                if (day < 0) {
                    return "今日";
                }
                return ((int) day + 1) + "天前";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "今日";
    }

    public static String formatHouseUpdateTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (TextUtils.isEmpty(time) || !TextUtils.isDigitsOnly(time)) {
            return "今日";
        }
        Date date = null;
        try {
            long l = Long.parseLong(time);
            date = new Date(l);
        } catch (Exception e) {
            e.printStackTrace();
            return "今日";
        }

        Calendar current = Calendar.getInstance();

        Calendar today = Calendar.getInstance();    //今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();    //昨天
        yesterday.setTimeInMillis(today.getTimeInMillis() - DAY);

        current.setTime(date);

        if (current.after(today)) {
            return "今日";
        } else {
            return format.format(date);
        }
    }

    public static String formatHouseUpdateTimeRongYun(String time) {
        if (TextUtils.isEmpty(time) || !TextUtils.isDigitsOnly(time)) {
            return "今天 00:00";
        }
        Date date = null;
        try {
            long l = Long.parseLong(time);
            date = new Date(l);
        } catch (Exception e) {
            e.printStackTrace();
            return "今天 00:00";
        }

        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();    //昨天
        yesterday.setTimeInMillis(today.getTimeInMillis() - DAY);

        current.setTime(date);

        if (current.after(today)) {
            return "今天 " + format.format(date);
        } else if (current.before(today) && current.after(yesterday)) {
            return "昨天 " + format.format(date);
        } else {
            return format1.format(date);
        }
    }

    /**
     * 判读是否是今日
     *
     * @param time
     * @return
     */
    public static boolean isToday(String time) {
        if (TextUtils.isEmpty(time) || !TextUtils.isDigitsOnly(time))
            return false;
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        long timeInMillis = today.getTimeInMillis();
        String todayStr = timeInMillis + "";
        return todayStr.compareToIgnoreCase(time) <= 0;
    }

    public static long getTodayLinChen() {
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        return today.getTimeInMillis();
    }

    /**
     * 返回day天前的日期
     *
     * @param day 0 今天凌晨       1 昨天
     * @return
     */
    public static long agoTime(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -day);
        return calendar.getTimeInMillis();
    }
}
