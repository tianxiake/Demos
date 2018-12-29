package com.runningsnail.demos;

import com.runningsnail.demos.common.utils.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liuyongjie create on 2018/12/21.
 */
public class Test {

	public static void main(String[] args) {
		String utcShowTime = getUTCShowTime(5693 + "");
		System.out.println(utcShowTime);
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
}
