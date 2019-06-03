package com.runningsnail.demos;

import com.runningsnail.demos.common.utils.StringUtils;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author liuyongjie create on 2018/12/21.
 */
public class Test {

	public static void main(String[] args) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);


		String value1 = MessageFormat.format("oh, {0} is 'a' pig", "ZhangSan");
		System.out.println(value1);

		String value2 = MessageFormat.format("oh, {0} is ''a'' pig", "ZhangSan");
		System.out.println(value2);

		String value3 = MessageFormat.format("oh, {1} is ''a'' pig", "ZhangSan");
		System.out.println(value3);

		System.out.println(MessageFormat.format("'{0}'-{1}", 1, 2));
		System.out.println(MessageFormat.format("{1}xxxx{2}", 1, 2));
        System.out.println(MessageFormat.format("{0}xxxx{0}", 1, 2));

	}


	public static String getUTCShowTime(String timeMillis) {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		long aLong = 0L;
		long startTime = -28800000L;//格林尼治时间 1970/01/01 星期四 00：00：00
		if (!StringUtils.isEmpty(timeMillis)) {
			try {
				aLong = Long.valueOf(timeMillis);
			} catch (Exception e) {
				aLong = 0L;
			}
		}
		aLong += startTime;
		Date date = new Date(aLong);
		return format.format(date);
	}


	public static void print(String... str) {
		System.out.println("可变长参数");
		if (str != null) {
			for (String s : str) {
				System.out.println(s);
			}
		}
	}

	public static void print(String str) {
		System.out.println("一个参数");
	}

	public static void print(String str, String str2) {
		System.out.println("两个参数");
	}

	public static void print(String str, String...str1) {
		System.out.println("------------------");
	}


	/**
	 * 格式化GMT时间字符串20160505171212 为UTC格式：20160505T091212Z
	 *
	 * @return
	 */
	public static String formatGMTStrTimeToUTC(String timeStr) {
		String result = "";
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date parseDate = simpleDateFormat.parse(timeStr);
			simpleDateFormat.applyPattern("yyyyMMdd'T'HHmmss'Z'");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+:00:00"));
			result = simpleDateFormat.format(parseDate);

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
