package com.navinfo.wecloud.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <p>Title      : TODO[应用名]_[基础]</p>
 * <p>Description: TODO[时间工具管理类]</p>
 * <p>Copyright  : Copyright (c) 2016</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : wangyam
 * @version : 1.0
 */
public class TimeUtils {

    public static final String COMMON_GMT_DATE = "yyyyMMddHHmmss";// 精确到秒
    public static final String COMMON_GMT_DATE_MS = "yyyyMMddHHmmssSSS";// 精确到毫秒
    public static final String COMMON_DATE = "yyyy-MM-dd HH:mm:ss";// 精确到秒
    public static final String COMMON_DATE_MS = "yyyy-MM-dd HH:mm:ss SSS";// 精确到秒
    public static final String MESSAGE_DATE = "MM月dd日 HH:mm";// 精确到时分
    public static final String MESSAGE_DATE_HM = "yyyy-MM-dd HH:mm";// 精确到时分
    public static final String MESSAGE_DATE_Day = "yyyy-MM-dd";// 精确到时分

    /**
     * <p>Discription: TODO[取得据当前时间的之前多久]</p>
     *
     * @param inTime
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String getRemainTimeString(Date inTime) {
        String ret = "---";
        if (inTime == null) {
            return ret;
        }
        SimpleDateFormat dff = new SimpleDateFormat(COMMON_DATE);
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String nowDate = dff.format(new Date());
        Date now = stringToDate(nowDate, COMMON_DATE);
        int i = now.compareTo(inTime);
        if (i <= 0) {
            ret = "刚刚";
            return ret;
        }
        long l = now.getTime() - inTime.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        // long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String dayString = "天之前";
        String hourString = "小时之前";
        String minStringString = "分钟之前";
        if (day >= 1) {
            ret = day + dayString;
        } else if ((day < 1) && (hour >= 1)) {
            ret = hour + hourString;
        } else if ((hour < 1) && (min >= 1)) {
            ret = min + minStringString;
        } else if (min < 1) {
            ret = "刚刚";
        }
        return ret;
    }

    /**
     * <p>Discription: TODO[获取系统当前时间字符串，格式为：type]</p>
     *
     * @param type
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String getCurrentTime(String type) {
        return format(Calendar.getInstance().getTime(), type);
    }

    /**
     * <p>Discription: TODO[utc时间转化]</p>
     *
     * @param mDate
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String getLocalUtcTime(Date mDate, String type) {
        SimpleDateFormat utcFormater = new SimpleDateFormat(type);
        utcFormater.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String localtime = utcFormater.format(mDate);
        return localtime;
    }

    /**
     * <p>Discription: TODO[ Date转为 type格式的str]</p>
     *
     * @param date
     * @param type
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String dateToString(Date date, String type) {
        String ret = "";
        if (date != null) {
            ret = format(date, type);
        }
        return ret;
    }

    /**
     * <p>Discription: TODO[将日期按指定转换字符串]</p>
     *
     * @param date
     * @param type
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String format(Date date, String type) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(date);
    }

    /**
     * <p>Discription: TODO[将字符串格式化日期]</p>
     *
     * @param time
     * @param type
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String format(String time, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return null;
        }

        return sdf.format(date);
    }

    /**
     * <p>Discription:[long转为String type格式]</p>
     *
     * @param s
     * @param type
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String longToString(long s, String type) {
        String ret = "";
        String strTime = String.valueOf(s);
        if (strTime != null) {
            if (strTime.length() == 10) {
                strTime = String.valueOf(Integer.valueOf(strTime) * 1000l);
            }
            Date date = new Date(Long.valueOf(strTime));
            ret = format(date, type);
        }
        return ret;
    }

    /**
     * <p>Discription: [时间戳string 转为String  type格式]</p>
     *
     * @param strTime
     * @param type
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String longToString(String strTime, String type) {
        String ret = "";
        Date date;
        if (strTime != null) {
            if (strTime.length() == 13) {
                date = new Date(Long.valueOf(strTime.substring(0, 9)));
            } else {
                date = new Date(Long.valueOf(strTime));
            }
            ret = format(date, type);
        }
        return ret;
    }

    /**
     * <p>Discription: TODO[ms String转为  type格式]</p>
     *
     * @param s
     * @param type
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String msStringToString(String s, String type) {
        String ret = "";
        if (s != null) {
            long slong = 0;
            try {
                slong = Long.parseLong(s);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Date date = new Date(slong);
            ret = format(date, type);
        }
        return ret;
    }

    /**
     * <p>Discription: TODO[ms String转为 Date]</p>
     *
     * @param s
     * @return Date
     * @author : wangyam
     * @update :2016/4/25
     */
    public static Date msStringToDate(String s) {
        if (s != null) {
            long slong = 0;
            try {
                slong = Long.parseLong(s);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Date date = new Date(slong);
            return date;
        }
        return null;
    }

    /**
     * <p>Discription: TODO[yyyy-MM-dd HH:mm:ss 转为Date]</p>
     *
     * @param string
     * @return Date
     * @author : wangyam
     * @update :2016/4/25
     */
    public static Date stringToDate(String string) {
        Date ret = null;
        if (string != null) {
            SimpleDateFormat sdfDateFormat = new SimpleDateFormat(COMMON_DATE);
            try {
                ret = sdfDateFormat.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * <p>Discription: TODO[将给定字符按照给定模式转换为日期类型]</p>
     *
     * @param date
     * @param pattern
     * @return Date
     * @author : wangyam
     * @update :2016/4/25
     */
    public static Date stringToDate(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * <p>Discription: TODO[将字符串 yyyy-MM-dd HH:mm:ss转为时间戳]</p>
     *
     * @param time
     * @return long
     * @author : wangyam
     * @update :2016/4/25
     */
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(COMMON_DATE);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * <p>Discription: TODO[将字符串 yyyy-MM-dd HH:mm:ss转为时间戳]</p>
     *
     * @param time
     * @return long
     * @author : wangyam
     * @update :2016/4/25
     */
    public static long getStringToDate(String time, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * <p>Discription: TODO[GMT 时间转换]</p>
     *
     * @param gmt
     * @return String
     * @author : wangyam
     * @update :2016/4/25
     */
    public static String convertGMTToLoacale(String gmt) {
        try {
            if (gmt == null) {
                return null;
            }
            String cc = gmt.substring(0, 19) + gmt.substring(33, 38);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);
            Date date = sdf.parse(cc);
            String result = format(date, COMMON_DATE);
            return result;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * <p>Discription: TODO[GMT 时间转换]</p>
     *
     * @param gmt
     * @return long
     * @author : wangyam
     * @update :2016/4/25
     */
    public static long convertGMTtoLong(String gmt) {
        try {
            String cc = gmt.substring(0, 19) + gmt.substring(33, 38);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);
            Date date = sdf.parse(cc);
            String result = format(date, COMMON_GMT_DATE);
            return Long.parseLong(result);
        } catch (Exception e) {
        }
        return 0l;
    }

    /**
     * <p>Discription: TODO[判断str是否为时间格式]</p>
     *
     * @param str
     * @return boolean true为时间格式
     * @author : wangyam
     * @update :2016/4/25
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(COMMON_DATE);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * <p>Discription: TODO[获取当前时间戳]</p>
     *
     * @return long 13位时间戳
     * @author : wangyam
     * @update :2016/4/25
     */
    public static long getCurrentTimestamp() {

        long time = System.currentTimeMillis() / 1000;
        String str = String.valueOf(time);
        str = str + "000";
        return Long.parseLong(str);
    }

    public static String getTimeFullFormat(long time) {
        String ret = "---";
        //处理时间
        String inTime = TimeUtils.longToString(time, COMMON_DATE);
        if (inTime.isEmpty()) {
            return ret;
        }
        ret = inTime;
        //当前时间
        ret = ret.substring(0, 16);// add by charlie  不显示秒
    //    ret = ret.replace("-", "/");
        return ret;
    }

    public static String getTimeFormat(long time) {

        String ret = "---";
        //处理时间
        String inTime = TimeUtils.longToString(time, COMMON_DATE);
        if (inTime.isEmpty()) {
            return ret;
        }
        ret = inTime;
        //当前时间
        String curTime = getCurrentTime(COMMON_DATE);

        //若当前时间与处理时间的年数不同，；若年数相同，判断时间日期，当前日期只显示时分秒，昨天时间显示昨天加时分秒，其他，显示整个时间。
        if (curTime.substring(0, 4).equals(inTime.substring(0, 4))) {//年限相同
            int curDay = Integer.valueOf(curTime.substring(8, 10));
            int inDay = Integer.valueOf(inTime.substring(8, 10));

            int curMon = Integer.valueOf(curTime.substring(5, 7));
            int inMon = Integer.valueOf(inTime.substring(5, 7));
            if (curMon - inMon == 0) {//日期相同
                if (curDay == inDay){
                    ret = inTime.substring(11, 16);
                    return ret;
                }
                else if (curDay - inDay == 1){
                    ret = "昨天 " + inTime.substring(11, 16);
                    return ret;
                }
            } else if (curMon - inMon == 1) {
                int day = curDay - inDay;
                if (day== -30 || day == -29 || day == -28|| day == -27){
                    ret = "昨天 " + inTime.substring(11, 16);
                    return ret;
                }
            }
        }
        ret = ret.substring(0, 16);// add by charlie  不显示秒
        ret = ret.replace("-", "/");
        return ret;
    }

    public static String timeDate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long l = Long.valueOf(time);
        String times = sdr.format(new Date(l));
        return times;
    }
}
