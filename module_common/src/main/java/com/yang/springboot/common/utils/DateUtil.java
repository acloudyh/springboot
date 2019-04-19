package com.yang.springboot.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期转换工具类
 * 使用joda-time 避免使用jdk自带的SimpleDateFormat，该类存在并发问题
 *
 * @author yanghao
 * @date 2019-04-19 16:32
 */
public class DateUtil {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    public static final String DIR_PATTERN = "yyyy/MM/dd";

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String TIMESTAMP_PATTERN_MINUTE = "yyyy-MM-dd HH:mm";

    public static final String TIMES_PATTERN = "HH:mm:ss";

    public static final String NOCHAR_PATTERN = "yyyyMMddHHmmss";

    public static final String GMT8_PATTERN = "yyMMddHHmmss";

    public static final String TIMES_NOCHAR_PATTERN = "HHmmss";

    public static final String DEFAULT_NOCHAR_PATTERN = "yyyyMMdd";

    public static final String MUTI_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String CST_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";


    /**
     * 东八区为例
     */
    public static DateTimeZone zone = DateTimeZone.forOffsetHours(8);


    /**
     * 转换为日期
     * @param date
     * @param datePattern
     * @return
     */
    public static Date parseDate(String date, String datePattern) {
        if(StringUtils.isBlank(date) || StringUtils.isBlank(datePattern)){
            return null;
        }
        return DateTime.parse(date, DateTimeFormat.forPattern(datePattern)).toDate();
    }


    /**
     * 转换为日期(东8区)
     * @param date
     * @param datePattern
     * @return
     * @throws ParseException
     */
    public static Date parseDateWith8Zone(String date, String datePattern) throws ParseException {
        if(StringUtils.isBlank(date) || StringUtils.isBlank(datePattern)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        // 设置北京时区
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return sdf.parse(date);
    }

    /**
     * 格式化日期
     * @param date
     * @param datePattern
     * @return
     */
    public static String formatDate(Date date, String datePattern){
        if(date == null || StringUtils.isBlank(datePattern)) {
            return "";
        }
        return new DateTime(date,zone).toString(datePattern);
    }

    /**
     * 格式化日期
     * @param date
     * @param datePattern
     * @param dateTimeZone
     * @return
     */
    public static String formatDate(Date date, String datePattern,DateTimeZone dateTimeZone){
        if(date == null || StringUtils.isBlank(datePattern)) {
            return "";
        }
        return new DateTime(date,dateTimeZone==null?zone:dateTimeZone).toString(datePattern);
    }


    /**
     * yyyy-MM-dd
     * @param date
     * @return
     */
    public static Date parseDefaultDate(String date){
        return parseDate(date, DEFAULT_PATTERN);
    }

    /**
     * yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatDefaultDate(Date date){
        return formatDate(date, DEFAULT_PATTERN);
    }

    /**
     * yyyy/MM/dd
     * @param date
     * @return
     */
    public static Date parseDirDate(String date){
        return parseDate(date, DIR_PATTERN);
    }

    /**
     * yyyy/MM/dd
     * @param date
     * @return
     */
    public static String formatDirDate(Date date){
        return formatDate(date, DIR_PATTERN);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static Date parseTimestampDate(String date){
        return parseDate(date, TIMESTAMP_PATTERN);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatTimestampDate(Date date){
        return formatDate(date, TIMESTAMP_PATTERN);
    }


    /**
     * yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatTimestampDate(Date date,DateTimeZone zone){
        return formatDate(date, TIMESTAMP_PATTERN,zone);
    }

    /**
     * HH:mm:ss
     * @param date
     * @return
     */
    public static Date parseTimesDate(String date){
        return parseDate(date, TIMES_PATTERN);
    }

    /**
     * HH:mm:ss
     * @param date
     * @return
     */
    public static String formatTimesDate(Date date){
        return formatDate(date, TIMES_PATTERN);
    }

    /**
     * yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static Date parseNoCharDate(String date){
        return parseDate(date, NOCHAR_PATTERN);
    }

    /**
     * yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String formatNoCharDate(Date date){
        return formatDate(date, NOCHAR_PATTERN);
    }

    /**
     * HHmmss
     * @param date
     * @return
     */
    public static Date parseTimesNoCharDate(String date){
        return parseDate(date, TIMES_NOCHAR_PATTERN);
    }

    /**
     * HHmmss
     * @param date
     * @return
     */
    public static String formatTimesNoCharDate(Date date){
        return formatDate(date, TIMES_NOCHAR_PATTERN);
    }

    /**
     * yyyyMMdd
     * @param date
     * @return
     */
    public static Date parseDefaultNoCharDate(String date){
        return parseDate(date, DEFAULT_NOCHAR_PATTERN);
    }

    /**
     * yyyyMMdd
     * @param date
     * @return
     */
    public static String formatDefaultNoCharDate(Date date){
        return formatDate(date, DEFAULT_NOCHAR_PATTERN);
    }

    /**
     * 2016-08-15T16:00:00.000Z
     * @param date
     * @return
     */
    public static Date mutiDate (String date) {
        return parseDate(date, MUTI_PATTERN);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurrentTime(){
        return new DateTime(zone).toDate();
    }

    /**
     * 获取当前时间
     * @return
     */
    public static DateTime getCurrentDateTime(){
        return new DateTime(zone);
    }

    /**
     * 获取当前月最后一天
     * @return
     */
    public static DateTime getCurrentMonthLastDate() {
        return DateTime.now().dayOfMonth().withMaximumValue()
                .withHourOfDay(23).withMinuteOfHour(59)
                .withSecondOfMinute(59);
    }

    /**
     * 获取当前月第一天
     * @return
     */
    public static DateTime getCurrentMonthFirstDate(){
        return DateTime.now().dayOfMonth().withMinimumValue()
                .withTimeAtStartOfDay();
    }


    /**
     * 获取随后的多少天
     * @param interval
     * @return
     */
    public static List<String> getNextSomeDate(int interval){
        List<String> list = new ArrayList<>();
        for(int i=interval-1;i>=0;i--){
            list.add(formatDefaultDate(DateTime.now().minusDays(i).toDate()));
        }

        return list;
    }


    /**
     * 获取随后的多少天
     * @param interval
     * @return
     */
    public static String getIntervalDate(int interval){
        return formatDefaultDate(DateTime.now().minusDays(interval).toDate());
    }


    /**
     *
     * UTC格式转换
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String parseUTCDate(Date date) throws ParseException {
        SimpleDateFormat utcFormater = new SimpleDateFormat(CST_PATTERN, Locale.US);
        Date gpsUTCDate = utcFormater.parse(String.valueOf(date));
        return formatTimestampDate(gpsUTCDate,zone);
    }


}
