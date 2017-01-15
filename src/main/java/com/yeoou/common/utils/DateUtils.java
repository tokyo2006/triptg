package com.yeoou.common.utils;

import java.text.SimpleDateFormat;
import com.yeoou.common.context.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yeoou.tour.model.*;

/**
 * <p>
 * 标题: 日期工具
 * </p>
 * <p>
 * 描述: 为日期转换提供一些封装
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2008-12-16
 */
public class DateUtils
{

    private static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 获取多少天以前的时期
     * 
     * @param days 距今天多少天
     * @return 以前的时间
     */
    public static Date previous(int days) {
        return new Date(System.currentTimeMillis() - days * 3600000L * 24L);
    }

    /**
     * 转化日期为字符串，格式为： "yyyy-MM-dd HH:mm".
     */
    public static String formatDateTime(Date d) {
        return new SimpleDateFormat(DATETIME_FORMAT).format(d);
    }
    public static String formatDateTime(String formart,Date d) {
        return new SimpleDateFormat(formart).format(d);
    }
    /**
     * 转换日期和时间为字符串，格式为： "yyyy-MM-dd HH:mm".
     */
    public static String formatDateTime(long d) {
        return new SimpleDateFormat(DATETIME_FORMAT).format(d);
    }

    /**
     * 转换日期格式为字符串 "yyyy-MM-dd".
     */
    public static String formatDate(Date d) {
        return new SimpleDateFormat(DATE_FORMAT).format(d);
    }

    /**
     * 转换字符串为时间，格式为 "yyyy-MM-dd".
     */
    public static Date parseDate(String d) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(d);
        }
        catch(Exception e) {}
        return null;
    }
    /**
     * 转换字符串为时间，格式为 format.
     */
    public static Date parseDate(String format,String d)
    {
    	try {
            return new SimpleDateFormat(format).parse(d);
        }
        catch(Exception e) {}
        return null;
    }
    /**
     * 转换字符串为日期格式为 "yyyy-MM-dd hh:mm".
     */
    public static Date parseDateTime(String dt) {
        try {
            return new SimpleDateFormat(DATETIME_FORMAT).parse(dt);
        }
        catch(Exception e) {}
        return null;
    }
	public static String formatCalendar(String pattern, Calendar cal) {
		java.util.Date date = cal.getTime();
		SimpleDateFormat format = (SimpleDateFormat)SimpleDateFormat.getDateInstance();
        format.applyPattern(pattern);
		return format.format(date);
	}
	/**
	 * 获取以某个日期为起点，几天后的日期
	 * @param date		起点日期
	 * @param days		天数
	 * @return			目标日期
	 */
	public static String getBackDay(Date date,int days)
	{
		Calendar cal=Calendar.getInstance();
		cal.clear();
		cal.setTime(date);
		cal.add(Calendar.DATE, days-1);
		date = cal.getTime();
		return DateUtils.formatDate(date);
	}
	/**
	 * 截止日期判定
	 * @param date		截止日期
	 * @param days		提前天数
	 * @return			是否截止
	 */
	public static boolean isPushDown(Date date,int days)
	{
		Date today = new Date();
		today = DateUtils.parseDate(DateUtils.formatDate(today));
		Calendar cal=Calendar.getInstance();
		cal.clear();
		cal.setTime(date);
		cal.add(Calendar.DATE, 0-days);
		date = DateUtils.parseDate(DateUtils.formatDate(cal.getTime()));
		String dateStr = DateUtils.formatDate(date);
		if(today.after(date)||today.compareTo(date)==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * @deprecated
	 * 日期转为星期
	 * @param dateStr		日期
	 * @return				星期
	 */
	public static String getWeekByDate(String dateStr)
	{
		int dayOfWeek = 0;
		Date date = DateUtils.parseDate(dateStr);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
		return Global.otherDayOfWeeks[dayOfWeek];
	}
	/**
	 * 日期转为星期
	 * @param dateStr	日期
	 * @param weeks     星期模板
	 * @return			星期
	 */
	public static String getWeekByDate(String dateStr,String[] weeks)
	{
		int dayOfWeek = 0;
		Date date = DateUtils.parseDate(dateStr);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
		return weeks[dayOfWeek];
	}
	/**
	 * 日期格式为YYYY-MM-dd，过去日期列表
	 * @param startDate		起始日期
	 * @param endDate		截止日期
	 * @param weeks			选择星期
	 * @return				返回日期列表
	 */
	public static List<String> getDateStringByWeeks(String startDate,String endDate,String weeks)
	{
		String[] week = weeks.split(",");
		int dayOfWeek = 0;
		List<String> tempDate = new ArrayList<String>();
		List<String> weekList = new ArrayList<String>();
		if(week[0].equals("1"))
		{
			for(int i=0;i<7;i++)
			{
				weekList.add(String.valueOf(i));
			}
		}
		else
		{
			for(int i=1;i<week.length;i++)
			{
				if(week[i].equals("1"))
				{
					int t = i-1;
					weekList.add(String.valueOf(t));
				}
			}
		}
		Date stDate = DateUtils.parseDate(startDate);
		Date edDate = DateUtils.parseDate(endDate);
		Calendar cal=Calendar.getInstance();
		if(startDate.equals(endDate))
		{
			tempDate.add(startDate);
			return tempDate;
		}
		while(true)
		{
			
			cal.clear();
			cal.setTime(stDate);
			dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
			for(int i=0;i<weekList.size();i++)
			{
				if(weekList.get(i).trim().equals(String.valueOf(dayOfWeek)))
				{
					tempDate.add(DateUtils.formatDate(stDate));
				}
			}
			cal.add(Calendar.DATE, 1);
			stDate = cal.getTime();
			String deb = DateUtils.formatDateTime(stDate);
			stDate = DateUtils.parseDate(deb);
			if(edDate.compareTo(stDate)==0)
			{
				cal.clear();
				cal.setTime(stDate);
				dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
				for(int i=0;i<weekList.size();i++)
				{
					if(weekList.get(i).trim().equals(String.valueOf(dayOfWeek)))
					{
						tempDate.add(DateUtils.formatDate(stDate));
					}
				}
				break;
			}
		}
		return tempDate;
	}
}
