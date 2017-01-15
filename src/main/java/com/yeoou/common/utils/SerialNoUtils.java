package com.yeoou.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * <p>
 * 标题: 序列号生成工具
 * </p>
 * <p>
 * 描述: 生成随机序列号
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2008-12-16
 */
public class SerialNoUtils
{
	/**
	 * 获取随机序列号
	 * @return
	 */
	public static String getSerialNo()
	{
		StringBuilder serialNo = new StringBuilder();
		Random rd = new Random();
		int ad = rd.nextInt(10000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
		String bd =  sdf.format(new Date()); 
		serialNo.append(bd);
		serialNo.append(ad);
		return serialNo.toString();
	}
	/**
	 * 生成日期的序列号
	 * @param date	日期
	 * @return
	 */
	public static String getSerialNo(String date)
	{
		StringBuilder serialNo = new StringBuilder();
		Random rd = new Random();
		int ad = rd.nextInt(10000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
		String bd =  sdf.format(DateUtils.parseDate(date)); 
		serialNo.append(bd);
		serialNo.append(ad);
		return serialNo.toString();
	}
	/**
	 * 获取日期的字符串格式为：yyyyMMdd 例如：20080910
	 * @return
	 */
	public static String getDateString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		String bd =  sdf.format(new Date()); 
		return bd;
	}
}
