package com.fanc.wheretoplay.util;


import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * <p>时间转换工具类</p>
 */
public class DateFormatUtil {

    /**
     * 格式：yyyy-MM-dd
     */
    public static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 格式：yyyyMMdd
     */
    public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    /**
     * 格式：yyyy/MM/dd
     */
    public static SimpleDateFormat _yyyy_MM_dd = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    public static SimpleDateFormat yMd_Hms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat yMd_Hm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * 格式：yyyy/MM/dd HH:mm:ss
     */
    public static SimpleDateFormat _yMd_Hms = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    /**
     * 格式：yyyyMMddHHmmss
     */
    private static SimpleDateFormat yMdHms = new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     * 格式：yyyy.MM.dd
     */
    public static SimpleDateFormat yMd = new SimpleDateFormat("yyyy.MM.dd");

    /**
     * 格式：yyyyMM
     */
    public static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
    /**
     * 格式：HH:mm:ss
     */
    public static SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");
    /**
     * 格式：HH:mm
     */
    public static SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");
    /**
     * 格式：mm:ss
     */
    public static SimpleDateFormat mmss = new SimpleDateFormat("mm:ss");
    /**
     * 格式：MM.dd
     */
    public static SimpleDateFormat MMdd = new SimpleDateFormat("MM.dd");
    private static Date dt2;

    /**
     * 日期字符串解析成Date
     *
     * @param dateString
     * @param simpleDateFormat
     * @return
     */
    public static Date parseStringToDate(String dateString, SimpleDateFormat simpleDateFormat) {
        try {
            if (TextUtils.isEmpty(dateString)) {
                return null;
            }
            if (simpleDateFormat == null) {
                return null;
            }

            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期字符串解析成long
     *
     * @param dateString
     * @param simpleDateFormat
     * @return
     */
    public static long parseStringTolong(String dateString, SimpleDateFormat simpleDateFormat) {
        try {
            if (TextUtils.isEmpty(dateString)) {
                return 0;
            }
            if (simpleDateFormat == null) {
                return 0;
            }

            return simpleDateFormat.parse(dateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 指定时间所在季度的第一天
     *
     * @param time
     * @return
     */
    public static String getFirstDayOfQuarter(long time) {
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = getQuarterInMonth(calendar.get(Calendar.MONTH), true);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDateStr(calendar.getTime());
    }

    /**
     * 指定时间所在季度的最后一天
     *
     * @param time
     * @return
     */
    public static String getLastDayOfQuarter(long time) {
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = getQuarterInMonth(calendar.get(Calendar.MONTH), false);
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return getDateStr(calendar.getTime());
    }

    /**
     * 返回第几个月份，不是几月
     * 季度一年四季， 第一季度：1月-3月， 第二季度：4月-6月， 第三季度：7月-9月， 第四季度：10月-11月
     *
     * @param month
     * @param isQuarterStart
     * @return
     */
    public static int getQuarterInMonth(int month, boolean isQuarterStart) {
        int months[] = {0, 3, 6, 9};
        if (!isQuarterStart) {
            months = new int[]{2, 5, 8, 11};
        }
        if (month >= 0 && month <= 2)
            return months[0];
        else if (month >= 3 && month <= 5)
            return months[1];
        else if (month >= 6 && month <= 8)
            return months[2];
        else
            return months[3];
    }

    /**
     * 获取指定时间，当月的第一天
     *
     * @param time
     * @return
     */
    public static String getFirstDayOfMonth(long time) {
        Calendar firstDate = Calendar.getInstance();
        firstDate.setTime(new Date(time));

        firstDate.set(Calendar.DAY_OF_MONTH, 1);

        return yyyy_MM_dd.format(firstDate.getTime());
    }

    /**
     * 获取指定时间，当月的最后一天
     *
     * @param time
     * @return
     */
    public static String getLastDayOfMonth(long time) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(new Date(time));

        lastDate.set(Calendar.DAY_OF_MONTH, 1);
        lastDate.add(Calendar.MONTH, 1);
        lastDate.add(Calendar.DATE, -1);

        return yyyy_MM_dd.format(lastDate.getTime());
    }

    /**
     * 获取指定时间在一年中的第几周
     *
     * @param time
     * @return
     */
    public static int getWeeks(long time) {
        Calendar week = Calendar.getInstance();
        week.setTime(new Date(time));

        return week.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前日期是星期几
     *
     * @return
     */
    public static String getCustomDayOfWeek() {
        Calendar week = Calendar.getInstance();
        week.setTime(new Date());
        int day = week.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";

        }
        return null;
    }

    /**
     * 获取当前日期是星期几
     *
     * @param date 日期
     * @return
     */
    public static String getCustomDayOfWeek(Date date) {
        Calendar week = Calendar.getInstance();
        week.setTime(date);
        int day = week.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";

        }
        return null;
    }

    /**
     * 获取当前时间所在周的开始日期
     *
     * @param time
     * @return
     */
    public static String getFirstDayOfWeek(long time) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(new Date(time));
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return getDateStr(c.getTime());
    }

    /**
     * 获取当前时间所在周的结束日期
     *
     * @param time
     * @return
     */
    public static String getLastDayOfWeek(long time) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(new Date(time));
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return getDateStr(c.getTime());
    }

    /**
     * 把时间戳格式化成特殊格式
     * 1分钟前，1小时前，2016-12-12
     *
     * @param time
     * @return
     */
    public static String formatTimestamp(String time) {
        // 评论创建时间
        long issueTime = Long.parseLong(time) * 1000;
        // 系统当前时间
        long systemTime = System.currentTimeMillis();
        //与现在的时间差值
        long differentials = systemTime - issueTime;
        final int M = 60 * 1000;// 分钟
        final int H = 60 * 60 * 1000;// 小时
        final int D = 24 * 60 * 60 * 1000;// 天
        if (differentials < M) {
            return "1分钟前";
        } else if (differentials < H) {
            int m = (int) (differentials / M);
            return m + "分钟前";
        } else if (differentials < D) {
            int h = (int) (differentials / H);
            return h + "小时前";
        } else if (differentials < 1.5 * D) {
            return "昨天";
        } else if (differentials < 2.5 * D) {
            return "前天";
        } else {
            return getDateStr(new Date(issueTime));
        }
    }


    /**
     * <p>获取当前系统时间</p>
     * 通过java.util.Date类获取
     *
     * @return 返回java.util.Date类型对象
     * @see #
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * <p>获取当前系统时间</p>
     * 通过java.util.Calendar类获取
     *
     * @return 返回java.util.Date类型对象
     * @see Calendar
     */
    public static Date getCalendarDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * <p>获取当前系统日期，返回字符串格式</p>
     * 格式：yyyy-MM-dd
     *
     * @return 返回日期字符串格式：yyyy-MM-dd
     * @see #getDateStr(Date)
     */
    public static String getDateStr() {
        return getDateStr(new Date());
    }

    /**
     * <p>获取格式化字符串日期，返回字符串格式</p>
     * 格式：yyyy-MM-dd
     *
     * @param date 指定日期对象
     * @return 返回日期字符串格式：yyyy-MM-dd
     */
    public static String getDateStr(Date date) {
        return yyyy_MM_dd.format(date);
    }

    /**
     * <p><b>[默认]</b> 获取当前系统日期时间，返回字符串格式</p>
     * 格式：yyyy-MM-dd HH:mm:ss<br>
     *
     * @return 返回字符串对象：yyyy-MM-dd HH:mm:ss
     * @see #getDateTimeStr(Date)
     */
    public static String getDateTime() {
        return yMd_Hms.format(new Date());
    }

    /**
     * <p>获取当前系统日期时间，返回字符串格式</p>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return 返回字符串对象：yyyy-MM-dd HH:mm:ss
     * @see #getDateTimeStr(Date)
     */
    public static String getDateTimeStr() {
        return getDateTimeStr(new Date());
    }

    /**
     * <p>获取格式化字符串日期时间，返回字符串格式</p>
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 指定日期对象
     * @return 返回日期时间字符串格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeStr(Date date) {
        return yMd_Hms.format(date);
    }

    public static String getDateStr2(Date date) {
        return yyyy_MM_dd.format(date);
    }


    public static String getDateFormatString(Date date) {
//        if (time == null || time.isEmpty()) {
//            return null;
//        }
//        long t = Long.valueOf(time) * 1000;
//        return yMd.format(new Date(Long.parseLong(time)*1000));
//        return "时间戳";
        return yMd.format(date);
    }

    /**
     * 格式化日期
     * 2017.07.05
     *
     * @param
     * @return
     */
    public static String getDateFormatString(String time) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        long date = Long.parseLong(time) * 1000;
        return yMd.format(new Date(date));
    }

    /**
     * 格式化日期
     * 2017-07-06 10:35 或者 07-06 10:35
     */
    public static String getDateFormatMMDDHHmm(String time) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        long date = Long.parseLong(time) * 1000;
        Date date1 = new Date(date);
        if (getCustomYear(date1) < getCurrentYear()) {
            return getDateTimeStr(date1).substring(0, 16);
        } else {
            return getDateTimeStr(date1).substring(5, 16);
        }
    }

    /**
     * 格式化日期
     * 2017-07-06 10:35
     */
    public static String getYYYYMMDDHHmm(String time) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        long date = Long.parseLong(time) * 1000;
        Date date1 = new Date(date);
        return getDateTimeStr(date1).substring(0, 16);
    }


    /**
     * <p>获取当前年</p>
     *
     * @return 返回int类型的整数
     */
    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * <p>获取当前月</p>
     *
     * @return 返回int类型的整数，一位或两位数，范围是：1-12
     */
    public static int getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * <p>获取当前日</p>
     *
     * @return 返回int类型的整数，一位或两位数，范围是：1-31
     */
    public static int getCurrentDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * <p>获得指定的年，int格式</p>
     *
     * @param date 指定日期对象
     * @return 返回int类型的整数
     */
    public static int getCustomYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * <p>获得指定的月，int格式</p>
     *
     * @param date 指定日期对象
     * @return 返回int类型的整数，一位或两位数，范围是：1-12
     */
    public static int getCustomMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * <p>获得指定的日，int格式</p>
     *
     * @param date 指定日期对象
     * @return 返回int类型的整数，一位或两位数，范围是：1-31
     */
    public static int getCustomDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * <p>获取当前系统时间的小时数</p>
     * 通过java.util.Calendar获取
     *
     * @return 返回小时数
     */
    public static int getCurrentHour() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * <p>获取当前系统时间的分钟数</p>
     * 通过java.util.Calendar获取
     *
     * @return 返回分钟数
     */
    public static int getCurrentMinute() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * <p>获取当前系统时间的秒数</p>
     * 通过java.util.Calendar获取
     *
     * @return 返回秒数
     */
    public static int getCurrentSecond() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.SECOND);
    }

    /**
     * <p>获得指定的小时(日中的)，int格式</p>
     * <br>
     *
     * @return
     */
    public static int getCustomHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * <p>获得指定的分钟，int格式</p>
     * <br>
     *
     * @return
     */
    public static int getCustomMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * <p>获得指定的秒，int格式</p>
     * <br>
     *
     * @return
     */
    public static int getCustomSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**********************我是华丽丽的分割线***************************************************/

    /**
     * <p>
     * 获取本月第一天日期（格式如YYYYMMDD）,如果当前日为当月1日,则返回上月第一日
     * </p>
     *
     * @return
     */
    public static String getMonthFirstDay() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = 0;
        if (day == 1)// 当月第一日
        {
            calendar.add(Calendar.MONTH, -1);
        }
        month = calendar.get(Calendar.MONTH);
        if (month < 10) {
            return "" + calendar.get(Calendar.YEAR) + "0" + (month + 1) + "01";
        } else {
            return "" + calendar.get(Calendar.YEAR) + month + "01";
        }
    }

    /**
     * <p>
     * 获取当前时间前几天或后几天的日期
     * </p>
     *
     * @return
     */
    public static Date getAddDays(int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * <p>
     * 获取某个月后的日期格式（yyyyMMdd）
     * </p>
     *
     * @return
     */
    public static String getAfterMonth(int monthNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, monthNum);
        return yyyyMMdd.format(calendar.getTime());
    }

    /**
     * <p>
     * 返回日期（格式yyyyMMdd）
     * </p>
     *
     * @param timeMillis
     * @return
     */
    public static String getFormatDate(long timeMillis) {
        return yMdHms.format(new Date(timeMillis));
    }

    /**
     * 获取当前系统时间距离传入时间的毫秒数
     *
     * @param strTime 格式[ DD:00:00]
     * @return
     * @throws ParseException
     */
    public static long getSleepTime(String strTime) throws ParseException {
        long p = 1;
        long l_date = System.currentTimeMillis();
        Date date_now = new Date(l_date);
        String strDate = yyyy_MM_dd.format(date_now) + strTime;
        if (date_now.before(yMd_Hms.parse(strDate)))
            p = (yMd_Hms.parse(strDate)).getTime() - l_date;
        else {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date_now);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date date = calendar.getTime();
            strDate = yyyy_MM_dd.format(date) + strTime;
            p = (yMd_Hms.parse(strDate)).getTime() - l_date;
        }
        return p;
    }

    public static String getPredate() {
        Date nowDate = new Date();
        String nowdates = yyyy_MM_dd.format(nowDate);
        String[] dates = nowdates.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]) - 1;
        if (day == 0) {
            switch (month - 1) {
                case 1:
                    day = 31;
                    break;
                case 3:
                    day = 31;
                    break;
                case 5:
                    day = 31;
                    break;
                case 7:
                    day = 31;
                    break;
                case 8:
                    day = 31;
                    break;
                case 10:
                    day = 31;
                    break;
                case 0:
                    month = 13;
                    year = year - 1;
                    day = 31;
                    break;
                case 4:
                    day = 30;
                    break;
                case 6:
                    day = 30;
                    break;
                case 9:
                    day = 30;
                    break;
                case 11:
                    day = 30;
                    break;
                case 2:
                    if (year % 4 == 0) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                    break;
                default:
                    break;
            }
            month = month - 1;
        }
        String predate = Integer.toString(year) + "-"
                + (month < 10 ? "0" + month : month) + "-"
                + (day < 10 ? "0" + day : day);
        return predate;
    }


    /**
     * add by gongtao
     * <p>
     * 将字符串类型的日期格式 转换为 符合要求的 Date类型的日期格式
     * </P>
     *
     * @param date
     * @param format
     * @return
     */
    public static Date getDate4StrDate(String date, String format) {
        if (date == null || "".equals(date.trim())) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            try {
                return df.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * add by gongtao 计算指定日期时间之间的时间差
     *
     * @param beginStr 开始日期字符串
     * @param endStr   结束日期字符串
     * @param f        时间差的形式0-秒,1-分种,2-小时,3--天 日期时间字符串格式:yyyyMMddHHmmss
     */
    public static int getInterval(String beginStr, String endStr, int f) {
        int hours = 0;
        try {
            Date beginDate = yMd_Hms.parse(beginStr);
            Date endDate = yMd_Hms.parse(endStr);
            long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减获取日期差X(单位:毫秒)
            /**
             * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
             * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
             * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
             * 天
             * */
            switch (f) {
                case 0: // second
                    return (int) (millisecond / 1000);
                case 1: // minute
                    return (int) (millisecond / (1000 * 60));
                case 2: // hour
                    return (int) (millisecond / (1000 * 60 * 60));
                case 3: // day
                    return (int) (millisecond / (1000 * 60 * 60 * 24));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hours;
    }

    /**
     * add by lipp
     * <p>
     * 获取起始日期前或后天数的日期
     * </P>
     *
     * @param starttime 起始日期 格式：yyyy-MM-dd
     * @param days
     * @return
     * @throws ParseException
     */
    public static Date getStartDateInterval(String starttime, int days) {
        // 格式化起始时间 yyyyMMdd
        Date startDate = null;
        try {
            startDate = yyyy_MM_dd.parse(starttime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar startTime = Calendar.getInstance();
        startTime.clear();
        startTime.setTime(startDate);

        startTime.add(Calendar.DAY_OF_YEAR, days);
        return startTime.getTime();
    }

    /**
     * add by lipp
     * <p>
     * 获取起始日期和结束日期之间的天数
     * </P>
     *
     * @param beginStr 起始日期
     * @param endStr   结束日期
     * @param format   根据 日期参数的格式，传对应的SimpleDateFormat格式
     * @return 天数
     */
    public static int getDaysInterval(String beginStr, String endStr,
                                      SimpleDateFormat format) {

        try {
            Date beginDate = format.parse(beginStr);
            Date endDate = format.parse(endStr);
            long millisecond = endDate.getTime() - beginDate.getTime();
            // 日期相减获取日期差X(单位:毫秒)
            return (int) (millisecond / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取本月第一天
     *
     * @return
     */
    public static Date getFristDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();

    }

    /**
     * 获取本月最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();

    }

    /**
     * 获得本周一的日期
     * @return
     * @throws ParseException
     */
    /*public static Date getMondayOFWeek() throws ParseException {
      int mondayPlus = getMondayPlus();
	  GregorianCalendar currentDate = new GregorianCalendar();
	  currentDate.add(Calendar.DATE, mondayPlus);
	  Date monday = currentDate.getTime();
	  String dateStr = getDateTime_I(monday);
	  StringBuffer sb = new StringBuffer(dateStr);
	  sb.append(" ").append("00:00:00");
	  return parseDateStrToDate(sb.toString());
	}*/

    /**
     * 获得本周星期日的日期
     * @return
     * @throws ParseException
     */
    /*public static Date getCurrentWeekday() throws ParseException {
      int mondayPlus = getMondayPlus();
	  GregorianCalendar currentDate = new GregorianCalendar();
	  currentDate.add(Calendar.DATE, mondayPlus + 6);
	  Date monday = currentDate.getTime();
	  String dateStr = getDateTime_I(monday);
	  StringBuffer sb = new StringBuffer(dateStr);
	  sb.append(" ").append("00:00:00");
	  return parseDateStrToDate(sb.toString());
	}*/

    /**
     * 字符串时间格式转换为 Date
     *
     * @param date 此格式 yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Date parseDateStrToDate(String date)
            throws ParseException {
        Date date_time = null;
        if (!TextUtils.isEmpty(date)) {
            date_time = yMd_Hms.parse(date);
        }
        return date_time;
    }

    /**
     * 字符串时间格式转换为 Date
     *
     * @param date 此格式 yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date parseDateFromStr(String date)
            throws ParseException {
        Date date_time = null;
        if (!TextUtils.isEmpty(date)) {
            date_time = yyyy_MM_dd.parse(date);
        }
        return date_time;
    }

    /**
     * 字符串时间格式转换为 Date
     *
     * @param date 此格式 yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date parseDateFromYYYY_MM_DD_HH_mmStr(String date)
            throws ParseException {
        Date date_time = null;
        if (!TextUtils.isEmpty(date)) {
            date_time = yMd_Hm.parse(date);
        }
        return date_time;
    }

    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 因为按中国礼拜一作为第一天所以这里减1
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 1) {
            return 0;
        } else if (dayOfWeek == 0) {
            return 1 - 7;
        } else {
            return 1 - dayOfWeek;
        }
    }

    public static String getYYYY_MM_DDString(String time) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        long t = Long.parseLong(time) * 1000;
        Date date = new Date(t);
        return yyyy_MM_dd.format(date);
    }

    /**
     * @param beginDate
     * @param endDate
     * @param f         时间差的形式0:秒,1:分种,2:小时,3:天
     * @return
     */
    public static int getDifferenceNum(Date beginDate, Date endDate, int f) {
        int result = 0;
        if (beginDate == null || endDate == null) {
            return 0;
        }
        try {
            // 日期相减获取日期差X(单位:毫秒)
            long millisecond = endDate.getTime() - beginDate.getTime();
            /**
             * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
             * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
             * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
             * 天
             * */
            switch (f) {
                case 0: // second
                    return (int) (millisecond / 1000);
                case 1: // minute
                    return (int) (millisecond / (1000 * 60));
                case 2: // hour
                    return (int) (millisecond / (1000 * 60 * 60));
                case 3: // day
                    return (int) (millisecond / (1000 * 60 * 60 * 24));
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * <p>比较两个日期的大小,精确到秒</p>
     *
     * @param d1
     * @param d2
     * @return 返回一个long类型的整数，若大于0表示第一个日期晚于第二个日期，小于0表示第一个日期早于第二个日期，否则相等
     * @author lipp@icloud-edu.com
     * @date 2014-06-03
     */
    public static long compareEachOther(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return -1;
        String d1Str = d1.getTime() + "";
        String d2Str = d2.getTime() + "";
        int l1 = d1Str.length();
        int l2 = d2Str.length();
        d1Str = d1Str.substring(0, l1 - 3) + "000";
        d2Str = d2Str.substring(0, l2 - 3) + "000";
        //System.out.println(d1Str + "   " + d2Str);
        long long1 = Long.parseLong(d1Str);
        long long2 = Long.parseLong(d2Str);
        return long1 - long2;
    }

    /**
     * 掉此方法输入所要转换的时间输入例如（"yyyy年MM月dd日-16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    public static String dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日 HH:mm",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }


    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time
     * @return
     */
    public static String getDateToString(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time
     * @return
     */
    public static String getDateTimeToString(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 获取当前时间
     *
     * @param
     * @return
     */
    public static String getCurrDateAndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static int daysBetween(Date startDate, Date endDate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        startDate = sdf.parse(sdf.format(startDate));
        endDate = sdf.parse(sdf.format(endDate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        long time1 = calendar.getTimeInMillis();


        calendar.setTime(endDate);
        long time2 = calendar.getTimeInMillis();


        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(betweenDays));
    }


    public static Date changeDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date d1 = null;
        try {
            d1 = sdf.parse(str);
            return d1;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String getMonthDayDate(String dateStr) {
        // 需要解析的日期字符串
//		String dateStr = "2016年12月14日 20:59";
        // 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，HH表示小时24小时制，小写的话是12小时制
        // mm，小写，表示分钟，ss表示秒
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        try {
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dateStr);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
            // 获取年
            int year = calendar.get(Calendar.YEAR);
            // 这里要注意，月份是从0开始。
            int month = calendar.get(Calendar.MONTH);
            // 获取天
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            return month + 1 + "-" + day;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static String getHourMinuteDate(String dateStr) {
        // 需要解析的日期字符串
//		String dateStr = "2016年12月14日 20:59";
        // 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，HH表示小时24小时制，小写的话是12小时制
        // mm，小写，表示分钟，ss表示秒
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        try {
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dateStr);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
            // 获取年
            int year = calendar.get(Calendar.YEAR);
            // 这里要注意，月份是从0开始。
            int month = calendar.get(Calendar.MONTH);
            // 获取天
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            if (hour < 10) {
                if (minute < 10) {
                    return "0" + hour + ":0" + minute;
                } else {
                    return "0" + hour + minute;
                }
            } else {
                if (minute < 10) {
                    return hour + "0" + minute;
                }
            }
            return hour + ":" + minute;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    public static String stampToDate(String s) {
        if ("0".equals(s) || TextUtils.isEmpty(s)) {
            return "";
        } else {
            String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new java.util.Date(Long.parseLong(s) * 1000));
            return date;
        }
    }


    public static String stampToDate3(String s) {
        if ("0".equals(s) || TextUtils.isEmpty(s)) {
            return "";
        } else {
            String date = new SimpleDateFormat("MM-dd HH:mm:ss").format(new java.util.Date(Long.parseLong(s) * 1000));
            return date;
        }
    }


    public static String stampToDateMM(String s) {
        if ("0".equals(s) || TextUtils.isEmpty(s)) {
            return "";
        } else {
            String date = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date(Long.parseLong(s) * 1000));
            return date;
        }
    }


    public static String dateToStamp(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime() / 1000;
        res = String.valueOf(ts);
        return res;
    }


    public static String dateToStamp2(String s) {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime()+"";
    }


    public static boolean time1totime2(String dt2time) {

        DateFormat df = new SimpleDateFormat("HH:mm");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd

        Date dt1 = null;//将字符串转换为date类型
        try {

            Date now = new Date();
            String value = df.format(now);
            dt1 = df.parse(value);
            dt2 = df.parse(dt2time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //比较时间大小,如果dt1大于dt2
        return dt1.getTime() > dt2.getTime();
    }

    public static boolean isCurrentDay(String value) {
        if (TextUtils.isEmpty(value)) {
            return true;
        }
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        //获取今天的日期
        String nowDay = sf.format(now);
        return value.equals(nowDay);


    }

    public static String getMM() {
        Calendar cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH) + 1;
        if (month < 10) {
            return "0" + month;
        } else {
            return month + "";
        }

    }

    public static String getYY() {
        Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR) ;
       return year+"";

    }

    public static String getDD() {
        Calendar cale = Calendar.getInstance();
        int date = cale.get(Calendar.DATE);
        return date+"";

    }

    public static String getWeek() {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];

    }


}



