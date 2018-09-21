package com.gb.common.util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ObjectConverter {

	public ObjectConverter() {
	}

	public static String getSnsTime(Timestamp d) {
		String s = "";
		long l = System.currentTimeMillis() - d.getTime();
		long linshi = l / 1000L;
		if (linshi < 60L)
			s = (new StringBuilder(String.valueOf(linshi))).append("\u79D2\u524D").toString();
		else if (linshi < 3600L)
			s = (new StringBuilder(String.valueOf(linshi / 60L))).append("\u5206\u949F\u524D").toString();
		else if (linshi < 86400L)
			s = (new StringBuilder(String.valueOf(linshi / 3600L))).append("\u5C0F\u65F6\u524D").toString();
		else if (linshi < 2592000L)
			s = (new StringBuilder(String.valueOf(linshi / 86400L))).append("\u5929\u524D").toString();
		else if (linshi < 31104000L)
			s = (new StringBuilder(String.valueOf(linshi / 2592000L))).append("\u4E2A\u6708\u524D").toString();
		else
			s = "1\u5E74\u524D";
		return s;
	}

	public static String getSnsTime(long l) {
		String s = "";
		long linshi = l;
		if (linshi < 60L)
			s = (new StringBuilder(String.valueOf(linshi))).append("\u79D2\u524D").toString();
		else if (linshi < 3600L)
			s = (new StringBuilder(String.valueOf(linshi / 60L))).append("\u5206\u949F\u524D").toString();
		else if (linshi < 86400L)
			s = (new StringBuilder(String.valueOf(linshi / 3600L))).append("\u5C0F\u65F6\u524D").toString();
		else if (linshi < 2592000L)
			s = (new StringBuilder(String.valueOf(linshi / 86400L))).append("\u5929\u524D").toString();
		else if (linshi < 31104000L)
			s = (new StringBuilder(String.valueOf(linshi / 2592000L))).append("\u4E2A\u6708\u524D").toString();
		else
			s = "1\u5E74\u524D";
		return s;
	}

	public static String doubleToString(double d, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(d);
	}

	public static String longToString(long l, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(l);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime());
	}

	public static String longToString2(long l, String format) {
		Date date = new Date();
		date.setTime(l);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String longToString3(long l, String format) {
		Timestamp timestamp = new Timestamp(l);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(timestamp);
	}

	public static String longToStartQuarter(long l, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(l);
		int currentMonth = calendar.get(2) + 1;
		if (currentMonth >= 1 && currentMonth <= 3)
			calendar.set(2, 0);
		else if (currentMonth >= 4 && currentMonth <= 6)
			calendar.set(2, 3);
		else if (currentMonth >= 7 && currentMonth <= 9)
			calendar.set(2, 6);
		else if (currentMonth >= 10 && currentMonth <= 12)
			calendar.set(2, 9);
		calendar.set(5, 1);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime());
	}

	public static String longToEndQuarter(long l, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(l);
		int currentMonth = calendar.get(2) + 1;
		if (currentMonth >= 1 && currentMonth <= 3) {
			calendar.set(2, 2);
			calendar.set(5, 31);
		} else if (currentMonth >= 4 && currentMonth <= 6) {
			calendar.set(2, 5);
			calendar.set(5, 30);
		} else if (currentMonth >= 7 && currentMonth <= 9) {
			calendar.set(2, 8);
			calendar.set(5, 30);
		} else if (currentMonth >= 10 && currentMonth <= 12) {
			calendar.set(2, 11);
			calendar.set(5, 31);
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime());
	}

	public static String numToStartQuarter(int i, String format) {
		Calendar calendar = Calendar.getInstance();
		if (i == 1)
			calendar.set(2, 0);
		else if (i == 2)
			calendar.set(2, 3);
		else if (i == 3)
			calendar.set(2, 6);
		else if (i == 4)
			calendar.set(2, 9);
		calendar.set(5, 1);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime());
	}

	public static String numToEndQuarter(int i, String format) {
		Calendar calendar = Calendar.getInstance();
		if (i == 1) {
			calendar.set(2, 2);
			calendar.set(5, 31);
		} else if (i == 2) {
			calendar.set(2, 5);
			calendar.set(5, 30);
		} else if (i == 3) {
			calendar.set(2, 8);
			calendar.set(5, 30);
		} else if (i == 4) {
			calendar.set(2, 11);
			calendar.set(5, 31);
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime());
	}

	public static String timestampToString(Timestamp timestamp, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(timestamp);
	}

	public static Short[] stringsToShorts(String s[]) {
		Short l[] = new Short[s.length];
		for (int i = 0; i < s.length; i++)
			l[i] = Short.valueOf(Short.parseShort(s[i]));

		return l;
	}

	public static Integer[] stringsToIntegers(String s[]) {
		Integer l[] = new Integer[s.length];
		for (int i = 0; i < s.length; i++)
			l[i] = Integer.valueOf(Integer.parseInt(s[i]));

		return l;
	}

	public static Long[] stringsToLongs(String s[]) {
		Long l[] = new Long[s.length];
		for (int i = 0; i < s.length; i++)
			l[i] = Long.valueOf(Long.parseLong(s[i]));

		return l;
	}

	public static List stringsToLongArray(String s[]) {
		List l = new ArrayList();
		for (int i = 0; i < s.length; i++)
			l.add(Long.valueOf(Long.parseLong(s[i])));

		return l;
	}

	public static Double[] stringsToDoubles(String s[]) {
		Double l[] = new Double[s.length];
		for (int i = 0; i < s.length; i++)
			l[i] = Double.valueOf(Double.parseDouble(s[i]));

		return l;
	}

	public static byte[] hexStringToByte(String hex) {
		int len = hex.length() / 2;
		byte result[] = new byte[len];
		char achar[] = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}

		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	public static String bytesToHexString(byte bArray[]) {
		StringBuffer sb = new StringBuffer(bArray.length);
		for (int i = 0; i < bArray.length; i++) {
			String sTemp = Integer.toHexString(255 & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}

		return sb.toString();
	}

	public static Object bytesToObject(byte bytes[]) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream oi = new ObjectInputStream(in);
		Object o = oi.readObject();
		oi.close();
		return o;
	}

	public static byte[] objectToBytes(Serializable s) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream ot = new ObjectOutputStream(out);
		ot.writeObject(s);
		ot.flush();
		ot.close();
		return out.toByteArray();
	}

	public static String objectToHexString(Serializable s) throws IOException {
		return bytesToHexString(objectToBytes(s));
	}

	public static Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
		return bytesToObject(hexStringToByte(hex));
	}

	public static String bcdToStr(byte bytes[]) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 240) >>> 4));
			temp.append((byte) (bytes[i] & 15));
		}

		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
	}

	public static byte[] strToBcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = (new StringBuilder("0")).append(asc).toString();
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2)
			len /= 2;
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		for (int p = 0; p < asc.length() / 2; p++) {
			int j;
			if (abt[2 * p] >= 48 && abt[2 * p] <= 57)
				j = abt[2 * p] - 48;
			else if (abt[2 * p] >= 97 && abt[2 * p] <= 122)
				j = (abt[2 * p] - 97) + 10;
			else
				j = (abt[2 * p] - 65) + 10;
			int k;
			if (abt[2 * p + 1] >= 48 && abt[2 * p + 1] <= 57)
				k = abt[2 * p + 1] - 48;
			else if (abt[2 * p + 1] >= 97 && abt[2 * p + 1] <= 122)
				k = (abt[2 * p + 1] - 97) + 10;
			else
				k = (abt[2 * p + 1] - 65) + 10;
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}

		return bbt;
	}

	public static String MD5EncodeToHex(String origin) {
		return bytesToHexString(MD5Encode(origin));
	}

	public static byte[] MD5Encode(String origin) {
		return MD5Encode(origin.getBytes());
	}

	public static byte[] MD5Encode(byte bytes[]) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			return md.digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

	public static long ipToLong(String strIp) {
		long ip[] = new long[4];
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		sb.append(String.valueOf(longIp >>> 24));
		sb.append(".");
		sb.append(String.valueOf((longIp & 16777215L) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longIp & 65535L) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longIp & 255L));
		return sb.toString();
	}

	public static Date longToDate(long l) {
		Date date = new Date();
		date.setTime(l);
		return date;
	}

	public static Date stringToDate(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.parse(s);
		} catch (Exception e) {
			return null;
		}
	}

	public static long dateToLong(Date date) {
		return date.getTime();
	}

	public static String dateToString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static Timestamp longToTimestamp(long l) {
		Timestamp timestamp = new Timestamp(l);
		return timestamp;
	}

	public static Timestamp stringToTimestamp(String s) {
		Timestamp timestamp = Timestamp.valueOf(s);
		return timestamp;
	}

	public static Timestamp stringToTimestamp(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return new Timestamp(df.parse(s).getTime());
		} catch (Exception e) {
			return null;
		}
	}

	public static long timestampToLong(Timestamp timestamp) {
		return timestamp.getTime();
	}

	public static String timestampToString(Timestamp timestamp) {
		return timestamp.toString();
	}

	public static Calendar longToCalendar(long l) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(l);
		return calendar;
	}

	public static Calendar timestampToCalendar(Timestamp t) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(t.getTime());
		return calendar;
	}

	public static Calendar stringToCalendar(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			return calendar;
		} catch (Exception e) {
			return null;
		}
	}

	public static long calendarToLong(Calendar calendar) {
		return calendar.getTimeInMillis();
	}

	public static String calendarToString(Calendar calendar, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(calendar.getTime());
	}

	public static List stringToWeeks(String s, String format) {
		try {
			List list = new ArrayList();
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			Calendar calendarFirst = Calendar.getInstance();
			calendarFirst.setTime(calendar.getTime());
			calendarFirst.set(5, calendar.getActualMinimum(5));
			Calendar calendarLast = Calendar.getInstance();
			calendarLast.setTime(calendar.getTime());
			calendarLast.set(5, calendar.getActualMaximum(5));
			int weekNum = calendarLast.get(4);
			int weekNo = calendarFirst.get(7);
			String beginDateWeek = df.format(calendarFirst.getTime());
			calendarFirst.add(5, 7 - weekNo);
			String endDateWeek = df.format(calendarFirst.getTime());
			list.add((new StringBuilder(String.valueOf(beginDateWeek))).append(",").append(endDateWeek).toString());
			for (int i = 2; i < weekNum; i++) {
				calendarFirst.add(5, 1);
				beginDateWeek = df.format(calendarFirst.getTime());
				calendarFirst.add(5, 6);
				endDateWeek = df.format(calendarFirst.getTime());
				list.add((new StringBuilder(String.valueOf(beginDateWeek))).append(",").append(endDateWeek).toString());
			}

			calendarFirst.add(5, 1);
			beginDateWeek = df.format(calendarFirst.getTime());
			endDateWeek = df.format(calendarLast.getTime());
			list.add((new StringBuilder(String.valueOf(beginDateWeek))).append(",").append(endDateWeek).toString());
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToYesterday(String s, String format) {
		return stringToBeforeDays(s, 1, format);
	}

	public static String stringToBeforeDays(String s, int days, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(5, -1 * days);
			String currentDate = df.format(calendar.getTime());
			return currentDate;
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToTomorrow(String s, String format) {
		return stringToAfterDays(s, 1, format);
	}

	public static Timestamp timestampToTomorrow(Timestamp t) {
		return timestampToAfterDays0(t, 1);
	}

	public static String stringToAfterDays(String s, int days, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(5, days);
			String currentDate = df.format(calendar.getTime());
			return currentDate;
		} catch (Exception e) {
			return null;
		}
	}

	public static Timestamp timestampToAfterDays(Timestamp t, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(t.getTime());
		calendar.add(5, days);
		return new Timestamp(calendar.getTimeInMillis());
	}

	public static Timestamp timestampToAfterDays0(Timestamp t, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(t.getTime());
		calendar.add(5, days);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	public static Timestamp timestampToAfterDays23(Timestamp t, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(t.getTime());
		calendar.add(5, days);
		calendar.set(11, 23);
		calendar.set(12, 59);
		calendar.set(13, 59);
		return new Timestamp(calendar.getTimeInMillis());
	}

	public static String stringToLastWeek(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(5, -7);
			String currentDate = df.format(calendar.getTime());
			return currentDate;
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToLastMonth(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(2, -1);
			String currentDate = df.format(calendar.getTime());
			return currentDate;
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToBeforeMonths(String s, int months, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(2, -months);
			String currentDate = df.format(calendar.getTime());
			return currentDate;
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToAfterMonths(String s, int months, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(2, months);
			String currentDate = df.format(calendar.getTime());
			return currentDate;
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToLastYear(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(1, -1);
			String currentDate = df.format(calendar.getTime());
			return currentDate;
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToCurrentWeeks(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.set(7, 1);
			String beginDate = df.format(calendar.getTime());
			calendar.set(7, 7);
			String endDate = df.format(calendar.getTime());
			return (new StringBuilder(String.valueOf(beginDate))).append(",").append(endDate).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToLastWeeks(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(5, -7);
			calendar.set(7, 1);
			String beginDate = df.format(calendar.getTime());
			calendar.set(7, 7);
			String endDate = df.format(calendar.getTime());
			return (new StringBuilder(String.valueOf(beginDate))).append(",").append(endDate).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToCurrentMonths(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.set(5, calendar.getActualMinimum(5));
			String beginDate = df.format(calendar.getTime());
			calendar.set(5, calendar.getActualMaximum(5));
			String endDate = df.format(calendar.getTime());
			return (new StringBuilder(String.valueOf(beginDate))).append(",").append(endDate).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToCurrentMonthsCn(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy\u5E74MM\u6708dd\u65E5");
			calendar.set(5, calendar.getActualMinimum(5));
			String beginDate = df2.format(calendar.getTime());
			calendar.set(5, calendar.getActualMaximum(5));
			String endDate = df2.format(calendar.getTime());
			return (new StringBuilder(String.valueOf(beginDate))).append("\u81F3").append(endDate).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToLastMonths(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(2, -1);
			calendar.set(5, calendar.getActualMinimum(5));
			String beginDate = df.format(calendar.getTime());
			calendar.set(5, calendar.getActualMaximum(5));
			String endDate = df.format(calendar.getTime());
			return (new StringBuilder(String.valueOf(beginDate))).append(",").append(endDate).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String stringToLastYears(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(1, -1);
			calendar.set(5, calendar.getActualMinimum(5));
			String beginDate = df.format(calendar.getTime());
			calendar.set(5, calendar.getActualMaximum(5));
			String endDate = df.format(calendar.getTime());
			return (new StringBuilder(String.valueOf(beginDate))).append(",").append(endDate).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static int stringToDays(String s, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			return calendar.getActualMaximum(5);
		} catch (Exception e) {
			return -1;
		}
	}

	public static int stringToDays(String s1, String s2, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s1));
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(df.parse(s2));
			int days = (int) ((calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / 86400000L + 1L);
			return days;
		} catch (Exception e) {
			return -1;
		}
	}

	public static int stringToMonths(String s1, String s2, String format) {
		try {
			int months = 0;
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s1));
			int year = calendar.get(1);
			int month = calendar.get(2) + 1;
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(df.parse(s2));
			int year2 = calendar2.get(1);
			int month2 = calendar2.get(2) + 1;
			if (year == year2)
				months = month2 - month;
			else
				months = (year2 - year) * 12 + (month2 - month);
			return months;
		} catch (Exception e) {
			return -1;
		}
	}

	public static String stringToTheOtherDays(String s, int theOtherDay, String format) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(df.parse(s));
			calendar.add(5, -1);
			String endDate = df.format(calendar.getTime());
			calendar.add(5, -1 * (theOtherDay - 1));
			String beginDate = df.format(calendar.getTime());
			return (new StringBuilder(String.valueOf(beginDate))).append(",").append(endDate).toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String htmlToTxt(String htmlStr) {
		if (htmlStr == null) {
			return "";
		} else {
			htmlStr = htmlStr.replaceAll("&", "&amp;");
			htmlStr = htmlStr.replaceAll("\"", "&quot;");
			htmlStr = htmlStr.replaceAll("\t", "&nbsp;&nbsp;");
			htmlStr = htmlStr.replaceAll(" ", "&nbsp;");
			htmlStr = htmlStr.replaceAll("<", "&lt;");
			htmlStr = htmlStr.replaceAll(">", "&gt;");
			return htmlStr;
		}
	}

	public static String utop(long l) {
		if (l > 2147483647L)
			return "";
		String digths = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String result = "";
		int tempInt = 0;
		String tempStr = "";
		for (tempInt = (int) ((long) tempInt + l); tempInt >= 36; tempInt /= 36)
			tempStr = (new StringBuilder(String.valueOf(digths.charAt(tempInt % 36)))).append(tempStr).toString();

		if (tempInt >= 0)
			tempStr = (new StringBuilder(String.valueOf(digths.charAt(tempInt)))).append(tempStr).toString();
		for (; tempStr.length() < 6; tempStr = (new StringBuilder("0")).append(tempStr).toString())
			;
		char chars1[] = tempStr.toCharArray();
		int len = 6;
		char chars2[] = new char[len];
		for (int i = 0; i < len; i++) {
			int j = i + 1;
			chars2[i] = chars1[len - j];
		}

		int len2 = digths.length();
		int m = digths.indexOf(chars2[0]);
		int n = m + 6 < len2 ? m + 6 : (m + 6) - len2;
		chars2[0] = digths.charAt(n);
		m = digths.indexOf(chars2[1]);
		n = m + 5 < len2 ? m + 5 : (m + 5) - len2;
		chars2[1] = digths.charAt(n);
		m = digths.indexOf(chars2[2]);
		n = m + 4 < len2 ? m + 4 : (m + 4) - len2;
		chars2[2] = digths.charAt(n);
		m = digths.indexOf(chars2[3]);
		n = m + 3 < len2 ? m + 3 : (m + 3) - len2;
		chars2[3] = digths.charAt(n);
		m = digths.indexOf(chars2[4]);
		n = m + 2 < len2 ? m + 2 : (m + 2) - len2;
		chars2[4] = digths.charAt(n);
		m = digths.indexOf(chars2[5]);
		n = m + 1 < len2 ? m + 1 : (m + 1) - len2;
		chars2[5] = digths.charAt(n);
		result = new String(chars2);
		return result;
	}

	public static long ptou(String str) {
		if (str == null || str.equals("") || str.length() != 6)
			return 0L;
		str = str.toUpperCase();
		char chars1[] = str.toCharArray();
		int len = chars1.length;
		char chars2[] = new char[len];
		String digths = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		long result = 0L;
		int len2 = digths.length();
		int m = digths.indexOf(chars1[0]);
		int n = m + 30 < len2 ? m + 30 : (m + 30) - len2;
		chars1[0] = digths.charAt(n);
		m = digths.indexOf(chars1[1]);
		n = m + 31 < len2 ? m + 31 : (m + 31) - len2;
		chars1[1] = digths.charAt(n);
		m = digths.indexOf(chars1[2]);
		n = m + 32 < len2 ? m + 32 : (m + 32) - len2;
		chars1[2] = digths.charAt(n);
		m = digths.indexOf(chars1[3]);
		n = m + 33 < len2 ? m + 33 : (m + 33) - len2;
		chars1[3] = digths.charAt(n);
		m = digths.indexOf(chars1[4]);
		n = m + 34 < len2 ? m + 34 : (m + 34) - len2;
		chars1[4] = digths.charAt(n);
		m = digths.indexOf(chars1[5]);
		n = m + 35 < len2 ? m + 35 : (m + 35) - len2;
		chars1[5] = digths.charAt(n);
		for (int i = 0; i < len; i++) {
			int j = i + 1;
			chars2[i] = chars1[len - j];
		}

		for (int k = len; k > 0; k--)
			result = (long) ((double) result + (double) digths.indexOf(chars2[k - 1]) * Math.pow(36D, len - k));

		return result;
	}

	public static String mtostar(String str) {
		return (new StringBuilder(String.valueOf(str.substring(0, 3)))).append("****")
				.append(str.substring(7, str.length())).toString();
	}

	public static String etoname(String str) {
		return str.substring(0, str.indexOf("@"));
	}

	public static int longToDays(long l1, long l2) {
		return (new Long((l2 - l1) / 86400000L)).intValue();
	}

	public static int longToHours(long l1, long l2) {
		return (new Long((l2 - l1) / 3600000L)).intValue();
	}

	public static int longToMinutes(long l1, long l2) {
		return (new Long((l2 - l1) / 60000L)).intValue();
	}

	public static int timestampToDays(Timestamp t1, Timestamp t2) {
		return (new Long((t2.getTime() - t1.getTime()) / 86400000L)).intValue();
	}

	public static int timestampToHours(Timestamp t1, Timestamp t2) {
		return (new Long((t2.getTime() - t1.getTime()) / 3600000L)).intValue();
	}

	public static int timestampToMinutes(Timestamp t1, Timestamp t2) {
		return (new Long((t2.getTime() - t1.getTime()) / 60000L)).intValue();
	}

	public static void main(String args1[]) {
	}
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: E:\JAVAEE\em_code\emvo\lib\hibernate5_spring5_util.jar Total
 * time: 49 ms Jad reported messages/errors: Exit status: 0 Caught exceptions:
 */