package com.gb.common.util;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DateUtils {

	/**
	 * 自定义订单类 工具类，可以获取系统日期，订单编号等
	 */

	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtShort);
		return df.format(date);
	}

	/**
	 * 产生随机的五位数
	 * 
	 * @return
	 */
	public static String getFive() {
		Random rad = new Random();
		return rad.nextInt(100000) + "";
	}

	/**
	 * 返回当月天数
	 * 
	 * @param month
	 *            月份
	 * @return
	 */
	public static int getDay(int year, int month) {
		int day = 0;
		switch (month) {
		case 1:
			day = 31;
			break;
		case 2:
			day = ((year / 4 == 0 && year / 100 != 0) || year / 400 == 0) == true ? 28 : 29;
			break;
		case 3:
			day = 31;
			break;
		case 4:
			day = 30;
			break;
		case 5:
			day = 31;
			break;
		case 6:
			day = 30;
			break;
		case 7:
			day = 31;
			break;
		case 8:
			day = 31;
			break;
		case 9:
			day = 30;
			break;
		case 10:
			day = 31;
			break;
		case 11:
			day = 30;
			break;
		case 12:
			day = 31;
			break;
		}
		return day;
	}

	/**
	 * 将周期阿拉伯数字转换成中文数字
	 * 
	 * @param week
	 * @return
	 */
	public static String getWeek(int week) {
		String strWeek = "";
		switch (week) {
		case 1:
			strWeek = "一";
			break;
		case 2:
			strWeek = "二";
			break;
		case 3:
			strWeek = "三";
			break;
		case 4:
			strWeek = "四";
			break;
		case 5:
			strWeek = "五";
			break;
		case 6:
			strWeek = "六";
			break;
		case 7:
			strWeek = "日";
			break;
		}
		return strWeek;
	}

	/**
	 * 讲string类型时间转换成date类型
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date changeStringToDate(String time) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		Timestamp cfStartTime = new Timestamp(date.getTime());
		return cfStartTime;
	}

	/**
	 * 获得一天所有小时
	 * 
	 * @return
	 */
	public static List<Integer> getAllHours() {
		List<Integer> hours = new ArrayList<Integer>(24);
		for (int i = 0; i < 24; i++) {
			hours.add(i);
		}
		return hours;
	}

	/**
	 * 获得一个小时所有分钟
	 * 
	 * @return
	 */
	public static List<Integer> getAllMinutes() {
		List<Integer> minutes = new ArrayList<Integer>(60);
		for (int i = 0; i < 60; i++) {
			minutes.add(i);
		}
		return minutes;
	}
}
