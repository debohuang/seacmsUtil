package com.gb.common.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @ClassName: DateUtil
 * @Description: 日期格式处理工具类
 * @author 周良申
 * @date 2016年10月28日 上午11:11:41
 *
 */
public class DateUtil {
	
	public static String FORMAT_STYLE_1 = "yyyy-MM-dd HH:mm:ss";
	
	public static String FORMAT_STYLE_2 = "yyyy-MM-dd";

	public static String FORMAT_STYLE_3 = "yyyyMMddHHmmss";

	public static String FORMAT_STYLE_4 = "yyyyMMdd";
	
	public static String FORMAT_STYLE_5 = "yyyy";
	
	public static String FORMAT_STYLE_6 = "MM";
	
	public static String FORMAT_STYLE_7 = "dd";

	
	
	/**
	 * 
	 * @Title: getCurrentTimeStamp
	 * @Description: 获取当前的时间戳
	 * @return
	 */
	public static long getCurrentTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}
	
	/**
	 * 
	 * @Title: getFormatStyle
	 * @Description: 自定义选择时间格式
	 * @param style
	 * @return
	 */
	public static String getFormatStyle(Integer style) {
		String format = null;
		switch (style) {
		case 1:
			format = FORMAT_STYLE_1;
			break;
		case 2:
			format = FORMAT_STYLE_2;
			break;
		case 3:
			format = FORMAT_STYLE_3;
			break;
		case 4:
			format = FORMAT_STYLE_4;
			break;
		case 5:
			format = FORMAT_STYLE_5;
			break;
		case 6:
			format = FORMAT_STYLE_6;
			break;
		case 7:
			format = FORMAT_STYLE_7;
			break;
		default:
			format = FORMAT_STYLE_1;
			break;
		}
		return format;
	}
	
	/**
	 * 
	 * @Title: getFormatStyleFromString
	 * @Description: 根据转换的日起字符串获取转换的时间格式
	 * @param date
	 * @return
	 */
	public static String getFormatStyleFromString(String date) {
		if (date.contains("-")) {
			if (date.contains(" ")) {
				return getFormatStyle(1);
			} else {
				return getFormatStyle(2);
			}
		} else {
			if (date.length() > 8) {
				return getFormatStyle(3);
			} else {
				return getFormatStyle(4);
			}
		}
	}
	
	/**
	 * 
	 * @Title: getNDate
	 * @Description: 获取N天后的date
	 * @param dateStr
	 * @param n
	 * @return
	 */
	public static Date getNDate(Date dateIN, int n) {
		Calendar cal = Calendar.getInstance();   
		cal.setTime(dateIN);   
		cal.add(Calendar.DATE, n);   
		Date date = cal.getTime();   
		return date;
	}
	
	public static String getYesterday(int style) {
		return date2String(getNDate(new Date(), -1), style);
	}
	
	/**
	 * 
	 * @Title: getFormatDate
	 * @Description: 获取格式化了的日期
	 * @param date
	 * @return
	 */
	public static String date2String(Date date, int style){
	    DateFormat format = new SimpleDateFormat(getFormatStyle(style));
	    String df = format.format(date);
	    return df;
	}
	
	/**
	 * 
	 * @Title: stringToDate
	 * @Description: 字符串转时间对象
	 * @param dateStr
	 * @return
	 */
	public static Date string2Date(String dateStr) {
		DateFormat format = new SimpleDateFormat(getFormatStyleFromString(dateStr));
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 
	 * @Title: getDatePoor
	 * @Description: 两个日期相差多少天小时分钟
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static String getDateDiff(Date endDate, Date nowDate) {
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    if (day > 0) {
	    	return day + "天" + hour + "小时" + min + "分钟";
	    } else {
	    	if (hour > 0) {
	    		return hour + "小时" + min + "分钟";
	    	} else {
	    		return min + "分钟";
	    	}
	    }
	    
	}
	
	public static String startTimeOfDay(String date) {
		return date + " 00:00:00";
	}
	
	public static String endTimeOfDay(String date) {
		return date + " 23:59:59";
	} 
	
	public static String getCurrentDay(int style) {
		return date2String(new Date(), style);
	}
	
	public static void main(String[] args) {
//		System.out.println(getDateDiff(DateUtil.string2Date("2016-12-26 12:00:00"), new Date()));
//		System.out.println(System.currentTimeMillis());
//		System.out.println(getCurrentTimeStamp());
		System.out.println(date2String(new Date(),5));
		System.out.println(date2String(new Date(),6));
		System.out.println(date2String(new Date(),7));
	}
}
